package com.example.fc_api.domains.expenses.input;

import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder(toBuilder = true)
public class InsertExpenseDTO {
    private String name;
    private String description;
    private Long expectedExpense;
    private Long realExpenseMiddleMonth;
    private Long realExpenseFinalMonth;
    private CategoriesEntity category;
    private LocalDate currentDate;
    private String type;
}
