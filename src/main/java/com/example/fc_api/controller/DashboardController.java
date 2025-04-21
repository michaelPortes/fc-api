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
import com.example.fc_api.domains.expenses.input.UpdateExpenseDTO;
import com.example.fc_api.domains.expenses.presentation.ExpenseDTO;
import com.example.fc_api.domains.result.ResultUseCases;
import com.example.fc_api.domains.salary.SalaryUseCases;
import com.example.fc_api.domains.salary.input.InsertSalaryDTO;
import com.example.fc_api.domains.salary.presentation.SalaryDTO;
import com.example.fc_api.helper.MessageCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = {"http://localhost:3000", "https://finance-whisperer-78.lovable.app"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final CategoriesUseCases categoriesUseCases;

    private final ExpensesUseCases expensesUseCases;

    private final SalaryUseCases salaryUseCases;

    private final CommonUseCases commonUseCases;

    private final ResultUseCases resultUseCases;

    @GetMapping("/categories")
    public ResponseEntity<ResponseBody<List<CategoriesDTO>>> getCategories() throws ModelViolationException{
        var response = categoriesUseCases.getCategories();
        return new ResponseBuilder<List<CategoriesDTO>>(HttpStatusCode.valueOf(200), response).build();
    }

    @PostMapping("/categories/insert")
    public ResponseEntity<ResponseBody<CategoriesDTO>> insertCategories(
            @RequestBody CategoriesPostParam categoriesPostParam
            ) throws ModelViolationException {

        var insertCategoriesDTO = InsertCategoriesDTO.builder()
                .name(categoriesPostParam.getName())
                .build();

        var categoriesInsert = categoriesUseCases.insertCategories(insertCategoriesDTO);

        return new ResponseBuilder<CategoriesDTO>(HttpStatusCode.valueOf(200), categoriesInsert).build();
    }

    @DeleteMapping("/category/delete")
    public ResponseEntity<ResponseBody<CategoriesDTO>> deleteCategory(
            @RequestParam(value = "id", required = true) Long id
    ) throws ModelViolationException {


        var deleteCategory = categoriesUseCases.deleteCategory(id);

        return new ResponseBuilder<CategoriesDTO>(HttpStatusCode.valueOf(200), deleteCategory).build();
    }

    @GetMapping("/expense/list")
    public ResponseEntity<ResponseBody<List<ExpenseDTO>>>  getExpensesList(
            @RequestParam(value = "currentDate", required = false) LocalDate currentDate
            ) throws ModelViolationException{

        var responseData = expensesUseCases.getExpensesList(commonUseCases.getReferenceDate(currentDate));

        return new ResponseBuilder<List<ExpenseDTO>>(HttpStatusCode.valueOf(200), responseData).build();
    }

    @GetMapping("/expense/copy")
    public ResponseEntity<ResponseBody<MessageCodes>> copyDataToNextMonth(
            @RequestParam(value = "currentDate", required = false) LocalDate currentDate
    ) throws ModelViolationException{

        var responseData = expensesUseCases.copyToNextMonth(commonUseCases.getReferenceDate(currentDate));

        return new ResponseBuilder<MessageCodes>(HttpStatusCode.valueOf(200), responseData).build();
    }

    @PutMapping("/expense/update")
    public ResponseEntity<ResponseBody<ExpenseDTO>> upDateExpenseItem(
            @RequestParam(value = "id", required = true) Long id,
            @RequestBody ExpensesPostParam fixedPostParam
    ) throws ModelViolationException{

        var updateItem = UpdateExpenseDTO.builder()
                .id(id)
                .name(fixedPostParam.getName())
                .description(fixedPostParam.getDescription())
                .expectedExpense(fixedPostParam.getExpectedExpense())
                .realExpenseMiddleMonth(fixedPostParam.getRealExpenseMiddleMonth())
                .realExpenseFinalMonth(fixedPostParam.getRealExpenseFinalMonth())
                .category(
                        fixedPostParam.getCategory() != null ?
                                CategoriesEntity.builder().id(fixedPostParam.getCategory().getId()).build() :
                                null
                )
                .currentDate(fixedPostParam.getCurrentDate())
                .type(fixedPostParam.getType())
                .build();

        var responseData = expensesUseCases.updateExpenses(updateItem);

        return new ResponseBuilder<ExpenseDTO>(HttpStatusCode.valueOf(200), responseData).build();
    }

    @PostMapping("/expense/insert")
    public ResponseEntity<ResponseBody<ExpenseDTO>> insertExpenses(
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

        var responseDate = expensesUseCases.insertExpenses(fixedItem);

        return new ResponseBuilder<ExpenseDTO>(HttpStatusCode.valueOf(200), responseDate).build();
    }

    @DeleteMapping("/expense/delete")
    public ResponseEntity<ResponseBody<ExpenseDTO>> deleteExpense(
            @RequestParam(value = "id", required = true) Long id
    ) throws ModelViolationException {


        var deleteFixed = expensesUseCases.deleteExpenses(id);

        return new ResponseBuilder<ExpenseDTO>(HttpStatusCode.valueOf(200), deleteFixed).build();
    }

    @GetMapping("/salary")
    public ResponseEntity<ResponseBody<List<SalaryDTO>>> getSalaryList(
            @RequestParam(value = "currentDate", required = false) LocalDate currentDate
    ) throws ModelViolationException{

        var responseData = salaryUseCases.getSalaryList(commonUseCases.getReferenceDate(currentDate));

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

        salaryUseCases.insertSalary(variableItem);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/salary/delete")
    public ResponseEntity<ResponseBody<SalaryDTO>> deleteSalary(
            @RequestParam(value = "id", required = true) Long id
    ) throws ModelViolationException {

        var deleteVariable = salaryUseCases.deleteSalary(id);

        return new ResponseBuilder<SalaryDTO>(HttpStatusCode.valueOf(200), deleteVariable).build();
    }

    @GetMapping("/result")
    public ResponseEntity<ResponseBody<Object>> getResult(
            @RequestParam(value = "currentDate", required = false) LocalDate currentDate
    )throws ModelViolationException{

        var response = resultUseCases.getRestSalary(commonUseCases.getReferenceDate(currentDate));

        return new ResponseBuilder<Object>(HttpStatusCode.valueOf(200), response).build();
    }

    @GetMapping("/result/chart")
    public ResponseEntity<ResponseBody<Map<String, List<Double>>>> getLastSexMonth(
            @RequestParam(value = "currentDate", required = false) LocalDate currentDate
    ) throws ModelViolationException{

        var response = resultUseCases.getSixMonthsAgo(commonUseCases.getReferenceDate(currentDate));

        return new ResponseBuilder<Map<String, List<Double>>>(HttpStatusCode.valueOf(200), response).build();
    }

    @GetMapping("/percentage")
    public ResponseEntity<ResponseBody<Object>> getPercentage(
            @RequestParam(value = "currentDate", required = false) LocalDate currentDate
    ) throws  ModelViolationException{

        var response = resultUseCases.getPercentage(commonUseCases.getReferenceDate(currentDate));

        return new ResponseBuilder<Object>(HttpStatusCode.valueOf(200), response).build();
    }
}
