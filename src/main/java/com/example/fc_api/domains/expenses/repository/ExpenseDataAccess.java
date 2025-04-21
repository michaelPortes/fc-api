package com.example.fc_api.domains.expenses.repository;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.expenses.entity.ExpensesEntity;
import com.example.fc_api.domains.expenses.model.ExpenseModel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExpenseDataAccess {

    private final ExpensesRepository expensesRepository;

    public List<ExpenseModel> getExpenses(LocalDate currentDate) throws ModelViolationException{
        return expensesRepository.getExpensesList(currentDate).stream().map(entity -> {
            try {
                return ExpenseModel.fromEntity(entity);
            } catch (ModelViolationException e){
                throw new IllegalArgumentException(e);
            }
        }).toList();
    }

    public List<ExpenseModel> getExpensesListBetweenDates(LocalDate sixMonthAgo, LocalDate currentDate) throws ModelViolationException{
        return expensesRepository.getExpensesListBetweenDates(sixMonthAgo, currentDate).stream().map(entity -> {
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

    @SneakyThrows
    public Optional<ExpenseModel> findById(Long id) {
        return expensesRepository.findById(id)
                .map(entity -> {
                    try {
                        return ExpenseModel.fromEntity(entity);
                    } catch (ModelViolationException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public ExpenseModel deleteExpenses(Long id) throws ModelViolationException{

        ExpensesEntity expensesEntity;

        expensesEntity = ExpensesEntity.builder().build();
        expensesEntity.deleteExpenses(id);

        expensesRepository.delete(expensesEntity);

        return ExpenseModel.deleteExpenses(id);
    }
}
