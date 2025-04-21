package com.example.fc_api.domains.expenses;

import com.example.fc_api.controller.enums.ExpensesTypeTest;
import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import com.example.fc_api.domains.common.model.CommonUseCasesTest;
import com.example.fc_api.domains.expenses.input.InsertExpenseDTO;
import com.example.fc_api.domains.expenses.input.InsertExpenseDTOFixture;
import com.example.fc_api.domains.expenses.model.ExpenseModel;
import com.example.fc_api.domains.expenses.model.ExpensesModelTest;
import com.example.fc_api.domains.expenses.presentation.ExpenseDTO;
import com.example.fc_api.domains.expenses.repository.ExpenseDataAccess;
import com.example.fc_api.helper.MessageCodes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ExpensesUseCasesTest {

    @Autowired
    ExpensesUseCases expensesUseCases;

    @MockBean
    ExpenseDataAccess expenseDataAccess;

    static MockedStatic<LocalDate> localDateMock = Mockito.mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS);

    @AfterEach
    public void tearDown(){
        localDateMock.clearInvocations();
    }

    @AfterAll
    public static void onEnd() {
        localDateMock.close();
    }

    @Test
    void testGetListExpenses_success_enumTypeFIXED() throws ModelViolationException {

        var current_date = LocalDate.parse("2025-03-01");
        localDateMock.when(() -> LocalDate.now(Mockito.any(ZoneOffset.class))).thenReturn(current_date);

        var listExpenses = ExpensesModelTest.createListExpenses(1, ExpensesTypeTest.FIXED);
        when(expenseDataAccess.getExpenses(Mockito.any(LocalDate.class))).thenReturn(listExpenses);

        LocalDate currentDate = CommonUseCasesTest.testGetCurrentDate();
        var response = expensesUseCases.getExpensesList(currentDate);

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals("FIXED", response.getFirst().getType());
    }

    @Test
    void testGetListExpenses_success_enumTypeVARIABLE() throws ModelViolationException {

        var current_date = LocalDate.parse("2025-03-01");
        localDateMock.when(() -> LocalDate.now(Mockito.any(ZoneOffset.class))).thenReturn(current_date);

        var listExpenses = ExpensesModelTest.createListExpenses(1, ExpensesTypeTest.VARIABLE);
        when(expenseDataAccess.getExpenses(Mockito.any(LocalDate.class))).thenReturn(listExpenses);

        LocalDate currentDate = CommonUseCasesTest.testGetCurrentDate();
        var response = expensesUseCases.getExpensesList(currentDate);

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals("VARIABLE", response.getFirst().getType());
    }

    @Test
    void testGetListExpenses_success_enumTypeINVESTMENT() throws ModelViolationException {

        var current_date = LocalDate.parse("2025-03-01");
        localDateMock.when(() -> LocalDate.now(Mockito.any(ZoneOffset.class))).thenReturn(current_date);

        var listExpenses = ExpensesModelTest.createListExpenses(1, ExpensesTypeTest.INVESTMENT);
        when(expenseDataAccess.getExpenses(Mockito.any(LocalDate.class))).thenReturn(listExpenses);

        LocalDate currentDate = CommonUseCasesTest.testGetCurrentDate();
        var response = expensesUseCases.getExpensesList(currentDate);

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals("INVESTMENT", response.getFirst().getType());
    }

    @Test
    void testInsertExpense_success_enumTypeFIXED() throws ModelViolationException{
        CategoriesEntity category = new CategoriesEntity(1L, "Categoria Teste");

        var newExpense = InsertExpenseDTOFixture.InsertExpenseDTOFixture(
                InsertExpenseDTO.builder()
                        .name( "newExpense")
                        .description("newDescription")
                        .expectedExpense(123L)
                        .realExpenseMiddleMonth(null)
                        .realExpenseFinalMonth(123L)
                        .category(category)
                        .currentDate(CommonUseCasesTest.testGetCurrentDate())
                        .type(ExpensesTypeTest.FIXED.name()).build());

        when(expenseDataAccess.upsertExpenses(Mockito.<ExpenseModel>any()))
                .thenReturn(ExpenseModel.fromInputExpenses(newExpense));

        var expensesList = expensesUseCases.insertExpenses(newExpense);

        Assertions.assertEquals("newExpense", expensesList.getName());
        Assertions.assertEquals(ExpensesTypeTest.FIXED.name(), expensesList.getType());
    }

    @Test
    void testInsertExpense_success_enumTypeVARIABLE() throws ModelViolationException{
        CategoriesEntity category = new CategoriesEntity(1L, "Categoria Teste");

        var newExpense = InsertExpenseDTOFixture.InsertExpenseDTOFixture(
                InsertExpenseDTO.builder()
                        .name( "newExpense")
                        .description("newDescription")
                        .expectedExpense(123L)
                        .realExpenseMiddleMonth(null)
                        .realExpenseFinalMonth(123L)
                        .category(category)
                        .currentDate(CommonUseCasesTest.testGetCurrentDate())
                        .type(ExpensesTypeTest.VARIABLE.name()).build());

        when(expenseDataAccess.upsertExpenses(Mockito.<ExpenseModel>any()))
                .thenReturn(ExpenseModel.fromInputExpenses(newExpense));

        var expensesList = expensesUseCases.insertExpenses(newExpense);

        Assertions.assertEquals("newExpense", expensesList.getName());
        Assertions.assertEquals(ExpensesTypeTest.VARIABLE.name(), expensesList.getType());
    }

    @Test
    void testInsertExpense_success_enumTypeINVESTMENT() throws ModelViolationException{
        CategoriesEntity category = new CategoriesEntity(1L, "Categoria Teste");

        var newExpense = InsertExpenseDTOFixture.InsertExpenseDTOFixture(
                InsertExpenseDTO.builder()
                        .name( "newExpense")
                        .description("newDescription")
                        .expectedExpense(123L)
                        .realExpenseMiddleMonth(null)
                        .realExpenseFinalMonth(123L)
                        .category(category)
                        .currentDate(CommonUseCasesTest.testGetCurrentDate())
                        .type(ExpensesTypeTest.INVESTMENT.name()).build());

        when(expenseDataAccess.upsertExpenses(Mockito.<ExpenseModel>any()))
                .thenReturn(ExpenseModel.fromInputExpenses(newExpense));

        var expensesList = expensesUseCases.insertExpenses(newExpense);

        Assertions.assertEquals("newExpense", expensesList.getName());
        Assertions.assertEquals(ExpensesTypeTest.INVESTMENT.name(), expensesList.getType());
    }

