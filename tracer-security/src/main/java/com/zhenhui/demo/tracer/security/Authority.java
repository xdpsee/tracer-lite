package com.zhenhui.demo.tracer.security;

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

