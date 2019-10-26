package com.zhenhui.demo.tracer.server.support.server;

import com.zhenhui.demo.tracer.domain.server.Connection;
import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import com.zhenhui.demo.tracer.server.support.utils.ChannelAttributes;
import io.netty.channel.*;
import io.netty.channel.socket.DatagramChannel;

public final class PipelineInitializer<C extends Channel> extends ChannelInitializer<C> {

    private final ServerConnector connector;

    public PipelineInitializer(ServerConnector connector) {
        this.connector = connector;
    }

    @Override
    protected void initChannel(C channel) {

        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addFirst("channel-open", new OpenChannelHandler(connector));

        connector.initPipeline(pipeline);
    }

    private class OpenChannelHandler extends ChannelInboundHandlerAdapter {
        private final ServerConnector connector;

        OpenChannelHandler(ServerConnector connector) {
            this.connector = connector;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            if (!(ctx.channel() instanceof DatagramChannel)) {
                final Connection connection = new ConnectionImpl(ctx.channel(), connector.protocol());
                ChannelAttributes.set(ctx, ChannelAttributes.CONNECTION, connection);
            }
        }
    }
}


