package com.example.fc_api.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageHelper {

    private final MessageSource messageSource;
    private final String messageCodeRegMatch = "^\\{([^{}]+)}$";

    public boolean isMessageCode(String messageCode) {
        if(messageCode.isBlank()) return false;
        return messageCode.matches(messageCodeRegMatch);
    }

    public String getMessage(String messageCode) {
        Locale locale = LocaleContextHolder.getLocale();
        String plainMessageCode = messageCode.replaceAll(messageCodeRegMatch, "$1");
        return this.messageSource.getMessage(plainMessageCode,null, locale );
    }
}
