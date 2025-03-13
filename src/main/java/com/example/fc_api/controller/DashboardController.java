package com.example.fc_api.controller;

import com.example.fc_api.config.ResponseBody;
import com.example.fc_api.config.ResponseBuilder;
import com.example.fc_api.controller.param.CategoriesPostParam;
import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.categories.CategoriesUseCases;
import com.example.fc_api.domains.categories.input.InsertCategoriesDTO;
import com.example.fc_api.domains.categories.presentation.CategoriesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final CategoriesUseCases categoriesUseCases;

    @PostMapping("/categories")
    private ResponseEntity<ResponseBody<CategoriesDTO>> insertCategories(
            @RequestBody CategoriesPostParam categoriesPostParam
            ) throws ModelViolationException {

        var insertCategoriesDTO = InsertCategoriesDTO.builder()
                .id(categoriesPostParam.getId())
                .name(categoriesPostParam.getName())
                .build();

        var categoriesInsert = categoriesUseCases.insertCategories(insertCategoriesDTO);

        return new ResponseBuilder<CategoriesDTO>(HttpStatusCode.valueOf(200), categoriesInsert).build();
    }
}
