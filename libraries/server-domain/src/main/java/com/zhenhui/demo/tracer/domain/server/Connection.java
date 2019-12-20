package com.zhenhui.demo.tracer.domain.server;

import com.zhenhui.demo.tracer.domain.Command;
import com.zhenhui.demo.tracer.storage.api.domain.DeviceID;
import io.netty.channel.Channel;

import java.util.concurrent.ExecutionException;

public interface Connection {

    DeviceID deviceId();

    Channel channel();

    Protocol protocol();

    void send(Command command) throws ExecutionException;

    void syncSend(Command command) throws InterruptedException, ExecutionException;

    void write(Object message);

    void syncWrite(Object message) throws InterruptedException, ExecutionException;
}


