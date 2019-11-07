package com.zhenhui.demo.tracer.webapp.security;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {

    NORMAL,
    SUPER,
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}

