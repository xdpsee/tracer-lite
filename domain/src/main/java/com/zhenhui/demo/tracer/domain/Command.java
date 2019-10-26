package com.zhenhui.demo.tracer.domain;


import com.zhenhui.demo.tracer.domain.enums.CommandType;
import com.zhenhui.demo.tracer.domain.utils.AttributesOp;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Command {

    private CommandType type;

    private DeviceID deviceId;

    private Attributes attributes = new Attributes();

    /**
     * 扩展属性定义
     */
    public static class Attributes extends AttributesOp {

        public static final String KEY_UNIQUE_ID = "unique-id";
        public static final String KEY_FREQUENCY = "frequency";
        public static final String KEY_TIMEZONE = "timezone";
        public static final String KEY_DEVICE_PASSWORD = "device-password";
        public static final String KEY_RADIUS = "radius";
        public static final String KEY_MESSAGE = "message";
        public static final String KEY_PHONE = "phone";
        public static final String KEY_DATA = "data";

    }
}

