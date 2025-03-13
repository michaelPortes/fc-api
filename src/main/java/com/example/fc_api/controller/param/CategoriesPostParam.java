package com.example.fc_api.controller.param;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoriesPostParam {

    private Long id;
    private String name;
}
