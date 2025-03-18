package com.example.fc_api.controller;

import com.example.fc_api.config.ResponseBody;
import com.example.fc_api.config.ResponseBuilder;
import com.example.fc_api.controller.param.CategoriesPostParam;
import com.example.fc_api.controller.param.FixedPostParam;
import com.example.fc_api.controller.param.SalaryPostParam;
import com.example.fc_api.controller.param.VariablePostParam;
import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.CategoriesUseCases;
import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import com.example.fc_api.domains.categories.input.InsertCategoriesDTO;
import com.example.fc_api.domains.categories.presentation.CategoriesDTO;
import com.example.fc_api.domains.fixed_expenses.FixedUseCases;
import com.example.fc_api.domains.fixed_expenses.input.InsertFixedDTO;
import com.example.fc_api.domains.fixed_expenses.presentation.FixedDTO;
import com.example.fc_api.domains.salary.SalaryUseCases;
import com.example.fc_api.domains.salary.input.InsertSalaryDTO;
import com.example.fc_api.domains.salary.presentation.SalaryDTO;
import com.example.fc_api.domains.variable_expenses.VariableUseCases;
import com.example.fc_api.domains.variable_expenses.input.InsertVariableDTO;
import com.example.fc_api.domains.variable_expenses.presentation.VariableDTO;
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

    private final FixedUseCases fixedUseCases;

    private final VariableUseCases variableUseCases;

    private final SalaryUseCases salaryUseCases;

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

    @GetMapping("/fixed/list")
    public ResponseEntity<ResponseBody<List<FixedDTO>>>  getFixedList(
            @RequestParam(value = "currentDate", required = true) LocalDate currentDate
            ) throws ModelViolationException{

        var responseData = fixedUseCases.getFixedList(currentDate);

        return new ResponseBuilder<List<FixedDTO>>(HttpStatusCode.valueOf(200), responseData).build();
    }


    @PostMapping("/fixed/insert")
    public ResponseEntity<ResponseBody<FixedDTO>> insertFixed(
            @RequestBody FixedPostParam fixedPostParam
            ) throws ModelViolationException{

        var fixedItem = InsertFixedDTO.builder()
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
                .build();

        var responseDate = fixedUseCases.insertFixed(fixedItem);

        return new ResponseBuilder<FixedDTO>(HttpStatusCode.valueOf(200), responseDate).build();
    }

    @DeleteMapping("/fixed/delete")
    private ResponseEntity<ResponseBody<FixedDTO>> deleteFixed(
            @RequestParam(value = "id", required = true) Long id
    ) throws ModelViolationException {


        var deleteFixed = fixedUseCases.deleteFixed(id);

        return new ResponseBuilder<FixedDTO>(HttpStatusCode.valueOf(200), deleteFixed).build();
    }

    @GetMapping("/variable/list")
    public ResponseEntity<ResponseBody<List<VariableDTO>>>  getVariableList(
            @RequestParam(value = "currentDate", required = true) LocalDate currentDate
    ) throws ModelViolationException{

        var responseData = variableUseCases.getVariableList(currentDate);

        return new ResponseBuilder<List<VariableDTO>>(HttpStatusCode.valueOf(200), responseData).build();
    }

    @PostMapping("/variable/insert")
    public ResponseEntity<ResponseBody<VariableDTO>> insertVariable(
            @RequestBody VariablePostParam variablePostParam
    ) throws ModelViolationException{

        var variableItem = InsertVariableDTO.builder()
                .name(variablePostParam.getName())
                .description(variablePostParam.getDescription())
                .expectedExpense(variablePostParam.getExpectedExpense())
                .realExpenseMiddleMonth(variablePostParam.getRealExpenseMiddleMonth())
                .realExpenseFinalMonth(variablePostParam.getRealExpenseFinalMonth())
                .category(
                        variablePostParam.getCategory() != null ?
                                CategoriesEntity.builder().id(variablePostParam.getCategory().getId()).build() :
                                null
                )
                .currentDate(variablePostParam.getCurrentDate())
                .build();

        var responseDate = variableUseCases.insertVariable(variableItem);

        return new ResponseBuilder<VariableDTO>(HttpStatusCode.valueOf(200), responseDate).build();
    }

    @DeleteMapping("/variable/delete")
    private ResponseEntity<ResponseBody<VariableDTO>> deleteVariable(
            @RequestParam(value = "id", required = true) Long id
    ) throws ModelViolationException {


        var deleteVariable = variableUseCases.deleteVariable(id);

        return new ResponseBuilder<VariableDTO>(HttpStatusCode.valueOf(200), deleteVariable).build();
    }

    @GetMapping("/salary")
    public ResponseEntity<ResponseBody<List<SalaryDTO>>> getSalaryList(
            @RequestParam(value = "currentDate", required = true) LocalDate currentDate
    ) throws ModelViolationException{

        var responseData = salaryUseCases.getSalaryList(currentDate);

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
