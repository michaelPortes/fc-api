package com.example.fc_api.domains.result.presentation;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class PercentageDTO {
    private double investment;
    private double investmentValue;
    private double fixed;
    private double fixedValue;
    private double variable;
    private double variableValue;
}
