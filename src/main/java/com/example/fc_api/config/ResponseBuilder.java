package com.example.fc_api.config;

import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@Setter
public class ResponseBuilder<T> {

    private final HttpStatusCode httpStatusCode;
    private String message = null;
    private T data;

    public ResponseBuilder(HttpStatusCode httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public ResponseBuilder(HttpStatusCode httpStatusCode, T data) {
        this.httpStatusCode = httpStatusCode;
        this.data = data;
    }

    public ResponseBuilder(HttpStatusCode httpStatusCode, String message, T data) {
        this.httpStatusCode = httpStatusCode;
        this.data = data;
        this.message = message;
    }

    public ResponseEntity<ResponseBody<T>> build(){
        String message = getMessage();

        return new ResponseEntity<>(
                new ResponseBody<T>(this.httpStatusCode, message, this.data),
                this.httpStatusCode
        );
    }

    private String getMessage() {
        return Optional.ofNullable(this.message).orElse(
                httpStatusCode.toString().replaceFirst("\\d{1,3}+\\s(.*)", "$1")
        );
    }
}

