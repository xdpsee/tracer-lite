package com.zhenhui.demo.tracer.webapi.security;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority implements GrantedAuthority {
    NORMAL,
    ADMIN
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}
