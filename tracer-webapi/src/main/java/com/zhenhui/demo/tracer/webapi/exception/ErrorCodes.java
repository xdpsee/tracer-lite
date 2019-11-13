package com.zhenhui.demo.tracer.webapi.exception;

public enum ErrorCodes {
    SUCCESS(200, "OK"),

    DEVICE_NOT_FOUND(100001, "设备不存在"),
    ;

    public final int code;
    public final String comment;

    ErrorCodes(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }
}
