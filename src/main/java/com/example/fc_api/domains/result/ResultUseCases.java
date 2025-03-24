package com.example.fc_api.domains.result;

import com.example.fc_api.controller.enums.ExpensesTypes;
import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.expenses.repository.ExpenseDataAccess;
import com.example.fc_api.domains.result.presentation.DefaultResultDTO;
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
        var preview = sunItems(expensesList, "expenses", null, null);
        var actual = sunItems(expensesList, "realExpenseMiddleMonth", "realExpenseFinalMonth", null);

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

    public List<Object> getPercentage(LocalDate currentMonth) throws ModelViolationException{

        var expensesList = expenseDataAccess.getExpenses(currentMonth);
        var monthSalary = salaryDataAccess.getSalaryList(currentMonth).getFirst().getSalary();

        double investmentPercentage = percentage(monthSalary, sunItems(expensesList, "realExpenseMiddleMonth", "realExpenseFinalMonth", ExpensesTypes.INVESTMENT.name()));
        double variablePercentage = percentage(monthSalary, sunItems(expensesList, "realExpenseMiddleMonth", "realExpenseFinalMonth", ExpensesTypes.FIXED.name()));
        double fixedPercentage = percentage(monthSalary, sunItems(expensesList, "realExpenseMiddleMonth", "realExpenseFinalMonth", ExpensesTypes.VARIABLE.name()));


        return List.of(
                "investment", investmentPercentage,
                "variable", variablePercentage,
                "fixed", fixedPercentage
        );
    }

    private double percentage(double salary, double quantityType) {
        return (quantityType / salary) * 100;
    }

    public Long sunItems(List<?> model, String column, String secondColumn, String type){

            return model.stream().filter( item ->
                    {
                        try {
                            return type == null ||
                                item.getClass().getDeclaredField("type").get(item).equals(type);
                        } catch (IllegalAccessException | NoSuchFieldException e) {
                            throw new RuntimeException(e);
                        }
                    })
                .mapToLong(item -> {
            try {
                Field field1 = item.getClass().getDeclaredField(column);
                field1.setAccessible(true);
                Long value1 = (Long) field1.get(item);

                if (value1 == null) {
                    Field field2 = item.getClass().getDeclaredField(secondColumn);
                    field2.setAccessible(true);
                    return (Long) field2.get(item);
                }
                return value1;
            } catch (Exception e) {
                throw new RuntimeException(MessageCodes.IF_COLUMN_ERROR.getMessageCode());
            }
        }).sum();
    }
}
