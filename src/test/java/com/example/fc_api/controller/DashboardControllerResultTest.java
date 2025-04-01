package com.example.fc_api.controller;

import com.example.fc_api.custon.exception.ModelViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

@SpringBootTest
@Sql(scripts = {"/mock-data/schema.sql", "/mock-data/categories.sql", "/mock-data/salary.sql", "/mock-data/expenses.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DashboardControllerResultTest {

    @Autowired
    DashboardController dashboardController;

    @Test
    void testGetResult_success() throws ModelViolationException{

        LocalDate mockDate = LocalDate.parse("2025-03-10");

        var response = dashboardController.getResult(mockDate);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    void testGetLastMonthResult_success() throws ModelViolationException{

        LocalDate currentDate = LocalDate.parse("2025-03-10");

        var response = dashboardController.getLastSexMonth(currentDate);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(1, response.getBody().getData().size());
    }
}
