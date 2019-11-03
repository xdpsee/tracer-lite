package com.zhenhui.demo.tracer.domain.server;

import com.zhenhui.demo.tracer.domain.DeviceID;

public interface ConnectionManager {

    void registerConnection(DeviceID deviceId, Connection connection);

    void unregisterConnection(DeviceID deviceId);

    Connection getConnection(DeviceID deviceId);

}
