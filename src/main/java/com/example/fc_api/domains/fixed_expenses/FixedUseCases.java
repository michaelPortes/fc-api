package com.example.fc_api.domains.fixed_expenses;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.fixed_expenses.presentation.FixedDTO;
import com.example.fc_api.domains.fixed_expenses.repository.FixedDataAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FixedUseCases {
    private final FixedDataAccess fixedDataAccess;

    public List<FixedDTO> getFixedList(LocalDate currentMonth) throws ModelViolationException {

        var fixedList = fixedDataAccess.getFixedList(currentMonth);

        return fixedList.stream().map(entity ->
            FixedDTO.builder()
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .expectedExpense(entity.getExpectedExpense())
                    .realExpenseMiddleMonth(entity.getRealExpenseMiddleMonth())
                    .realExpenseFinalMonth(entity.getRealExpenseFinalMonth())
                    .build()
        ).toList();
    }
}
