package com.zhenhui.demo.tracer.common;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceID implements Serializable {

    private static final long serialVersionUID = -1991862317619422230L;

    @NonNull
    private Type type;

    @NonNull
    private String value;

    public static DeviceID fromString(String string) {
        String[] components = string.split(":");
        if (components.length != 2) {
            throw new IllegalArgumentException("invalid DeviceID format!");
        }

        return new DeviceID(Type.valueOf(components[0]), components[1]);
    }

    @Override
    public String toString() {
        return String.format("%s:%s", type, value);
    }

    public enum Type {
        IMEI,
        ;
    }
}

