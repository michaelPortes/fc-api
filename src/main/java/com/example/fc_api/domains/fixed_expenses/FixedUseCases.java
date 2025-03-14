package com.example.fc_api.domains.fixed_expenses;

import com.example.fc_api.domains.categories.presentation.CategoriesDTO;
import com.example.fc_api.domains.fixed_expenses.model.FixedModel;
import com.example.fc_api.domains.fixed_expenses.presentation.FixedDTO;
import com.example.fc_api.domains.fixed_expenses.repository.FixedDataAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FixedUseCases {
    private final FixedDataAccess fixedDataAccess;

    public List<FixedDTO> getFixedList(LocalDate currentMonth){

        var fixeModel = fixedDataAccess.getFixedList(currentMonth);
        return null;
    }
}
