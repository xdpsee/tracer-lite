package com.zhenhui.demo.tracer.security;

import org.springframework.security.core.AuthenticationException;

public class TokenException extends AuthenticationException {

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }
}

