package com.example.fc_api.controller;


import com.example.fc_api.controller.param.SalaryPostParam;
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
@Sql(scripts = {"/mock-data/schema.sql", "/mock-data/salary.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DashboardControllerSalaryTest {

    @Autowired
    DashboardController dashboardController;

    @Test
    void testGetSalary_success() throws ModelViolationException{

        LocalDate mockDate = LocalDate.parse("2025-03-10");

        var response = dashboardController.getSalaryList(mockDate);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(1L, response.getBody().getData().getFirst().getId());
        Assertions.assertEquals(125, response.getBody().getData().getFirst().getSalary());
    }

    @Test
    void testInsertSalary_success() throws ModelViolationException{

        SalaryPostParam salary = new SalaryPostParam(123L, LocalDate.parse("2025-03-01"));

        var response = dashboardController.insertSalary(salary);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(2L, response.getBody().getData().getId());
        Assertions.assertEquals(123, response.getBody().getData().getSalary());
    }

    @Test
    void testDeleteSalary_success() throws ModelViolationException{

        Long expenseId = 1L;

        var response = dashboardController.deleteSalary(expenseId);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(1, response.getBody().getData().getId());
    }
}
