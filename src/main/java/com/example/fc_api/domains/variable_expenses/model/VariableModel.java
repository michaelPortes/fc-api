package com.example.fc_api.domains.variable_expenses.model;


import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import com.example.fc_api.domains.variable_expenses.entity.VariableEntity;
import com.example.fc_api.domains.variable_expenses.input.InsertVariableDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder(builderClassName = "VariableModelBuilder", toBuilder = true)
@Getter
public class VariableModel {

    private Long id;
    private String name;
    private String description;
    private Long expectedExpense;
    private Long realExpenseMiddleMonth;
    private Long realExpenseFinalMonth;
    private CategoriesEntity category;
    private LocalDate currentDate;

    public static class VariableModelBuilder {
        public VariableModel build()  {
            var variableModel = new VariableModel(id, name, description, expectedExpense, realExpenseMiddleMonth, realExpenseFinalMonth, category, currentDate);
            validate(variableModel);
            return variableModel;
        }
    }

    public static void validate(VariableModel variableModel){
        // Implement validate;
    }

    public static VariableModel fromEntity(VariableEntity variableEntity) throws ModelViolationException{
        return VariableModel.builder()
                .id(variableEntity.getId())
                .name(variableEntity.getName())
                .description(variableEntity.getDescription())
                .expectedExpense(variableEntity.getExpectedExpense())
                .realExpenseMiddleMonth(variableEntity.getRealExpenseMiddleMonth())
                .realExpenseFinalMonth(variableEntity.getRealExpenseFinalMonth())
                .currentDate(variableEntity.getCurrentDate())
                .build();
    }

    public static VariableModel fromInputVariable(InsertVariableDTO insert) throws ModelViolationException {
        return VariableModel.builder()
                .name(insert.getName())
                .description(insert.getDescription())
                .expectedExpense(insert.getExpectedExpense())
                .realExpenseMiddleMonth(insert.getRealExpenseMiddleMonth())
                .realExpenseFinalMonth(insert.getRealExpenseFinalMonth())
                .category(insert.getCategory())
                .currentDate(insert.getCurrentDate())
                .build();

    }

    public static VariableModel deleteVariable(Long delete) throws ModelViolationException{
        return VariableModel.builder()
                .id(delete)
                .build();
    }
}
