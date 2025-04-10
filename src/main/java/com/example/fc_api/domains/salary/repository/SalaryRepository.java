package com.example.fc_api.domains.salary.repository;

import com.example.fc_api.domains.salary.entity.SalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryEntity, Long> {

    @Query(
            value = "SELECT * FROM salary " +
                    "WHERE DATE_TRUNC('month', reference_date) = date_trunc('month', CAST(:currentDate AS DATE));",
                nativeQuery = true
    )
    public Collection<SalaryEntity> getSalaryList(@Param("currentDate") LocalDate currentDate);

    @Query(
            value = "select * from salary " +
                    "WHERE DATE_TRUNC('month', reference_date) >= DATE_TRUNC('month', CAST(:sixMonthAgo AS DATE)) " +
                    "and DATE_TRUNC('month', reference_date) <= DATE_TRUNC('month', CAST(:currentDate AS DATE))",
            nativeQuery = true
    )
    public Collection<SalaryEntity> getSalaryListBetweenDates(@Param("sixMonthAgo") LocalDate sixMonthAgo, @Param("currentDate") LocalDate currentDate);

    @Modifying
    @Query(
        value = "update salary set salary = :salary where DATE_TRUNC('month', CAST(reference_date AS DATE)) = DATE_TRUNC('month', CAST(:currentMonth AS DATE))",
            nativeQuery = true
    )
    public void updateSalaryExist(@Param("currentMonth") LocalDate currentMonth, @Param("salary") BigInteger salary);

}
