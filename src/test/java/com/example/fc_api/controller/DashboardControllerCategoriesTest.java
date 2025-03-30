package com.example.fc_api.controller;

import com.example.fc_api.controller.param.CategoriesPostParam;
import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.CategoriesUseCases;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.lang.reflect.Array;
import java.util.Objects;


@SpringBootTest
@Sql(scripts = {"/mock-data/schema.sql", "/mock-data/categories.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DashboardControllerCategoriesTest {

    @Autowired
    DashboardController dashboardController;

    @Test
    void testGetListCategories_success() throws ModelViolationException{

        var response = dashboardController.getCategories();

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(10, Objects.requireNonNull(response.getBody()).getData().size());
    }

    @Test
    void testInsertCategories_success() throws ModelViolationException{

            CategoriesPostParam nameCategory = new CategoriesPostParam(11L, "test");

        var response = dashboardController.insertCategories(nameCategory);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(11, response.getBody().getData().getId());
        Assertions.assertEquals("test", response.getBody().getData().getName());
    }

    @Test
    void testDeleteCategory_success() throws ModelViolationException{

        Long categoryId = 11L;

        var response = dashboardController.deleteCategory(categoryId);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(11, response.getBody().getData().getId());
    }
}
