package com.zhenhui.demo.tracer.uic.api.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_SUPER
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}

