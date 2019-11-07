package com.zhenhui.demo.tracer.webapp.security;

import com.zhenhui.demo.tracer.webapp.config.WebSocketConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class WebSocketChannelInterceptor implements ChannelInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        logger.info("WebSocketChannelInterceptor.preSend {}, {}", message.getHeaders(), message.getPayload());

        return message;
    }
}


