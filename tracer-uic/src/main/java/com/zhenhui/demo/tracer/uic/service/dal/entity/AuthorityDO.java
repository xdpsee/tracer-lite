package com.zhenhui.demo.tracer.uic.service.dal.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AuthorityDO {
    COMMAND_SEND(1, "Send Command"),
    SET_DEVICE_NAME(2, "Change Device Name")
    ;

    private long id;
    private String comment;

    AuthorityDO(long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public static AuthorityDO valueOf(long id) {
        return Arrays.stream(values())
                .filter(e -> e.id == id)
                .findAny()
                .orElseThrow(()->new IllegalArgumentException("unknown authority id : " + id));
    }
}


