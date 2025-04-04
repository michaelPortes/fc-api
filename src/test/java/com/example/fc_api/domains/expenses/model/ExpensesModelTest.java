package com.example.fc_api.domains.expenses.model;

import com.example.fc_api.controller.enums.ExpensesTypeTest;
import com.example.fc_api.controller.enums.ExpensesTypes;
import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import jakarta.persistence.EnumType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpensesModelTest {

    public static ExpenseModel createExpenseModelValid(String id, ExpensesTypeTest enumType) throws ModelViolationException{
        return ExpenseModel.builder()
                .id(1L)
                .name("newExpense")
                .description("newDescription")
                .expenses(123L)
                .realExpenseMiddleMonth(null)
                .realExpenseFinalMonth(123L)
                .category(CategoriesEntity.builder().build())
                .currentDate(LocalDate.parse("2025-03-01"))
                .type(enumType.name())
                .build();
    }

    public static List<ExpenseModel> createListExpenses(int quantity, ExpensesTypeTest type) throws ModelViolationException{
        List<ExpenseModel> expenseModels = new ArrayList<>();
        for(int i = 0; i < quantity; i ++){
            var expenseModel = createExpenseModelValid(String.valueOf(i), type);
            expenseModels.add(expenseModel);
        }
        return expenseModels;
    }
}
