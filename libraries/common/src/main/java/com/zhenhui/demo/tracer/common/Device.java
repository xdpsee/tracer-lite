package com.zhenhui.demo.tracer.common;

import com.zhenhui.demo.tracer.common.utils.AttributesOp;
import lombok.Data;

import java.io.Serializable;

@Data
public class Device implements Serializable {

    private static final long serialVersionUID = 3725804156008769747L;

    private DeviceID deviceId;

    private String caption;

    private Attributes attributes = new Attributes();

    /**
     * 扩展属性定义
     */
    public static class Attributes extends AttributesOp {

        public static final String KEY_DOUBLE_SPEED_LIMIT = "speed.limit";

        public static final String KEY_MAINTENANCE_START = "maintenance.start";

        public static final String KEY_MAINTENANCE_INTERVAL = "maintenance.interval";

        public static final String KEY_LONG_EXPIRE_AT = "expireAt";

    }

}


