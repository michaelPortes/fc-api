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

    private String description;

    @NotNull
    @Column(name = "expected_expense")
    private Long expectedExpense;

    @Column(name = "real_expense_middle_month")
    private Long realExpenseMiddleMonth;

    @Column(name = "real_expense_final_month")
    private Long realExpenseFinalMonth;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoriesEntity category;

    @NotNull
    @Column(name = "reference_date ")
    private LocalDate currentDate;

}
