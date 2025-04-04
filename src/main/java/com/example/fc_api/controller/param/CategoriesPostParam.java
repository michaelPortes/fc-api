package com.example.fc_api.controller.param;

import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CategoriesPostParam {

    private Long id;
    private String name;
}
