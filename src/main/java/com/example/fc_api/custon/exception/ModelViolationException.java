package com.example.fc_api.custon.exception;
import lombok.Getter;

import java.util.List;

@Getter
public class ModelViolationException extends Exception {

    private final List<String> fieldErrorMessages;

    public ModelViolationException(String message, List<String> fieldErrorMessages){
        super(message);
        this.fieldErrorMessages = fieldErrorMessages;
    }

    public ModelViolationException(String message, String errorMessages){
        super(message);
        this.fieldErrorMessages = List.of(errorMessages);
    }
}