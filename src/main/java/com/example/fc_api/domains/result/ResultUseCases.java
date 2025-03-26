package com.example.fc_api.domains.result;

import com.example.fc_api.controller.enums.ExpensesTypes;
import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.expenses.repository.ExpenseDataAccess;
import com.example.fc_api.domains.result.presentation.DefaultResultDTO;
import com.example.fc_api.domains.result.presentation.PercentageDTO;
import com.example.fc_api.domains.salary.repository.SalaryDataAccess;
import com.example.fc_api.helper.MessageCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

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

        double investmentPercentage = percentage(monthSalary, sumItems(expensesList, "realExpenseMiddleMonth", "realExpenseFinalMonth", ExpensesTypes.INVESTMENT.name()));
        double variablePercentage = percentage(monthSalary, sumItems(expensesList, "realExpenseMiddleMonth", "realExpenseFinalMonth", ExpensesTypes.VARIABLE.name()));
        double fixedPercentage = percentage(monthSalary, sumItems(expensesList, "realExpenseMiddleMonth", "realExpenseFinalMonth", ExpensesTypes.FIXED.name()));


        return PercentageDTO.builder()
                .investment(investmentPercentage)
                .variable(variablePercentage)
                .fixed(fixedPercentage)
                .build();
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
