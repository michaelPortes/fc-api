package com.example.fc_api.domains.categories.repository;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import com.example.fc_api.domains.categories.model.CategoriesModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoriesDataAccess {

    private final CategoriesRepository categoriesRepository;

    public CategoriesModel upsertCategories(CategoriesModel categoriesModel) throws ModelViolationException {

        CategoriesEntity categoriesEntity;

        categoriesEntity = CategoriesEntity.builder().build();
        categoriesEntity.updateCategories(categoriesModel);

        var createEntity = categoriesRepository.save(categoriesEntity);

        return CategoriesModel.fromEntity(createEntity);
    }


    public CategoriesModel deleteCategory(Long categoriesModel) throws ModelViolationException{
        CategoriesEntity categoriesEntity;

        categoriesEntity = CategoriesEntity.builder().build();
        categoriesEntity.deleteCategory(categoriesModel);

        categoriesRepository.delete(categoriesEntity);

        return CategoriesModel.deleteCategory(categoriesModel);
    }
}
