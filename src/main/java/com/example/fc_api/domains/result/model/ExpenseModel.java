package com.example.fc_api.domains.result.model;


import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder(builderClassName = "ExpensesModelBuilder", toBuilder = true)
@Getter
public class ExpenseModel {

    private Long id;
    private String name;
    private String description;
    private Long expenses;
    private Long realExpenseMiddleMonth;
    private Long realExpenseFinalMonth;
    private CategoriesEntity category;
    private LocalDate currentDate;
    private String type;

    public static class ExpensesModelBuilder {
        public ExpenseModel build()  {
            var fixedModel = new ExpenseModel(id, name, description, expenses, realExpenseMiddleMonth, realExpenseFinalMonth, category, currentDate, type);
            validate(fixedModel);
            return fixedModel;
        }
    }

    public static void validate(ExpenseModel expenseModel){
        // Implement validate;
    }
}
