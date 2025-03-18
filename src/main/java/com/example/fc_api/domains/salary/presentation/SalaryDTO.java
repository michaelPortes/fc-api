package com.example.fc_api.domains.salary.presentation;

import com.example.fc_api.domains.salary.model.SalaryModel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;


@Builder(toBuilder = true)
@Getter
public class SalaryDTO {

    private Long id;
    private Long salary;

    public static SalaryDTO fromModel(SalaryModel salaryModel){
        return SalaryDTO.builder()
                .id(salaryModel.getId())
                .salary(salaryModel.getSalary())
                .build();
    }
}
