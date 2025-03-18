package com.example.fc_api.domains.salary.repository;

import com.example.fc_api.domains.salary.entity.SalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
