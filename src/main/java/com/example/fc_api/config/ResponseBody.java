package com.example.fc_api.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBody<T> {
    private final Long timestamp;
    private final T data;
    private final String message;
    private final int statusCode;

    public ResponseBody(HttpStatusCode httpStatusCode, String message, T data) {
        this.statusCode = httpStatusCode.value();
        this.timestamp = (new Date()).getTime();
        this.message = message;
        this.data = data;
    }
}
