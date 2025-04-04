package com.example.fc_api.domains.expenses;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import com.example.fc_api.domains.expenses.input.InsertExpenseDTO;
import com.example.fc_api.domains.expenses.model.ExpenseModel;
import com.example.fc_api.domains.expenses.presentation.ExpenseDTO;
import com.example.fc_api.domains.expenses.repository.ExpenseDataAccess;
import com.example.fc_api.helper.MessageCodes;
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

   public MessageCodes copyToNextMonth(LocalDate currentMonth) throws ModelViolationException{
        var nextMonth = expenseDataAccess.getExpenses(currentMonth.plusMonths(1));

        if(nextMonth.isEmpty()){
            var actualMonth = expenseDataAccess.getExpenses(currentMonth);
            for(int integrate = 0; integrate < actualMonth.size(); integrate ++ ){

                var fixedItem = InsertExpenseDTO.builder()
                        .name(actualMonth.get(integrate).getName())
                        .description(actualMonth.get(integrate).getDescription())
                        .expectedExpense(actualMonth.get(integrate).getExpenses())
                        .realExpenseMiddleMonth(actualMonth.get(integrate).getRealExpenseMiddleMonth())
                        .realExpenseFinalMonth(actualMonth.get(integrate).getRealExpenseFinalMonth())
                        .category(
                                actualMonth.get(integrate).getCategory() != null ?
                                        CategoriesEntity.builder().id(actualMonth.get(integrate).getCategory().getId()).build() :
                                        CategoriesEntity.builder()
                                                .id(20L).build()
                        )
                        .currentDate(currentMonth.plusMonths(1))
                        .type(actualMonth.get(integrate).getType())
                        .build();


                expenseDataAccess.upsertExpenses(ExpenseModel.fromInputExpenses(fixedItem));

            }

        }
        return MessageCodes.ADD_SUCCESSFUL;
   }
}
