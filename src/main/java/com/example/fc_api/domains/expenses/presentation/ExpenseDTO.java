package com.example.fc_api.domains.expenses.presentation;

import com.example.fc_api.domains.expenses.model.ExpenseModel;
import lombok.Builder;
import lombok.Getter;


@Builder(toBuilder = true)
@Getter
public class ExpenseDTO {

    private Long id;
    private String name;
    private String description;
    private Long expectedExpense;
    private Long realExpenseMiddleMonth;
    private Long realExpenseFinalMonth;4
    private Long category;
    private String type;


    public static ExpenseDTO fromModel(ExpenseModel expenseModel){
        return ExpenseDTO.builder()
                .id(expenseModel.getId())
                .name(expenseModel.getName())
                .description(expenseModel.getDescription())
                .expectedExpense(expenseModel.getExpenses())
                .realExpenseMiddleMonth(expenseModel.getRealExpenseMiddleMonth())
                .realExpenseFinalMonth(expenseModel.getRealExpenseFinalMonth())
                .category(expenseModel.getCategory().getId())
                .type(expenseModel.getType())
                .build();
    }
}
