package com.zhenhui.demo.tracer.domain.server;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public interface Decoder<I, T extends Message>  {

    List<T> decode(ChannelHandlerContext ctx, I in) throws Exception;

}



