package com.example.fc_api.domains.categories.presentation;


import com.example.fc_api.domains.categories.model.CategoriesModel;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class CategoriesDTO {

    private Long id;
    private String name;

    public static CategoriesDTO fromModel(CategoriesModel categoriesModel){
        return CategoriesDTO.builder()
                .id(categoriesModel.getId())
                .name(categoriesModel.getName())
                .build();
    }
}
