package com.zhenhui.demo.tracer.server.support.server;

import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import com.zhenhui.demo.tracer.domain.server.TraceServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DefaultTraceServer implements TraceServer {

    private static final Logger logger = LoggerFactory.getLogger(DefaultTraceServer.class);

    private final ServerConnector connector;

    private ServerBootstrap bootstrap;

    private Channel channel;

    public DefaultTraceServer(ServerConnector connector) {
        this.connector = connector;
    }

    @Override
    public void listen(int port, String host) {

        if (bootstrap != null) {
            throw new IllegalStateException("Already started");
        }

        EventLoopGroup acceptorGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.group(acceptorGroup, workerGroup);
        bootstrap.childHandler(new PipelineInitializer<>(connector));

        channel = bootstrap.bind(host, port).syncUninterruptibly().channel();

        logger.info("server listening on {}:{}", host, port);
    }

    @Override
    public void close() {
        if (channel != null) {
            channel.close();
            channel = null;
        }
    }

}

