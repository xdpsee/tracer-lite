package com.zhenhui.demo.tracer.server.support.server;

import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public final class PipelineInitializer<C extends Channel> extends ChannelInitializer<C> {

    private final ServerConnector connector;

    public PipelineInitializer(ServerConnector connector) {
        this.connector = connector;
    }

    @Override
    protected void initChannel(C channel) {

        ChannelPipeline pipeline = channel.pipeline();
        connector.initPipeline(pipeline);
    }

}


