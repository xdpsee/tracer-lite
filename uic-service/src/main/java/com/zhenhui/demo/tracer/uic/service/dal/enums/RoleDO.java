package com.zhenhui.demo.tracer.uic.service.dal.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RoleDO {

    ROLE_USER(1, "NORMAL Test"),
    ROLE_ADMIN(2, ""),
    ROLE_SUPER(3, "SUPER Power"),
    ;

    private long id;
    private String comment;

    RoleDO(long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public static RoleDO valueOf(long id) {
        return Arrays.stream(values())
                .filter(e -> e.id == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("unknown role id : " + id));
    }

}
