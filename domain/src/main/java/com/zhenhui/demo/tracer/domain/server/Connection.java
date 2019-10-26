package com.zhenhui.demo.tracer.domain.server;

import com.zhenhui.demo.tracer.domain.Command;
import com.zhenhui.demo.tracer.domain.DeviceID;
import com.zhenhui.demo.tracer.domain.server.Protocol;
import io.netty.channel.Channel;

public interface Connection {

    DeviceID deviceId();

    Channel channel();

    Protocol protocol();

    void send(Command command);

    void write(Object message);
}


