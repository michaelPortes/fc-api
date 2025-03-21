package com.example.fc_api.domains.categories.repository;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import com.example.fc_api.domains.categories.model.CategoriesModel;
import com.example.fc_api.domains.expenses.model.ExpenseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesDataAccess {

    private final CategoriesRepository categoriesRepository;

    public List<CategoriesModel> getCategories() throws ModelViolationException{
        return categoriesRepository.findAllCategories().stream().map(entity -> {
            try {
                return CategoriesModel.fromEntity(entity);
            } catch (ModelViolationException e){
                throw new IllegalArgumentException(e);
            }
        }).toList();
    }

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