//    @Test
//    void testDeleteCategory_success() throws ModelViolationException{
//
//        Long expenseId = 11L;
//
//        ExpenseModel expenseModel = ExpenseModel.builder().id(expenseId).build();
//        when(expenseDataAccess.deleteExpenses(expenseId)).thenReturn(expenseModel);
//
//        ExpenseDTO result = expensesUseCases.deleteExpenses(expenseId);
//        verify(expenseDataAccess, times(1)).deleteExpenses(expenseId);
//
//
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(expenseId, result.getId());
//    }

    @Test
    void testCopyToNextMonth_success()throws ModelViolationException{

        LocalDate currentMonth = LocalDate.of(2025, 3, 1);
        LocalDate nextMonth = currentMonth.plusMonths(1);

        List<ExpenseModel> actualMonthExpenses = List.of(
                ExpenseModel.builder()
                        .name("Expense 1")
                        .description("Description 1")
                        .expenses(100L)
                        .realExpenseMiddleMonth(50L)
                        .realExpenseFinalMonth(50L)
                        .category(CategoriesEntity.builder().id(10L).build())
                        .currentDate(currentMonth)
                        .type("FIXED")
                        .build(),
                ExpenseModel.builder()
                        .name("Expense 2")
                        .description("Description 2")
                        .expenses(200L)
                        .realExpenseMiddleMonth(100L)
                        .realExpenseFinalMonth(100L)
                        .category(null) // Testando a lógica do ID 20L como fallback
                        .currentDate(currentMonth)
                        .type("VARIABLE")
                        .build()
        );
        when(expenseDataAccess.getExpenses(nextMonth)).thenReturn(Collections.emptyList());
        when(expenseDataAccess.getExpenses(currentMonth)).thenReturn(actualMonthExpenses);

        MessageCodes result = expensesUseCases.copyToNextMonth(currentMonth);
        verify(expenseDataAccess, times(actualMonthExpenses.size()))
                .upsertExpenses(any(ExpenseModel.class));

        Assertions.assertEquals(MessageCodes.ADD_SUCCESSFUL, result);
    }
}


