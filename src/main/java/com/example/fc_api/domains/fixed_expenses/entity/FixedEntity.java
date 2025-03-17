package com.example.fc_api.domains.fixed_expenses.entity;


import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import com.example.fc_api.domains.fixed_expenses.model.FixedModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fixed_expenses")
@Builder
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

    public void upsertFixed(FixedModel fixedModel){
        this.name = fixedModel.getName();
        this.description = fixedModel.getDescription();
        this.expectedExpense = fixedModel.getExpectedExpense();
        this.realExpenseMiddleMonth = fixedModel.getRealExpenseMiddleMonth();
        this.realExpenseFinalMonth = fixedModel.getRealExpenseFinalMonth();
        this.category = fixedModel.getCategory();
        this.currentDate = fixedModel.getCurrentDate();

    }

    public void deleteFixed(Long id){
        this.id = id;
    }

}
