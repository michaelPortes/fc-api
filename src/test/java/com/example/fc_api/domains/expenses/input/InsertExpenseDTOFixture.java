package com.example.fc_api.domains.expenses.input;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InsertExpenseDTOFixture {
    public static InsertExpenseDTO InsertExpenseDTOFixture(InsertExpenseDTO insertExpenseDTO){
        return new InsertExpenseDTO(
                insertExpenseDTO.getName(),
                insertExpenseDTO.getDescription(),
                insertExpenseDTO.getExpectedExpense(),
                insertExpenseDTO.getRealExpenseMiddleMonth(),
                insertExpenseDTO.getRealExpenseFinalMonth(),
                insertExpenseDTO.getCategory(),
                insertExpenseDTO.getCurrentDate(),
                insertExpenseDTO.getType());
    }
}
