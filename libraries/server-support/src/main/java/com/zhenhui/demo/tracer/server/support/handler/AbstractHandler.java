package com.zhenhui.demo.tracer.server.support.handler;

import com.zhenhui.demo.tracer.common.DeviceID;
import com.zhenhui.demo.tracer.domain.server.Configs;
import com.zhenhui.demo.tracer.domain.server.Connection;
import com.zhenhui.demo.tracer.domain.Message;
import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import com.zhenhui.demo.tracer.server.support.exception.DeviceException;
import com.zhenhui.demo.tracer.server.support.exception.MessageException;
import com.zhenhui.demo.tracer.server.support.server.ConnectionImpl;
import com.zhenhui.demo.tracer.server.support.server.ServerContext;
import com.zhenhui.demo.tracer.server.support.utils.ChannelAttributes;
import com.zhenhui.demo.tracer.server.support.utils.DeviceUtils;
import com.zhenhui.demo.tracer.common.Device;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;

public abstract class AbstractHandler<T extends Message> extends SimpleChannelInboundHandler<T> implements io.netty.channel.ChannelInboundHandler {

    private static final Logger logger = LoggerFactory.getLogger(AbstractHandler.class);

    protected final ServerConnector connector;

    public AbstractHandler(ServerConnector connector) {
        super();
        this.connector = connector;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) {

        ctx.executor().submit(() -> {
            try {
                Connection connection = checkDevice(ctx, msg);
                messageReceived(ctx, connection, msg);
            } catch (DeviceException.NotFound | DeviceException.Expired e) {
                logger.error("AbstractHandler device exception", e);
                ctx.close();
            } catch (MessageException.ExecuteFailure e) {
                logger.error("AbstractHandler message handle exception", e);
                ctx.close();
            } catch (Throwable e) {
                logger.error("AbstractHandler message unexpected exception", e);
                ctx.close();
            }
        });

    }

    protected abstract void messageReceived(ChannelHandlerContext ctx, Connection connection, T msg) throws MessageException.ExecuteFailure;

    protected final Configs configs() {
        return connector.configs();
    }

    private Connection checkDevice(ChannelHandlerContext ctx, T msg) throws DeviceException.NotFound, DeviceException.Expired {

        final Device device = ServerContext.deviceService().queryDevice(msg.deviceId());
        if (null == device) {
            throw new DeviceException.NotFound();
        }

        DeviceUtils.checkExpired(device);

        final Connection connection = new ConnectionImpl(ctx.channel(), connector.protocol());
        ChannelAttributes.setIfAbsent(ctx, ChannelAttributes.CONNECTION, connection);
        ChannelAttributes.setIfAbsent(ctx, ChannelAttributes.DEVICE_ID, device.getDeviceId());

        Connection conn = ServerContext.connectionManager().getConnection(device.getDeviceId());
        if (!connection.equals(conn)) {
            ServerContext.connectionManager().registerConnection(connection);
        }

        return connection;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        DeviceID deviceId = (DeviceID) ChannelAttributes.get(ctx, ChannelAttributes.DEVICE_ID);
        if (deviceId != null) {
            ServerContext.connectionManager().unregisterConnection(deviceId);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        final Channel channel = ctx.channel();
        if (cause instanceof ClosedChannelException) {
            logger.warn("AbstractHandler.exceptionCaught, attempt to write to closed channel " + channel);
        } else {
            if (cause instanceof IOException && "Connection reset by peer".equals(cause.getMessage())) {
                logger.warn("AbstractHandler.exceptionCaught, connection reset by peer");
            } else {
                logger.error("AbstractHandler.exceptionCaught, unexpected exception from downstream for " + channel,
                        cause);
            }

            ctx.close();
        }
    }
}


