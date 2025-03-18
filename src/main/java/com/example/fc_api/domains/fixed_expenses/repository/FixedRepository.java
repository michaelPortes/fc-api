package com.example.fc_api.domains.fixed_expenses.repository;

import com.example.fc_api.domains.fixed_expenses.entity.FixedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface FixedRepository extends JpaRepository<FixedEntity, Long> {

    @Query(
            value = "SELECT * FROM fixed_expenses " +
                    "WHERE DATE_TRUNC('month', reference_date) = DATE_TRUNC('month', CAST(:currentDate AS DATE))",
            nativeQuery = true
    )
    public Collection<FixedEntity> getFixedList(@Param("currentDate") LocalDate currentDate);
}
