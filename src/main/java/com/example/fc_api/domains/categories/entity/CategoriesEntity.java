package com.example.fc_api.domains.categories.entity;

import com.example.fc_api.domains.categories.model.CategoriesModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "categories")
@Table(name = "categories")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriesEntity{

    @Id
    private Long id;

    @NotNull
    private String name;

    public void updateCategories(CategoriesModel categoriesModel){

        this.id = categoriesModel.getId();
        this.name = categoriesModel.getName();

    }
}

