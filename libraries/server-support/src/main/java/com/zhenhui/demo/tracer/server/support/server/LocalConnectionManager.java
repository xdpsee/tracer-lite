package com.zhenhui.demo.tracer.server.support.server;

import com.zhenhui.demo.tracer.storage.api.domain.DeviceID;
import com.zhenhui.demo.tracer.domain.server.Connection;
import com.zhenhui.demo.tracer.domain.server.ConnectionManager;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

@Component
public class LocalConnectionManager implements ConnectionManager {

    private static final Logger logger = LoggerFactory.getLogger(LocalConnectionManager.class);

    private final HashMap<DeviceID, WeakReference<Channel>> deviceChannelMap = new HashMap<>(1024);

    private final WeakHashMap<Channel, Connection> channelConnectionMap = new WeakHashMap<>(1024);

    public synchronized void registerConnection(Connection connection) {
        logger.info("[ConnectionManager] connection created: " + connection.deviceId().toString());

        channelConnectionMap.put(connection.channel(), connection);

        final DeviceID deviceId = connection.deviceId();
        if (deviceId != null) {
            deviceChannelMap.put(deviceId, new WeakReference<>(connection.channel()));
        }
    }

    public synchronized void unregisterConnection(DeviceID deviceId) {
        logger.info("[ConnectionManager] connection closed: " + deviceId.toString());

        final WeakReference<Channel> reference = deviceChannelMap.get(deviceId);
        final Channel channel = reference.get();
        if (channel != null) {
            deviceChannelMap.remove(deviceId);
            channelConnectionMap.remove(channel);
        }
    }

    public synchronized Connection getConnection(DeviceID deviceId) {

        final WeakReference<Channel> reference = deviceChannelMap.get(deviceId);
        final Channel channel = reference.get();
        if (channel != null) {
            return channelConnectionMap.get(channel);
        }

        return null;
    }

}
