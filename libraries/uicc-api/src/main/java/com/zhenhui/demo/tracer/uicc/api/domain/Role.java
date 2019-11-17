package com.zhenhui.demo.tracer.uicc.api.domain;

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

