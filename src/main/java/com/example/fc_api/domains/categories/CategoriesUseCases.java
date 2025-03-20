package com.example.fc_api.domains.categories;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.input.InsertCategoriesDTO;
import com.example.fc_api.domains.categories.model.CategoriesModel;
import com.example.fc_api.domains.categories.presentation.CategoriesDTO;
import com.example.fc_api.domains.categories.repository.CategoriesDataAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesUseCases {

    private final CategoriesDataAccess categoriesDataAccess;

    public List<CategoriesDTO> getCategories() throws ModelViolationException{

        var categories = categoriesDataAccess.getCategories();
        return categories.stream().map(entity ->
                CategoriesDTO.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .build()
        ).toList();
    }

    public CategoriesDTO insertCategories(InsertCategoriesDTO insertCategoriesDTO) throws ModelViolationException {

        var categories = CategoriesModel.fromInputDTO(insertCategoriesDTO);
        var categoriesModel = categoriesDataAccess.upsertCategories(categories);

        return CategoriesDTO.fromModel(categoriesModel).toBuilder().build();
    }

    public CategoriesDTO deleteCategory(Long deleteCategory) throws ModelViolationException{

        var delete = categoriesDataAccess.deleteCategory(deleteCategory);

        return CategoriesDTO.fromModel(delete).toBuilder().build();
    }
}
