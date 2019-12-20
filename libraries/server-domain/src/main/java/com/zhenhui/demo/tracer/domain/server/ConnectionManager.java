package com.zhenhui.demo.tracer.domain.server;


import com.zhenhui.demo.tracer.storage.api.domain.DeviceID;

public interface ConnectionManager {

    void registerConnection(Connection connection);

    void unregisterConnection(DeviceID deviceId);

    Connection getConnection(DeviceID deviceId);

}
