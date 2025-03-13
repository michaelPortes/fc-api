package com.example.fc_api.domains.categories.input;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InsertCategoriesDTO {

    private Long id;
    private String name;
}
