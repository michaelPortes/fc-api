package com.example.fc_api.domains.fixed_expenses.input;

import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder(toBuilder = true)
public class InsertFixedDTO {
    private String name;
    private String description;
    private Long expectedExpense;
    private Long realExpenseMiddleMonth;
    private Long realExpenseFinalMonth;
    private CategoriesEntity category;
    private LocalDate currentDate;
}
