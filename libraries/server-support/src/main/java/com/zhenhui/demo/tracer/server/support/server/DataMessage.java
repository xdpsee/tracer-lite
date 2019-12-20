package com.zhenhui.demo.tracer.server.support.server;

import com.zhenhui.demo.tracer.domain.Message;
import com.zhenhui.demo.tracer.storage.api.domain.DeviceID;
import com.zhenhui.demo.tracer.storage.api.domain.Location;
import lombok.Getter;

public final class DataMessage implements Message {

    @Getter
    private final Location location;

    public DataMessage(Location location) {
        this.location = location;
    }

    @Override
    public DeviceID deviceId() {
        return location.getDeviceId();
    }

    @Override
    public byte[] rawBytes() {
        return new byte[0];
    }
}
