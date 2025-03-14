package com.example.fc_api.domains.fixed_expenses.repository;

import com.example.fc_api.domains.fixed_expenses.entity.FixedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface FixedRepository extends JpaRepository<FixedEntity, Long> {

    @Query(
        value = "select * from fixed where date = :#{#currentDate}",
            nativeQuery = true
    )
    public Collection<FixedEntity> getFixedList(LocalDate currentDate);
}
