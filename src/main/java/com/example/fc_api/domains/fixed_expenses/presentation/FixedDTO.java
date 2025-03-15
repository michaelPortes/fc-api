package com.example.fc_api.domains.fixed_expenses.presentation;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class FixedDTO {

    private String name;
    private String description;
    private Long expectedExpense;
    private Long realExpenseMiddleMonth;
    private Long realExpenseFinalMonth;
}
