package com.zhenhui.demo.tracer.domain.server;


import io.netty.channel.ChannelPipeline;

public interface ServerConnector {

    Configs configs();

    Protocol protocol();

    void initPipeline(ChannelPipeline pipeline);

}


