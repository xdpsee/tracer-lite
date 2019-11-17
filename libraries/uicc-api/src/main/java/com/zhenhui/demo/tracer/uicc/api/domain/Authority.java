package com.zhenhui.demo.tracer.uicc.api.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    COMMAND_SEND,
    SET_DEVICE_NAME,
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}

