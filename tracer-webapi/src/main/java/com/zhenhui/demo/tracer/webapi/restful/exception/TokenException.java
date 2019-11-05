package com.zhenhui.demo.tracer.webapi.restful.exception;

public class TokenException extends RuntimeException {

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }
}

