package com.zhenhui.demo.tracer.server.support.server;

import com.google.common.collect.MapMaker;
import com.zhenhui.demo.tracer.domain.DeviceID;
import com.zhenhui.demo.tracer.domain.server.Connection;
import com.zhenhui.demo.tracer.domain.server.ConnectionManager;

import java.util.concurrent.ConcurrentMap;

public class LocalConnectionManager implements ConnectionManager {

    private ConcurrentMap<DeviceID, Connection> connectionMap = new MapMaker()
            .initialCapacity(1024)
            .concurrencyLevel(16)
            .weakValues()
            .makeMap();

    public void registerConnection(DeviceID deviceId, Connection connection) {
        connectionMap.put(deviceId, connection);
    }

    public void unregisterConnection(DeviceID deviceId) {
        connectionMap.remove(deviceId);
    }

    public Connection getConnection(DeviceID deviceId) {
        return connectionMap.get(deviceId);
    }

}
