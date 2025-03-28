package com.example.fc_api.controller.param;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class SalaryPostParam {
    private Long salary;
    private LocalDate currentDate;
}
