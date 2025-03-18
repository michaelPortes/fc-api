package com.example.fc_api.domains.salary.entity;


import com.example.fc_api.domains.salary.model.SalaryModel;
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
@Table(name = "salary")
@Builder
public class SalaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long salary;

    @NotNull
    @Column(name = "reference_date")
    private LocalDate currentDate;

    public void upsertVariable(SalaryModel salaryModel){
        this.salary = salaryModel.getSalary();
        this.currentDate = salaryModel.getCurrentDate();

    }

    public void deleteVariable(Long id){
        this.id = id;
    }

}
