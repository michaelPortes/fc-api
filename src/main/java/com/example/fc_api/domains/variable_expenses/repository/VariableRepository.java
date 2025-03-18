package com.example.fc_api.domains.variable_expenses.repository;

import com.example.fc_api.domains.variable_expenses.entity.VariableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface VariableRepository extends JpaRepository<VariableEntity, Long> {

    @Query(
            value = "SELECT * FROM variable_expenses ve " +
                    "WHERE DATE_TRUNC('month', reference_date) = DATE_TRUNC('month', CAST(:currentDate AS DATE));",
                nativeQuery = true
    )
    public Collection<VariableEntity> getVariableList(@Param("currentDate") LocalDate currentDate);
}
