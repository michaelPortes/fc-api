package com.example.fc_api.domains.categories.model;

import com.example.fc_api.custon.exception.ModelViolationException;

import java.util.ArrayList;
import java.util.List;

public class CategoriesModelFixture {

    public static CategoriesModel createCategoryModelValid(String id) throws ModelViolationException{
        return CategoriesModel.builder()
                .id(1L)
                .name("newCategory")
                .build();
    }

    public static List<CategoriesModel> createListCategories(int quantity) throws ModelViolationException{

        List<CategoriesModel> categoryModels = new ArrayList<>();

        for(int i = 0; i < quantity; i ++){
            var categoryModel = createCategoryModelValid(String.valueOf(i));
            categoryModels.add(categoryModel);
        }

        return categoryModels;
    }
}
