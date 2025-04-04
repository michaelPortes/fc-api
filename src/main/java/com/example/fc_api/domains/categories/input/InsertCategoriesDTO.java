package com.example.fc_api.domains.categories.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class InsertCategoriesDTO {
    private String name;
}
