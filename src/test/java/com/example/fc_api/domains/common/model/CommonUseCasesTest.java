package com.example.fc_api.domains.common.model;

import java.time.LocalDate;

public class CommonUseCasesTest {
    public static LocalDate testGetCurrentDate(){
        return LocalDate.parse("2025-03-01");
    }
}
