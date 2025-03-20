package com.example.fc_api.domains.result.presentation;

import com.example.fc_api.domains.result.model.ExpenseModel;
import lombok.Builder;
import lombok.Getter;


@Builder(toBuilder = true)
@Getter
public class ExpenseDTO {

    private String name;
    private String description;
    private Long expectedExpense;
    private Long realExpenseMiddleMonth;
    private Long realExpenseFinalMonth;
    private String type;


    public static ExpenseDTO fromModel(ExpenseModel expenseModel){
        return ExpenseDTO.builder()
                .name(expenseModel.getName())
                .description(expenseModel.getDescription())
                .expectedExpense(expenseModel.getExpenses())
                .realExpenseMiddleMonth(expenseModel.getRealExpenseMiddleMonth())
                .realExpenseFinalMonth(expenseModel.getRealExpenseFinalMonth())
                .type(expenseModel.getType())
                .build();
    }
}
