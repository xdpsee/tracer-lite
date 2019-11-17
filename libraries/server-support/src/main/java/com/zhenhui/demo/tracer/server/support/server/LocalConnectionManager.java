package com.zhenhui.demo.tracer.server.support.server;

import com.google.common.collect.MapMaker;
import com.zhenhui.demo.tracer.common.DeviceID;
import com.zhenhui.demo.tracer.domain.server.Connection;
import com.zhenhui.demo.tracer.domain.server.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentMap;

@Component
public class LocalConnectionManager implements ConnectionManager {

    private static final Logger logger = LoggerFactory.getLogger(LocalConnectionManager.class);

    private ConcurrentMap<DeviceID, Connection> connectionMap = new MapMaker()
            .initialCapacity(1024)
            .concurrencyLevel(16)
            .weakValues()
            .makeMap();

    public void registerConnection(Connection connection) {
        connectionMap.put(connection.deviceId(), connection);
        logger.info("[ConnectionManager] connection created: " + connection.deviceId().toString());
    }

    public void unregisterConnection(DeviceID deviceId) {
        connectionMap.remove(deviceId);
        logger.info("[ConnectionManager] connection closed: " + deviceId.toString());
    }

    public Connection getConnection(DeviceID deviceId) {
        return connectionMap.get(deviceId);
    }

}
