package com.example.fc_api.domains.salary.model;


import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.salary.entity.SalaryEntity;
import com.example.fc_api.domains.salary.input.InsertSalaryDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder(builderClassName = "SalaryModelBuilder", toBuilder = true)
@Getter
public class SalaryModel {

    private Long id;
    private Long salary;
    private LocalDate currentDate;

    public static class SalaryModelBuilder {
        public SalaryModel build()  {
            var salaryModel = new SalaryModel(id, salary, currentDate);
            validate(salaryModel);
            return salaryModel;
        }
    }

    public static void validate(SalaryModel salaryModel){
        // Implement validate;
    }

    public static SalaryModel fromEntity(SalaryEntity salary) throws ModelViolationException{
        return SalaryModel.builder()
                .id(salary.getId())
                .salary(salary.getSalary())
                .currentDate(salary.getCurrentDate())
                .build();
    }

    public static SalaryModel fromInputVariable(InsertSalaryDTO insert) throws ModelViolationException {
        return SalaryModel.builder()
                .salary(insert.getSalary())
                .currentDate(insert.getCurrentDate())
                .build();

    }

    public static SalaryModel deleteVariable(Long delete) throws ModelViolationException{
        return SalaryModel.builder()
                .id(delete)
                .build();
    }
}
