package com.example.fc_api.domains.expenses;

import com.example.fc_api.controller.enums.ExpensesTypes;
import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.expenses.input.InsertExpenseDTO;
import com.example.fc_api.domains.expenses.model.ExpenseModel;
import com.example.fc_api.domains.expenses.presentation.ExpenseDTO;
import com.example.fc_api.domains.expenses.repository.ExpenseDataAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExpensesUseCases {

    private final ExpenseDataAccess expenseDataAccess;

    public List<ExpenseDTO> getExpensesList(LocalDate currentMonth) throws ModelViolationException {

        var fixedList = expenseDataAccess.getExpenses(currentMonth);

        return fixedList.stream().map(entity ->
            ExpenseDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .expectedExpense(entity.getExpenses())
                    .realExpenseMiddleMonth(entity.getRealExpenseMiddleMonth())
                    .realExpenseFinalMonth(entity.getRealExpenseFinalMonth())
                    .type(entity.getType())
                    .build()
        ).toList();
    }

    public ExpenseDTO insertExpenses(InsertExpenseDTO insert) throws ModelViolationException {


        var newExpenses = ExpenseModel.fromInputExpenses(insert);
        var responseData = expenseDataAccess.upsertExpenses(newExpenses);

        return ExpenseDTO.fromModel(responseData).toBuilder().build();
    }

   public ExpenseDTO deleteExpenses(Long fixedModel) throws ModelViolationException{

        var delete = expenseDataAccess.deleteExpenses(fixedModel);

        return ExpenseDTO.fromModel(delete).toBuilder().build();

   }

   private String getEnumByType(String type) {
       if (type.equals("FIXED")) {
           return ExpensesTypes.FIXED.name();
       } else if (type.equals("VARIABLE")) {
            return ExpensesTypes.VARIABLE.name();
       }else {
           return ExpensesTypes.INVESTMENT.name();
       }
   }
}
