package com.example.fc_api.helper;

public enum MessageCodes {
    UNKNOWN_ERROR("{server.unknown.error}"),
    SERVER_EXCEPTION_MODEL_VIOLATION("{server.exception.modelViolation}"),
    SERVER_EXCEPTION_RESOURCE_NOT_FOUND("{server.exception.resource.not.found}"),
    SECURITY_INVALID_TOKEN("{security.invalid.token}"),
    SECURITY_NOT_AUTHORIZED("{security.not.authorized}"),
    SECURITY_ERROR_TOKEN_GENERATE("{security.error.token.generate}"),
    DASHBOARD_SHIFT_SETTING_MESSAGE("{dashboard.shift.setting.message}");

    private final String messageCode;

    public String getMessageCode() {
        return this.messageCode;
    }

    MessageCodes(String messageCode) {
        this.messageCode = messageCode;
    }


}
