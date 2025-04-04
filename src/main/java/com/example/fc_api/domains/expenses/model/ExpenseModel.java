package com.example.fc_api.domains.expenses.model;


import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import com.example.fc_api.domains.expenses.entity.ExpensesEntity;
import com.example.fc_api.domains.expenses.input.InsertExpenseDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder(builderClassName = "ExpensesModelBuilder", toBuilder = true)
@Getter
public class ExpenseModel {

    public Long id;
    public String name;
    public String description;
    public Long expenses;
    public Long realExpenseMiddleMonth;
    public Long realExpenseFinalMonth;
    public CategoriesEntity category;
    public LocalDate currentDate;
    public String type;

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

    public static ExpenseModel fromEntity(ExpensesEntity expenseEntity) throws ModelViolationException{
        return ExpenseModel.builder()
                .id(expenseEntity.getId())
                .name(expenseEntity.getName())
                .description(expenseEntity.getDescription())
                .expenses(expenseEntity.getExpense())
                .realExpenseMiddleMonth(expenseEntity.getRealExpenseMiddleMonth())
                .realExpenseFinalMonth(expenseEntity.getRealExpenseFinalMonth())
                .category(expenseEntity.getCategory())
                .currentDate(expenseEntity.getCurrentDate())
                .type(expenseEntity.getType())
                .build();
    }

    public static ExpenseModel fromInputExpenses(InsertExpenseDTO insert) throws ModelViolationException {
        return ExpenseModel.builder()
                .name(insert.getName())
                .description(insert.getDescription())
                .expenses(insert.getExpectedExpense())
                .realExpenseMiddleMonth(insert.getRealExpenseMiddleMonth())
                .realExpenseFinalMonth(insert.getRealExpenseFinalMonth())
                .category(insert.getCategory())
                .currentDate(insert.getCurrentDate())
                .type(insert.getType())
                .build();

    }

    public static ExpenseModel deleteExpenses(Long delete) throws ModelViolationException{
        return ExpenseModel.builder()
                .id(delete)
                .build();
    }
}
