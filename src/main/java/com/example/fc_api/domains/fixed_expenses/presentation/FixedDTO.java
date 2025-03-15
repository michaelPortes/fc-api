package com.example.fc_api.domains.fixed_expenses.presentation;

import com.example.fc_api.domains.fixed_expenses.model.FixedModel;
import lombok.Builder;
import lombok.Getter;


@Builder(toBuilder = true)
@Getter
public class FixedDTO {

    private String name;
    private String description;
    private Long expectedExpense;
    private Long realExpenseMiddleMonth;
    private Long realExpenseFinalMonth;



    public static FixedDTO fromModel(FixedModel fixedModel){
        return FixedDTO.builder()
                .name(fixedModel.getName())
                .description(fixedModel.getDescription())
                .expectedExpense(fixedModel.getExpectedExpense())
                .realExpenseMiddleMonth(fixedModel.getRealExpenseMiddleMonth())
                .realExpenseFinalMonth(fixedModel.getRealExpenseFinalMonth())
                .build();
    }
}
