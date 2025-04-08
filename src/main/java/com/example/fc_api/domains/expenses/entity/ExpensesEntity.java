package com.example.fc_api.domains.expenses.entity;


import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import com.example.fc_api.domains.expenses.model.ExpenseModel;
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
@Entity(name = "expenses")
@Table(name = "expenses")
@Builder
public class ExpensesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Long expense;

    @Column(name = "real_expense_middle_month")
    private Long realExpenseMiddleMonth;

    @Column(name = "real_expense_final_month")
    private Long realExpenseFinalMonth;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoriesEntity category;

    @NotNull
    @Column(name = "reference_date")
    private LocalDate currentDate;

    @NotNull
    private String type;

    public void upsertExpenses(ExpenseModel expenseModel){
        this.name = expenseModel.getName();
        this.description = expenseModel.getDescription();
        this.expense = expenseModel.getExpenses();
        this.realExpenseMiddleMonth = expenseModel.getRealExpenseMiddleMonth();
        this.realExpenseFinalMonth = expenseModel.getRealExpenseFinalMonth();
        this.category = expenseModel.getCategory();
        this.currentDate = expenseModel.getCurrentDate();
        this.type = expenseModel.getType();

    }

    public void deleteExpenses(Long id){
        this.id = id;
    }

}
