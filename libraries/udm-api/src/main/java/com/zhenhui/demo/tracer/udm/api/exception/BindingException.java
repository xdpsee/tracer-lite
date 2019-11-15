package com.zhenhui.demo.tracer.udm.api.exception;

import lombok.Getter;

public class BindingException extends Exception {

    @Getter
    private final ErrorCode errorCode;

    public BindingException(ErrorCode errorCode) {
        super(errorCode.message);
        this.errorCode = errorCode;
    }

}
