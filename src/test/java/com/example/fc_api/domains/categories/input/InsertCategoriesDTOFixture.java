package com.example.fc_api.domains.categories.input;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InsertCategoriesDTOFixture {
    public static InsertCategoriesDTO InsertCategoryDTO(String name){
        return new InsertCategoriesDTO(name);
    }
}
