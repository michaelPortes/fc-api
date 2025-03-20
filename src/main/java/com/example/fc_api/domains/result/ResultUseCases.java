package com.example.fc_api.domains.result;

import com.example.fc_api.controller.enums.ExpensesTypes;
import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.expenses.repository.ExpenseDataAccess;
import com.example.fc_api.domains.result.model.ExpenseModel;
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

    public List<Object> getResults(LocalDate currentMonth) throws ModelViolationException {

        var expensesList = expenseDataAccess.getExpensesList(currentMonth, ExpensesTypes.FIXED.name());

        var totalFixed = sunItems(expensesList, "expenses", null);
        var totalVariable = sunItems(expensesList, "realExpenseMiddleMonth", "realExpenseFinalMonth");

        var monthSalary = salaryDataAccess.getSalaryList(currentMonth).getFirst().getSalary();


        return List.of(
                "fixedRest", monthSalary - totalFixed,
                "variableRest", monthSalary - totalVariable
        );
    }


    private Long sunItems(List<?> model, String column, String secondColumn){
        return model.stream().mapToLong(item -> {
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
        })
                .sum();

    }
}
