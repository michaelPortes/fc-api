package com.example.fc_api.domains.fixed_expenses.entity;


import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import com.example.fc_api.domains.categories.model.CategoriesModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fixed")
public class FixedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Long value;

    @JoinColumn(name = "category")
    private CategoriesEntity category;

    @NotNull
    private LocalDate currentDate;

}
