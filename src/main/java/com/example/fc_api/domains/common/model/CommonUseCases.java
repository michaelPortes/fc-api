package com.example.fc_api.domains.common.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CommonUseCases {

    public LocalDate getReferenceDate(LocalDate date){
        return (date != null) ? date : LocalDate.now();
    }
}
