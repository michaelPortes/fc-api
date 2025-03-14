package com.example.fc_api.domains.fixed_expenses.repository;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.fixed_expenses.model.FixedModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FixedDataAccess {
    private final FixedRepository fixedRepository;

    public List<FixedModel> getFixedList(LocalDate currentDate){
        return fixedRepository.getFixedList(currentDate).stream().map(entity -> {
            try {
                return FixedModel.fromEntity(entity);
            } catch (ModelViolationException e){
                throw new IllegalArgumentException(e);
            }
        }).toList();
    }
}
