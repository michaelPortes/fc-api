package com.example.fc_api.domains.result.presentation;


import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class DefaultResultDTO {
    private double expectedExpenses;
    private double actualExpenses;
    private double variance;
    private double actualSurpluses;
    private double expectedSurpluses;

}
