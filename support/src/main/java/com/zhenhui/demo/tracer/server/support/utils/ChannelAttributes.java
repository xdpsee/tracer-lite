package com.zhenhui.demo.tracer.server.support.utils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public enum ChannelAttributes {

    DEVICE_ID,
    CONNECTION,
    ;

    public static void setIfAbsent(Channel channel, ChannelAttributes attr, Object value) {
        if (null == channel || null == attr || null == value) {
            throw new IllegalArgumentException("null param");
        }

        channel.attr(AttributeKey.valueOf(attr.name())).setIfAbsent(value);
    }

    public static void set(Channel channel, ChannelAttributes attr, Object value) {
        if (null == channel || null == attr || null == value) {
            throw new IllegalArgumentException("null param");
        }

        channel.attr(AttributeKey.valueOf(attr.name())).set(value);
    }

    public static Object get(Channel channel, ChannelAttributes attr) {
        if (null == channel || null == attr) {
            throw new IllegalArgumentException("");
        }

        return channel.attr(AttributeKey.valueOf(attr.name())).get();
    }

    public static void setIfAbsent(ChannelHandlerContext ctx, ChannelAttributes attr, Object value) {
        setIfAbsent(ctx.channel(), attr, value);
    }

    public static void set(ChannelHandlerContext ctx, ChannelAttributes attr, Object value) {
        set(ctx.channel(), attr, value);
    }

    public static Object get(ChannelHandlerContext ctx, ChannelAttributes attr) {
        return get(ctx.channel(), attr);
    }


}


