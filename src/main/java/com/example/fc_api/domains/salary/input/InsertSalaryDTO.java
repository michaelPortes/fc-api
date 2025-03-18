package com.example.fc_api.domains.salary.input;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder(toBuilder = true)
public class InsertSalaryDTO {
    private Long salary;
    private LocalDate currentDate;
}
