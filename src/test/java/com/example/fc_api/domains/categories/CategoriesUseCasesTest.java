package com.example.fc_api.domains.categories;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import com.example.fc_api.domains.categories.input.InsertCategoriesDTO;
import com.example.fc_api.domains.categories.input.InsertCategoriesDTOFixture;
import com.example.fc_api.domains.categories.model.CategoriesModel;
import com.example.fc_api.domains.categories.model.CategoriesModelFixture;
import com.example.fc_api.domains.categories.presentation.CategoriesDTO;
import com.example.fc_api.domains.categories.repository.CategoriesDataAccess;
import com.example.fc_api.domains.categories.repository.CategoriesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoriesUseCasesTest {

    @Autowired
    CategoriesUseCases categoriesUseCases;

    @MockBean
    CategoriesDataAccess categoriesDataAccess;

    @Test
    void testGetCategoriesList_success() throws ModelViolationException{

        var listCategories = CategoriesModelFixture.createListCategories(2);
        when(categoriesDataAccess.getCategories()).thenReturn(listCategories);

        var categoriesList = categoriesUseCases.getCategories();

        Assertions.assertEquals(2, categoriesList.size());
        Assertions.assertEquals(1L, categoriesList.getFirst().getId());
        Assertions.assertEquals("newCategory", categoriesList.getFirst().getName());
    }

    @Test
    void testInsertCategory_success() throws ModelViolationException{

        var newCategory = InsertCategoriesDTOFixture.InsertCategoryDTO("newCategory");
        when(categoriesDataAccess.upsertCategories(Mockito.<CategoriesModel>any()))
                .thenReturn(CategoriesModel.fromInputDTO(newCategory));

        var categoriesList = categoriesUseCases.insertCategories(newCategory);

        Assertions.assertEquals("newCategory", categoriesList.getName());
    }

    @Test
    void testDeleteCategory_success() throws ModelViolationException {

        Long categoryId = 1L;

        CategoriesModel stubModel = CategoriesModel.builder()
                .id(categoryId)
                .build();
        when(categoriesDataAccess.deleteCategory(categoryId)).thenReturn(stubModel);

        CategoriesDTO result = categoriesUseCases.deleteCategory(categoryId);
        verify(categoriesDataAccess, times(1)).deleteCategory(categoryId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(categoryId, result.getId());
    }
}
