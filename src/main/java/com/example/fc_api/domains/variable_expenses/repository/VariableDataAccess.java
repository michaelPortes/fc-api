package com.example.fc_api.domains.variable_expenses.repository;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.variable_expenses.entity.VariableEntity;
import com.example.fc_api.domains.variable_expenses.model.VariableModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VariableDataAccess {

    private final VariableRepository variableRepository;

    public List<VariableModel> getVariableList(LocalDate currentDate) throws ModelViolationException{
        return variableRepository.getVariableList(currentDate).stream().map(entity -> {
            try {
                return VariableModel.fromEntity(entity);
            } catch (ModelViolationException e){
                throw new IllegalArgumentException(e);
            }
        }).toList();
    }

    public VariableModel upsertVariable(VariableModel variableModel) throws ModelViolationException{

        VariableEntity variableEntity;

        variableEntity = VariableEntity.builder().build();
        variableEntity.upsertVariable(variableModel);

        var createVariable = variableRepository.save(variableEntity);

        return VariableModel.fromEntity(createVariable);
    }

    public VariableModel deleteVariable(Long id) throws ModelViolationException{

        VariableEntity variableEntity;

        variableEntity = VariableEntity.builder().build();
        variableEntity.deleteVariable(id);

        variableRepository.delete(variableEntity);

        return VariableModel.deleteVariable(id);
    }
}
