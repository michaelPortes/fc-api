package com.example.fc_api.domains.expenses.repository;

import com.example.fc_api.domains.expenses.entity.ExpensesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface ExpensesRepository extends JpaRepository<ExpensesEntity, Long> {

    @Query(
            value = "SELECT * FROM expenses " +
                    "WHERE DATE_TRUNC('month', reference_date) >= DATE_TRUNC('month', CAST(:sixMonthAgo AS DATE)) " +
                    "and DATE_TRUNC('month', reference_date) <= DATE_TRUNC('month', CAST(:currentDate AS DATE))",
            nativeQuery = true
    )
    public Collection<ExpensesEntity> getExpensesListBetweenDates(@Param("sixMonthAgo") LocalDate sixMonthAgo, @Param("currentDate") LocalDate currentDate);

    @Query(
            value = "SELECT * FROM expenses " +
                    "WHERE DATE_TRUNC('month', reference_date) = DATE_TRUNC('month', CAST(:currentDate AS DATE))",
            nativeQuery = true
    )
    public Collection<ExpensesEntity> getExpensesList(@Param("currentDate") LocalDate currentDate);
}
