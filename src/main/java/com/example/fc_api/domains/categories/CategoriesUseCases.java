package com.example.fc_api.domains.categories;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.input.InsertCategoriesDTO;
import com.example.fc_api.domains.categories.model.CategoriesModel;
import com.example.fc_api.domains.categories.presentation.CategoriesDTO;
import com.example.fc_api.domains.categories.repository.CategoriesDataAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoriesUseCases {

    private final CategoriesDataAccess categoriesDataAccess;

    public CategoriesDTO insertCategories(InsertCategoriesDTO insertCategoriesDTO) throws ModelViolationException {

        var categories = CategoriesModel.fromInputDTO(insertCategoriesDTO);
        var categoriesModel = categoriesDataAccess.upsertCategories(categories);

        return CategoriesDTO.fromModel(categoriesModel).toBuilder().build();
    }
}
