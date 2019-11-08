package com.zhenhui.demo.tracer.security;

public class TokenException extends RuntimeException {

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }
}

