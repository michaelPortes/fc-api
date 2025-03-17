package com.example.fc_api.domains.variable_expenses.presentation;

import com.example.fc_api.domains.variable_expenses.model.VariableModel;
import lombok.Builder;
import lombok.Getter;


@Builder(toBuilder = true)
@Getter
public class VariableDTO {

    private String name;
    private String description;
    private Long expectedExpense;
    private Long realExpenseMiddleMonth;
    private Long realExpenseFinalMonth;



    public static VariableDTO fromModel(VariableModel variableModel){
        return VariableDTO.builder()
                .name(variableModel.getName())
                .description(variableModel.getDescription())
                .expectedExpense(variableModel.getExpectedExpense())
                .realExpenseMiddleMonth(variableModel.getRealExpenseMiddleMonth())
                .realExpenseFinalMonth(variableModel.getRealExpenseFinalMonth())
                .build();
    }
}
