package com.example.fc_api.controller;

import com.example.fc_api.config.ResponseBody;
import com.example.fc_api.config.ResponseBuilder;
import com.example.fc_api.controller.param.CategoriesPostParam;
import com.example.fc_api.controller.param.ExpensesPostParam;
import com.example.fc_api.controller.param.SalaryPostParam;
import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.CategoriesUseCases;
import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import com.example.fc_api.domains.categories.input.InsertCategoriesDTO;
import com.example.fc_api.domains.categories.presentation.CategoriesDTO;
import com.example.fc_api.domains.common.model.CommonUseCases;
import com.example.fc_api.domains.expenses.ExpensesUseCases;
import com.example.fc_api.domains.expenses.input.InsertExpenseDTO;
import com.example.fc_api.domains.expenses.presentation.ExpenseDTO;
import com.example.fc_api.domains.salary.SalaryUseCases;
import com.example.fc_api.domains.salary.input.InsertSalaryDTO;
import com.example.fc_api.domains.salary.presentation.SalaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final CategoriesUseCases categoriesUseCases;

    private final ExpensesUseCases fixedUseCases;

    private final SalaryUseCases salaryUseCases;

    private final CommonUseCases commonUseCases;

    @PostMapping("/categories")
    private ResponseEntity<ResponseBody<CategoriesDTO>> insertCategories(
            @RequestBody CategoriesPostParam categoriesPostParam
            ) throws ModelViolationException {

        var insertCategoriesDTO = InsertCategoriesDTO.builder()
                .name(categoriesPostParam.getName())
                .build();

        var categoriesInsert = categoriesUseCases.insertCategories(insertCategoriesDTO);

        return new ResponseBuilder<CategoriesDTO>(HttpStatusCode.valueOf(200), categoriesInsert).build();
    }

    @DeleteMapping("/category/delete")
    private ResponseEntity<ResponseBody<CategoriesDTO>> deleteCategory(
            @RequestParam(value = "id", required = true) Long id
    ) throws ModelViolationException {


        var deleteCategory = categoriesUseCases.deleteCategory(id);

        return new ResponseBuilder<CategoriesDTO>(HttpStatusCode.valueOf(200), deleteCategory).build();
    }

    @GetMapping("/expense/list")
    public ResponseEntity<ResponseBody<List<ExpenseDTO>>>  getFixedList(
            @RequestParam(value = "currentDate", required = false) LocalDate currentDate,
            @RequestParam(value = "enumType", required = true) String enumType
            ) throws ModelViolationException{

        var date = commonUseCases.getReferenceDate(currentDate);

        var responseData = fixedUseCases.getExpensesList(date, enumType);

        return new ResponseBuilder<List<ExpenseDTO>>(HttpStatusCode.valueOf(200), responseData).build();
    }


    @PostMapping("/expense/insert")
    public ResponseEntity<ResponseBody<ExpenseDTO>> insertFixed(
            @RequestBody ExpensesPostParam fixedPostParam
            ) throws ModelViolationException{

        var fixedItem = InsertExpenseDTO.builder()
                .name(fixedPostParam.getName())
                .description(fixedPostParam.getDescription())
                .expectedExpense(fixedPostParam.getExpectedExpense())
                .realExpenseMiddleMonth(fixedPostParam.getRealExpenseMiddleMonth())
                .realExpenseFinalMonth(fixedPostParam.getRealExpenseFinalMonth())
                .category(
                        fixedPostParam.getCategory() != null ?
                                CategoriesEntity.builder().id(fixedPostParam.getCategory().getId()).build() :
                                null // Ensure it's not null
                )
                .currentDate(fixedPostParam.getCurrentDate())
                .type(fixedPostParam.getType())
                .build();

        var responseDate = fixedUseCases.insertExpenses(fixedItem);

        return new ResponseBuilder<ExpenseDTO>(HttpStatusCode.valueOf(200), responseDate).build();
    }

    @DeleteMapping("/expense/delete")
    private ResponseEntity<ResponseBody<ExpenseDTO>> deleteFixed(
            @RequestParam(value = "id", required = true) Long id
    ) throws ModelViolationException {


        var deleteFixed = fixedUseCases.deleteExpenses(id);

        return new ResponseBuilder<ExpenseDTO>(HttpStatusCode.valueOf(200), deleteFixed).build();
    }

    @GetMapping("/salary")
    public ResponseEntity<ResponseBody<List<SalaryDTO>>> getSalaryList(
            @RequestParam(value = "currentDate", required = false) LocalDate currentDate
    ) throws ModelViolationException{

        var date = commonUseCases.getReferenceDate(currentDate);
        var responseData = salaryUseCases.getSalaryList(date);

        return new ResponseBuilder<List<SalaryDTO>>(HttpStatusCode.valueOf(200), responseData).build();
    }

    @PostMapping("/salary/insert")
    public ResponseEntity<ResponseBody<SalaryDTO>> insertSalary(
            @RequestBody SalaryPostParam salaryPostParam
    ) throws ModelViolationException{

        var variableItem = InsertSalaryDTO.builder()
                .salary(salaryPostParam.getSalary())
                .currentDate(salaryPostParam.getCurrentDate())
                .build();

        var responseDate = salaryUseCases.insertSalary(variableItem);

        return new ResponseBuilder<SalaryDTO>(HttpStatusCode.valueOf(200), responseDate).build();
    }

    @DeleteMapping("/salary/delete")
    private ResponseEntity<ResponseBody<SalaryDTO>> deleteSalary(
            @RequestParam(value = "id", required = true) Long id
    ) throws ModelViolationException {


        var deleteVariable = salaryUseCases.deleteSalary(id);

        return new ResponseBuilder<SalaryDTO>(HttpStatusCode.valueOf(200), deleteVariable).build();
    }

}
