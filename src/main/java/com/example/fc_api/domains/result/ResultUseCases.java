package com.example.fc_api.domains.result;

import com.example.fc_api.controller.enums.ExpensesTypes;
import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.expenses.model.ExpenseModel;
import com.example.fc_api.domains.expenses.presentation.ExpenseDTO;
import com.example.fc_api.domains.expenses.repository.ExpenseDataAccess;
import com.example.fc_api.domains.result.presentation.DefaultResultDTO;
import com.example.fc_api.domains.result.presentation.PercentageDTO;
import com.example.fc_api.domains.salary.model.SalaryModel;
import com.example.fc_api.domains.salary.presentation.SalaryDTO;
import com.example.fc_api.domains.salary.repository.SalaryDataAccess;
import com.example.fc_api.helper.MessageCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ResultUseCases {

    private final ExpenseDataAccess expenseDataAccess;

    private final SalaryDataAccess salaryDataAccess;

    public Object getRestSalary(LocalDate currentMonth) throws ModelViolationException {

        var expensesList = expenseDataAccess.getExpenses(currentMonth);

        var monthSalary = salaryDataAccess.getSalaryList(currentMonth).getFirst().getSalary();
        var preview = sumItems(expensesList, "expenses", null, null);
        var actual = sumItems(expensesList, "realExpenseMiddleMonth", "realExpenseFinalMonth", null);

        var variance = preview - actual;
        var expectedSurpluses = monthSalary - preview;
        var actualSurpluses = monthSalary - actual;


        return DefaultResultDTO.builder()
                .expectedExpenses(preview)
                .actualExpenses(actual)
                .variance(variance)
                .expectedSurpluses(expectedSurpluses)
                .actualSurpluses(actualSurpluses)
                .build();
    }

    public Object getPercentage(LocalDate currentMonth) throws ModelViolationException{

        var expensesList = expenseDataAccess.getExpenses(currentMonth);
        var monthSalary = salaryDataAccess.getSalaryList(currentMonth).getFirst().getSalary();

        double totalValueInvestment = sumItems(expensesList, "realExpenseMiddleMonth", "realExpenseFinalMonth", ExpensesTypes.INVESTMENT.name());
        double totalValueVariable = sumItems(expensesList, "realExpenseMiddleMonth", "realExpenseFinalMonth", ExpensesTypes.VARIABLE.name());
        double totalValueFixed = sumItems(expensesList, "realExpenseMiddleMonth", "realExpenseFinalMonth", ExpensesTypes.FIXED.name());

        double investmentPercentage = percentage(monthSalary, totalValueInvestment);
        double variablePercentage = percentage(monthSalary, totalValueVariable);
        double fixedPercentage = percentage(monthSalary, totalValueFixed);


        return PercentageDTO.builder()
                .investment(investmentPercentage)
                .investmentValue(totalValueInvestment)
                .variable(variablePercentage)
                .variableValue(totalValueVariable)
                .fixed(fixedPercentage)
                .fixedValue(totalValueFixed)
                .build();
    }

    public Map<String, List<Double>> getSixMonthsAgo(LocalDate currentDate) throws ModelViolationException{

        // Fetch data for the last 6 months
        List<SalaryModel> sixMonthsSalaries = salaryDataAccess.getSalaryBetweenDates(
                currentDate.minusMonths(6), currentDate
        );
        List<ExpenseModel> sixMonthsExpenses = expenseDataAccess.getExpensesListBetweenDates(
                currentDate.minusMonths(6), currentDate
        );

        // Check if we have expense data
        if (sixMonthsExpenses.isEmpty()) {
            throw new IllegalStateException("No expense data available for the last 6 months.");
        }

        // Group expenses by year-month and calculate sums
        Map<String, List<Double>> resultByMonth = sixMonthsExpenses.stream()
                .collect(Collectors.groupingBy(
                        expense -> {
                            LocalDate date = expense.getCurrentDate();
                            return date.format(DateTimeFormatter.ofPattern("yyyy-MM")); // e.g., "2024-03"
                        },
                        Collectors.collectingAndThen(Collectors.toList(), expenses -> {
                            List<Double> sums = new ArrayList<>();

                            // 1st item: Sum of 'expenses' for this month
                            double totalExpenses = expenses.stream()
                                    .mapToLong(ExpenseModel::getExpenses)
                                    .sum();
                            sums.add(totalExpenses);

                            // 2nd item: Sum of 'realExpenseMiddleMonth' + 'realExpenseFinalMonth' for this month
                            double totalRealExpenses = expenses.stream()
                                    .mapToLong(expense -> {
                                        Long middleMonth = expense.getRealExpenseMiddleMonth();
                                        Long finalMonth = expense.getRealExpenseFinalMonth();
                                        return (middleMonth != null ? middleMonth : 0L) +
                                                (finalMonth != null ? finalMonth : 0L);
                                    })
                                    .sum();
                            sums.add(totalRealExpenses);

                            // 3rd item: Sum of salaries based on unique dates in sixMonthsSalaries
                            double totalMatchingSalaries = sixMonthsSalaries.stream()
                                    .filter(salary -> sixMonthsExpenses.stream()
                                            .anyMatch(expense -> expense.currentDate.equals(salary.getCurrentDate())))
                                    .mapToDouble(SalaryModel::getSalary)
                                    .sum();

                            sums.add(totalMatchingSalaries);

                            return sums;
                        })
                ));

        return resultByMonth;
    }

    private double percentage(double salary, double quantityType) {
        return (quantityType / salary) * 100;
    }

    public Long sumItems(List<?> model, String column, String secondColumn, String type) {
        return model.stream()
                .filter(item -> {
                    try {
                        return type == null || item.getClass().getDeclaredField("type").get(item).equals(type);
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                })
                .mapToLong(item -> {
                    try {
                        Long total = 0L;

                        if (column != null) {
                            Field field1 = item.getClass().getDeclaredField(column);
                            field1.setAccessible(true);
                            Long value1 = (Long) field1.get(item);
                            total += (value1 != null ? value1 : 0);
                        }

                        if (secondColumn != null) {
                            Field field2 = item.getClass().getDeclaredField(secondColumn);
                            field2.setAccessible(true);
                            Long value2 = (Long) field2.get(item);
                            total += (value2 != null ? value2 : 0);
                        }

                        return total;
                    } catch (Exception e) {
                        throw new RuntimeException(MessageCodes.IF_COLUMN_ERROR.getMessageCode());
                    }
                }).sum();
    }
}
