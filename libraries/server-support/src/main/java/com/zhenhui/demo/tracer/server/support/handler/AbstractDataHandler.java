package com.zhenhui.demo.tracer.server.support.handler;

import com.zhenhui.demo.tracer.domain.server.Connection;
import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import com.zhenhui.demo.tracer.server.support.exception.MessageException;
import com.zhenhui.demo.tracer.server.support.server.DataMessage;
import io.netty.channel.ChannelHandlerContext;

public abstract class AbstractDataHandler extends AbstractHandler<DataMessage> {

    public AbstractDataHandler(ServerConnector connector) {
        super(connector);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Connection connection, DataMessage message) throws MessageException.ExecuteFailure {

        try {
            final DataMessage ret = handleMessage(message);
            if (ret != null) {
                ctx.fireChannelRead(ret);
            }
        } catch (Throwable e) {
            throw new MessageException.ExecuteFailure("location handle errors.", e);
        }
    }

    protected abstract DataMessage handleMessage(DataMessage message);
}
