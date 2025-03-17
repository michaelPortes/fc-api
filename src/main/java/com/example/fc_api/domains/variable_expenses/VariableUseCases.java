package com.example.fc_api.domains.variable_expenses;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.variable_expenses.input.InsertVariableDTO;
import com.example.fc_api.domains.variable_expenses.model.VariableModel;
import com.example.fc_api.domains.variable_expenses.presentation.VariableDTO;
import com.example.fc_api.domains.variable_expenses.repository.VariableDataAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VariableUseCases {

    private final VariableDataAccess variableDataAccess;

    public List<VariableDTO> getVariableList(LocalDate currentMonth) throws ModelViolationException {

        var variableList = variableDataAccess.getVariableList(currentMonth);

        return variableList.stream().map(entity ->
            VariableDTO.builder()
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .expectedExpense(entity.getExpectedExpense())
                    .realExpenseMiddleMonth(entity.getRealExpenseMiddleMonth())
                    .realExpenseFinalMonth(entity.getRealExpenseFinalMonth())
                    .build()
        ).toList();
    }

    public VariableDTO insertVariable(InsertVariableDTO insert) throws ModelViolationException{

        var variable = VariableModel.fromInputVariable(insert);
        var responseData = variableDataAccess.upsertVariable(variable);

        return VariableDTO.fromModel(responseData).toBuilder().build();
    }

   public VariableDTO deleteVariable(Long variableModel) throws ModelViolationException{

        var delete = variableDataAccess.deleteVariable(variableModel);

        return VariableDTO.fromModel(delete).toBuilder().build();

   }
}
