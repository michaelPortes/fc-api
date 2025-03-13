package com.example.fc_api.helper;

public enum MessageCodes {
    UNKNOWN_ERROR("{server.unknown.error}"),
    SERVER_EXCEPTION_MODEL_VIOLATION("{server.exception.modelViolation}"),
    SERVER_EXCEPTION_RESOURCE_NOT_FOUND("{server.exception.resource.not.found}"),
    DASHBOARD_PLANE_SERIAL_NUMBER_REQUIRED("{dashboard.plane.serialNumber.require}"),
    DASHBOARD_PLANE_MODEL_REQUIRED("{dashboard.plane.model.required}"),
    DASHBOARD_DOCUMENT_TYPE_INVALID("{dashboard.document.type.invalid}"),
    DASHBOARD_AIRCRAFT_NOT_FOUND("{dashboard.aircraft.not.found}"),
    DASHBOARD_STATION_NOT_FOUND("{dashboard.station.not.found}"),
    DASHBOARD_WORKFORCE_NOT_FOUND("{dashboard.workforce.not.found}"),
    DASHBOARD_SHIFT_PLAN_NOT_FOUND("{dashboard.shiftPlan.not.found}"),
    DASHBOARD_TAKT_NOT_FOUND("{dashboard.takt.not.found}"),
    DASHBOARD_WORKFORCE_DATE_OUT_OF_RANGE("{dashboard.workforce.out_of_range}"),
    DASHBOARD_WORKFORCE_ALREADY_EXISTS("{dashboard.workforce.already_exists}"),
    DASHBOARD_WORKFORCE_INVALID_ID("{dashboard.workforce.invalid_id}"),
    DASHBOARD_POSITION_INVALID_ID("{dashboard.position.invalid_id}"),
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
