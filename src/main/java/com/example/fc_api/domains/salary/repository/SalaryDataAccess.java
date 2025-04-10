package com.example.fc_api.domains.salary.repository;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.salary.entity.SalaryEntity;
import com.example.fc_api.domains.salary.model.SalaryModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SalaryDataAccess {

    private final SalaryRepository salaryRepository;

    public List<SalaryModel> getSalaryList(LocalDate currentDate){
        return salaryRepository.getSalaryList(currentDate).stream().map(entity -> {
            try {
                return SalaryModel.fromEntity(entity);
            } catch (ModelViolationException e){
                throw new IllegalArgumentException(e);
            }
        }).toList();
    }

    @Transactional
    public void upsertSalary(SalaryModel salaryModel) throws ModelViolationException{

        var getSalary = getSalaryList(salaryModel.getCurrentDate());

        if(getSalary.isEmpty()){
            SalaryEntity salaryEntity;

            salaryEntity = SalaryEntity.builder().build();
            salaryEntity.upsertVariable(salaryModel);

            salaryRepository.save(salaryEntity);
        }else{

            salaryRepository.updateSalaryExist(salaryModel.getCurrentDate(), BigInteger.valueOf(salaryModel.getSalary()));
        }
    }

    public List<SalaryModel> getSalaryBetweenDates(LocalDate sixMonthAgo, LocalDate currentDate){
        return salaryRepository.getSalaryListBetweenDates(sixMonthAgo, currentDate).stream().map(entity -> {
            try {
                return SalaryModel.fromEntity(entity);
            } catch (ModelViolationException e){
                throw new IllegalArgumentException(e);
            }
        }).toList();
    }

    public SalaryModel deleteSalary(Long id) throws ModelViolationException{

        SalaryEntity salaryEntity;

        salaryEntity = SalaryEntity.builder().build();
        salaryEntity.deleteVariable(id);

        salaryRepository.delete(salaryEntity);

        return SalaryModel.deleteVariable(id);
    }
}
