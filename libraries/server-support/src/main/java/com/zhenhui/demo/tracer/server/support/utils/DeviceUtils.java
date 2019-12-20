package com.zhenhui.demo.tracer.server.support.utils;

import com.zhenhui.demo.tracer.server.support.exception.DeviceException;
import com.zhenhui.demo.tracer.storage.api.domain.Device;

public class DeviceUtils {

    public static void checkExpired(Device device) throws DeviceException.Expired {

        final Long expireAtMillis = device.getAttributes().getLong(Device.Attributes.KEY_LONG_EXPIRE_AT);
        if (expireAtMillis != null && expireAtMillis < System.currentTimeMillis()) {
            throw new DeviceException.Expired();
        }

    }

    public static Double getDoubleAttr(Device device, String key) {
        return device.getAttributes().getDouble(key);
    }

    public static double getDoubleAttr(Device device, String key, double defaultValue) {

        Double v = device.getAttributes().getDouble(key);
        if (null == v) {
            return defaultValue;
        }

        return v;
    }
}
















