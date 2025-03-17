package com.example.fc_api.domains.variable_expenses.repository;

import com.example.fc_api.domains.variable_expenses.entity.VariableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface VariableRepository extends JpaRepository<VariableEntity, Long> {

    @Query(
            value = "select * from variable_expenses where reference_date = :#{#currentDate}",
                nativeQuery = true
    )
    public Collection<VariableEntity> getVariableList(LocalDate currentDate);
}
