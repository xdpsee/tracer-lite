package com.zhenhui.demo.tracer.server.mobile.message;


import com.zhenhui.demo.tracer.common.DeviceID;
import com.zhenhui.demo.tracer.domain.Message;

import java.nio.charset.Charset;

public final class RegistryMessage implements Message {

    private final String raw;

    private final DeviceID deviceId;

    public RegistryMessage(String body, DeviceID deviceId) {
        this.raw = body;
        this.deviceId = deviceId;
    }
    
    @Override
    public DeviceID deviceId() {
        return deviceId;
    }

    @Override
    public byte[] rawBytes() {
        return raw.getBytes(Charset.forName("UTF-8"));
    }
}
