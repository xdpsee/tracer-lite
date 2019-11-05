package com.zhenhui.demo.tracer.server.support.codec;

import com.zhenhui.demo.tracer.domain.Command;
import com.zhenhui.demo.tracer.domain.server.Encoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractProtocolEncoder extends MessageToByteEncoder<Command> implements Encoder {

    private static final Logger logger = LoggerFactory.getLogger(Encoder.class);

    @Override
    protected final void encode(ChannelHandlerContext ctx, Command command, ByteBuf out) throws Exception {
        byte[] bytes = encodeCommand(ctx, command);

        StringBuilder s = new StringBuilder();
        s.append(String.format("[%s] ", ctx.channel().id()));
        s.append("ID: ").append(command.getDeviceId().toString()).append(", ");
        s.append("CMD: ").append(command.getType()).append(" ");
        if (bytes != null) {
            s.append("SENT");
        } else {
            s.append("NOT SENT");
        }

        logger.info(s.toString());

        out.writeBytes(bytes);
    }

    private byte[] encodeCommand(ChannelHandlerContext ctx, Command command) throws Exception {
        return encode(command);
    }
}
