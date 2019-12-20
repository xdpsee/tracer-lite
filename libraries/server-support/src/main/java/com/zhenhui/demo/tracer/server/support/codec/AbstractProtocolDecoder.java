package com.zhenhui.demo.tracer.server.support.codec;

import com.zhenhui.demo.tracer.storage.api.domain.Location;
import com.zhenhui.demo.tracer.domain.server.Decoder;
import com.zhenhui.demo.tracer.domain.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class AbstractProtocolDecoder<I, T extends Message> extends MessageToMessageDecoder<I> implements Decoder<I, T> {

    @Override
    protected final void decode(ChannelHandlerContext ctx, I msg, List<Object> out) throws Exception {

        final List<T> results = decode(ctx, msg);

        results.forEach(result -> {
            if (result == msg) {
                ctx.fireChannelRead(result);
            } else {
                saveOrigin(result, msg);
                out.add(result);
            }
        });
    }

    private void saveOrigin(Object decodedMessage, Object originalMessage) {

        if (decodedMessage instanceof Location) {
            Location position = (Location) decodedMessage;
            if (originalMessage instanceof ByteBuf) {
                ByteBuf buf = (ByteBuf) originalMessage;
                position.getAttributes().put(Location.Attributes.KEY_ORIGINAL, ByteBufUtil.hexDump(buf, 0, buf.writerIndex()));
            } else if (originalMessage instanceof String) {
                position.getAttributes().put(Location.Attributes.KEY_ORIGINAL, DatatypeConverter.printHexBinary(
                        ((String) originalMessage).getBytes(StandardCharsets.UTF_8)));
            }

        }
    }
}




