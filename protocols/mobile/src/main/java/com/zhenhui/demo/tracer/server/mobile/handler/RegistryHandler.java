package com.zhenhui.demo.tracer.server.mobile.handler;

import com.zhenhui.demo.tracer.domain.Command;
import com.zhenhui.demo.tracer.domain.enums.CommandType;
import com.zhenhui.demo.tracer.domain.server.Connection;
import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import com.zhenhui.demo.tracer.server.mobile.message.RegistryMessage;
import com.zhenhui.demo.tracer.server.support.handler.AbstractHandler;
import io.netty.channel.ChannelHandlerContext;

public class RegistryHandler extends AbstractHandler<RegistryMessage> {

    public RegistryHandler(ServerConnector connector) {
        super(connector);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Connection connection, RegistryMessage msg) {

        Command command = new Command();
        command.setType(CommandType.TYPE_CUSTOM);
        command.setDeviceId(msg.deviceId());
        command.getAttributes().put(Command.Attributes.KEY_DATA, "OK");

        connection.send(command);

    }
}
