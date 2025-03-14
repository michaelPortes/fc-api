package com.example.fc_api.domains.fixed_expenses.model;


import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.model.CategoriesModel;
import com.example.fc_api.domains.fixed_expenses.entity.FixedEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder(builderClassName = "FixedModelBuilder", toBuilder = true)
@Getter
public class FixedModel {

    private Long id;
    private String name;
    private String description;
    private Long value;
    private CategoriesModel category;
    private LocalDate currentDate;

    public static class FixedModelBuilder {
        public FixedModel build()  {
            var fixedModel = new FixedModel(id, name, description, value, category, currentDate);
            validate(fixedModel);
            return fixedModel;
        }
    }

    public static void validate(FixedModel  fixedModel){
        // Implement validate;
    }

    public static FixedModel fromEntity(FixedEntity fixedEntity) throws ModelViolationException{
        return FixedModel.builder()
                .id(fixedEntity.getId())
                .name(fixedEntity.getName())
                .description(fixedEntity.getDescription())
                .value(fixedEntity.getValue())
                .category(CategoriesModel.fromEntity(fixedEntity.getCategory()))
                .currentDate(fixedEntity.getCurrentDate())
                .build();
    }
}
