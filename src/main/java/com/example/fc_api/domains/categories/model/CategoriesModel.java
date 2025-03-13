package com.example.fc_api.domains.categories.model;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import com.example.fc_api.domains.categories.input.DeleteCategory;
import com.example.fc_api.domains.categories.input.InsertCategoriesDTO;
import lombok.Builder;
import lombok.Getter;

@Builder(builderClassName = "CategoriesModelBuilder")
@Getter
public class CategoriesModel {

    private Long id;
    private String name;

    public static class CategoriesModelBuilder{
        public CategoriesModel build(){
            var categoriesModel = new CategoriesModel(id, name);
            validate(categoriesModel);
            return categoriesModel;
        }
    }

    private static void validate(CategoriesModel categoriesModel){
        // Implement validate
    }


    public static CategoriesModel fromEntity(CategoriesEntity entity) throws ModelViolationException{
        return CategoriesModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static CategoriesModel fromInputDTO(InsertCategoriesDTO categoriesDTO) throws ModelViolationException{
        return CategoriesModel.builder()
                .id(categoriesDTO.getId())
                .name(categoriesDTO.getName())
                .build();
    }
    public static CategoriesModel deleteCategory(Long deleteCategory) throws ModelViolationException{
        return CategoriesModel.builder()
                .id(deleteCategory)
                .build();
    }
}
