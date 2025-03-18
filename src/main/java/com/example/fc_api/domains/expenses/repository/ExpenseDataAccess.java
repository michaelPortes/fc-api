package com.example.fc_api.domains.expenses.repository;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.expenses.entity.ExpensesEntity;
import com.example.fc_api.domains.expenses.model.ExpenseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExpenseDataAccess {

    private final ExpensesRepository expensesRepository;

    public List<ExpenseModel> getExpensesList(LocalDate currentDate, String expensesType) throws ModelViolationException{
        return expensesRepository.getExpensesList(currentDate, expensesType).stream().map(entity -> {
            try {
                return ExpenseModel.fromEntity(entity);
            } catch (ModelViolationException e){
                throw new IllegalArgumentException(e);
            }
        }).toList();
    }

    public ExpenseModel upsertExpenses(ExpenseModel expenseModel) throws ModelViolationException{

        ExpensesEntity expensesEntity;

        expensesEntity = ExpensesEntity.builder().build();
        expensesEntity.upsertExpenses(expenseModel);

        var createFixed = expensesRepository.save(expensesEntity);

        return ExpenseModel.fromEntity(createFixed);
    }

    public ExpenseModel deleteExpenses(Long id) throws ModelViolationException{

        ExpensesEntity expensesEntity;

        expensesEntity = ExpensesEntity.builder().build();
        expensesEntity.deleteExpenses(id);

        expensesRepository.delete(expensesEntity);

        return ExpenseModel.deleteExpenses(id);
    }
}
