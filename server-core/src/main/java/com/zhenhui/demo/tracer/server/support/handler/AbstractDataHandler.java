package com.zhenhui.demo.tracer.server.support.handler;

import com.zhenhui.demo.tracer.domain.Location;
import com.zhenhui.demo.tracer.domain.server.Connection;
import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import com.zhenhui.demo.tracer.server.support.exception.MessageException;
import io.netty.channel.ChannelHandlerContext;

public abstract class AbstractDataHandler extends AbstractHandler<Location> {

    public AbstractDataHandler(ServerConnector connector) {
        super(connector);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Connection connection, Location currPos) throws MessageException.ExecuteFailure {

        try {
            final Location ret = handleLocation(currPos);
            if (ret != null) {
                ctx.fireChannelRead(ret);
            }
        } catch (Throwable e) {
            throw new MessageException.ExecuteFailure("location handle errors.", e);
        }
    }

    protected abstract Location handleLocation(Location position);
}
