package com.zhenhui.demo.tracer.webapi.exception;

public class ServiceException extends RuntimeException {

    public final ErrorCodes errorCode;

    public ServiceException(ErrorCodes errorCode) {
        super(errorCode.comment);
        this.errorCode = errorCode;
    }
}

