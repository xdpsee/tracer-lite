package com.zhenhui.demo.tracer.server.mobile;

import com.zhenhui.demo.tracer.domain.server.Protocol;
import com.zhenhui.demo.tracer.server.mobile.codec.MobileFrameDecoder;
import com.zhenhui.demo.tracer.server.mobile.codec.MobileProtocolDecoder;
import com.zhenhui.demo.tracer.server.mobile.codec.MobileProtocolEncoder;
import com.zhenhui.demo.tracer.server.mobile.handler.RegistryHandler;
import com.zhenhui.demo.tracer.server.support.handler.DefaultDataHandler;
import com.zhenhui.demo.tracer.server.support.handler.event.AlertEventHandler;
import com.zhenhui.demo.tracer.server.support.server.AbstractConnector;
import com.zhenhui.demo.tracer.server.support.server.ServerContext;
import io.netty.channel.ChannelPipeline;

public class MobileConnector extends AbstractConnector {

    public MobileConnector(ServerContext context) {
        super(context);
    }

    @Override
    public Protocol protocol() {
        return new MobileProtocol();
    }

    @Override
    public void initPipeline(ChannelPipeline pipeline) {
        pipeline.addLast("frameDecoder", new MobileFrameDecoder())
                .addLast("protocolDecoder", new MobileProtocolDecoder())
                .addLast("registryHandler", new RegistryHandler(this))
                .addLast("dataHandler", new DefaultDataHandler(this))
                .addLast("eventHandler", new AlertEventHandler(this));

        pipeline.addLast("protocolEncoder", new MobileProtocolEncoder());
    }
}

