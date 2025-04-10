package com.example.fc_api.domains.salary;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.salary.input.InsertSalaryDTO;
import com.example.fc_api.domains.salary.model.SalaryModel;
import com.example.fc_api.domains.salary.presentation.SalaryDTO;
import com.example.fc_api.domains.salary.repository.SalaryDataAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SalaryUseCases {

    private final SalaryDataAccess salaryDataAccess;

    public List<SalaryDTO> getSalaryList(LocalDate currentMonth) throws ModelViolationException {

        var salaryList = salaryDataAccess.getSalaryList(currentMonth);

        return salaryList.stream().map(entity ->
            SalaryDTO.builder()
                    .id(entity.getId())
                    .salary(entity.getSalary())
                    .build()
        ).toList();
    }

    public void insertSalary(InsertSalaryDTO insert) throws ModelViolationException{

        var variable = SalaryModel.fromInputVariable(insert);
        salaryDataAccess.upsertSalary(variable);
    }

   public SalaryDTO deleteSalary(Long variableModel) throws ModelViolationException{

        var delete = salaryDataAccess.deleteSalary(variableModel);

        return SalaryDTO.fromModel(delete).toBuilder().build();

   }
}
