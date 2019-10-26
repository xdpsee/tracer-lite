package com.zhenhui.demo.tracer.server.support.server;

import com.zhenhui.demo.tracer.domain.Command;
import com.zhenhui.demo.tracer.domain.DeviceID;
import com.zhenhui.demo.tracer.domain.server.Connection;
import com.zhenhui.demo.tracer.domain.server.Protocol;
import com.zhenhui.demo.tracer.server.support.utils.ChannelAttributes;
import io.netty.channel.Channel;

public class ConnectionImpl implements Connection {

    private final Channel channel;

    private final Protocol protocol;

    public ConnectionImpl(Channel channel, Protocol protocol) {
        this.channel = channel;
        this.protocol = protocol;
    }

    @Override
    public DeviceID deviceId() {
        return (DeviceID) ChannelAttributes.get(channel, ChannelAttributes.DEVICE_ID);
    }

    @Override
    public Channel channel() {
        return channel;
    }

    @Override
    public Protocol protocol() {
        return protocol;
    }

    @Override
    public void send(Command command) {
        protocol.sendCommand(this, command);
    }

    @Override
    public void write(Object message) {
        channel.writeAndFlush(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Connection that = (Connection) o;
        return channel.id().equals(that.channel().id());
    }

    @Override
    public int hashCode() {
        return this.channel.hashCode();
    }

}
