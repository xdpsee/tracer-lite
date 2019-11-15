package com.zhenhui.demo.tracer.udm.api.exception;

public enum ErrorCode {
    USER_NOT_FOUND(""),
    DEVICE_NOT_FOUND( ""),
    ;

    public final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
