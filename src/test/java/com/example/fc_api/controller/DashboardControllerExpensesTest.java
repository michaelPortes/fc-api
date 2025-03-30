

package com.example.fc_api.controller;

import com.example.fc_api.controller.param.CategoriesPostParam;
import com.example.fc_api.controller.param.ExpensesPostParam;
import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.Objects;


@SpringBootTest
@Sql(scripts = {"/mock-data/schema.sql", "/mock-data/categories.sql", "/mock-data/expenses.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DashboardControllerExpensesTest {


    @Autowired
    DashboardController dashboardController;

    @Test
    void testGetListExpenses_success() throws ModelViolationException {

        LocalDate mockDate = LocalDate.parse("2025-03-10");

        var response = dashboardController.getExpensesList(mockDate);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(1, Objects.requireNonNull(response.getBody()).getData().size());
        Assertions.assertEquals("test", Objects.requireNonNull(response.getBody().getData().getFirst().getName()));
        Assertions.assertEquals("xasfdsa", Objects.requireNonNull(response.getBody().getData().getFirst().getDescription()));
        Assertions.assertEquals(121L, Objects.requireNonNull(response.getBody().getData().getFirst().getExpectedExpense()));
        Assertions.assertEquals(null, Objects.requireNonNull(response.getBody().getData().getFirst().getRealExpenseMiddleMonth()));
        Assertions.assertEquals(121L, Objects.requireNonNull(response.getBody().getData().getFirst().getRealExpenseFinalMonth()));
    }

    @Test
    void testInsertExpense_success() throws ModelViolationException {
        CategoriesEntity category = new CategoriesEntity(1L, "Categoria Teste");

        ExpensesPostParam insertExpenses = new ExpensesPostParam("teste","xasfdsa",121L,null,123L, category, LocalDate.parse("2025-03-01"),"VARIABLE");

        var response = dashboardController.insertExpenses(insertExpenses);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals("teste", response.getBody().getData().getName());
        Assertions.assertEquals("xasfdsa", Objects.requireNonNull(response.getBody().getData().getDescription()));
        Assertions.assertEquals(121L, Objects.requireNonNull(response.getBody().getData().getExpectedExpense()));
        Assertions.assertEquals(123L, Objects.requireNonNull(response.getBody().getData().getRealExpenseFinalMonth()));
    }

    @Test
    void testDeleteExpense_success() throws ModelViolationException {

        Long expenseId = 11L;

        var response = dashboardController.deleteExpense(expenseId);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(11, response.getBody().getData().getId());
    }
}
