package com.zhenhui.demo.tracer.udm.api.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class BindingResult implements Serializable {

    private static final long serialVersionUID = -6932202732716242708L;

    public enum Error {
        USER_NOT_FOUND(1, ""),
        DEVICE_NOT_FOUND(2, ""),
        DEVICE_BOUND(3, ""),
        ;

        public final int code;
        public final String message;

        Error(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    private boolean success;

    private Error error;
}
