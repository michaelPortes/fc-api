package com.example.fc_api.controller;

import com.example.fc_api.config.ResponseBody;
import com.example.fc_api.controller.param.CategoriesPostParam;
import com.example.fc_api.controller.param.ExpensesPostParam;
import com.example.fc_api.controller.param.SalaryPostParam;
import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.CategoriesUseCases;
import com.example.fc_api.domains.categories.presentation.CategoriesDTO;
import com.example.fc_api.domains.common.model.CommonUseCases;
import com.example.fc_api.domains.expenses.ExpensesUseCases;
import com.example.fc_api.domains.expenses.presentation.ExpenseDTO;
import com.example.fc_api.domains.result.ResultUseCases;
import com.example.fc_api.domains.salary.SalaryUseCases;
import com.example.fc_api.domains.salary.presentation.SalaryDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DashboardControllerTest {

    @Mock
    private CategoriesUseCases categoriesUseCases;

    @Mock
    private ExpensesUseCases expensesUseCases;

    @Mock
    private SalaryUseCases salaryUseCases;

    @Mock
    private CommonUseCases commonUseCases;

    @Mock
    private ResultUseCases resultUseCases;

    @InjectMocks
    private DashboardController dashboardController;

    @Test
    void testGetCategories() throws ModelViolationException {
        when(categoriesUseCases.getCategories()).thenReturn(Collections.emptyList());

        ResponseEntity<ResponseBody<List<CategoriesDTO>>> response = dashboardController.getCategories();

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().getData().isEmpty());
    }

    @Test
    void testGetExpenseList() throws ModelViolationException {
        LocalDate date = LocalDate.now();
        when(commonUseCases.getReferenceDate(any())).thenReturn(date);
        when(expensesUseCases.getExpensesList(date)).thenReturn(Collections.emptyList());

        ResponseEntity<ResponseBody<List<ExpenseDTO>>> response = dashboardController.getFixedList(date);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().getData().isEmpty());
    }

    @Test
    void testGetSalaryList() throws ModelViolationException {
        LocalDate date = LocalDate.now();
        when(commonUseCases.getReferenceDate(any())).thenReturn(date);
        when(salaryUseCases.getSalaryList(date)).thenReturn(Collections.emptyList());

        ResponseEntity<ResponseBody<List<SalaryDTO>>> response = dashboardController.getSalaryList(date);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().getData().isEmpty());
    }

    @Test
    void testGetPercentage() throws ModelViolationException {
        LocalDate date = LocalDate.now();
        when(commonUseCases.getReferenceDate(any())).thenReturn(date);
        when(resultUseCases.getPercentage(date)).thenReturn(Collections.singletonList("test"));

        ResponseEntity<ResponseBody<List<Object>>> response = dashboardController.getPercentage(date);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertFalse(response.getBody().getData().isEmpty());
    }
}