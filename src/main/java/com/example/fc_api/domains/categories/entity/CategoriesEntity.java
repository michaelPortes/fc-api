package com.example.fc_api.domains.categories.entity;

import com.example.fc_api.domains.categories.model.CategoriesModel;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    public void updateCategories(CategoriesModel categoriesModel){
        this.name = categoriesModel.getName();

    }

    public void deleteCategory(Long idToDelete){
        this.id = idToDelete;
    }
}

