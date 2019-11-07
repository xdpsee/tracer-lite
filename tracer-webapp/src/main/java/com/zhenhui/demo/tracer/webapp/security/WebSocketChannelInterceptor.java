package com.zhenhui.demo.tracer.webapp.security;

import com.zhenhui.demo.tracer.webapp.config.WebSocketConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WebSocketChannelInterceptor implements ChannelInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        logger.info("WebSocketChannelInterceptor.preSend {}, {}", message.getHeaders(), message.getPayload());

        MessageHeaders headers = message.getHeaders();
        SimpMessageType messageType = (SimpMessageType) headers.get("simpMessageType");
        if (SimpMessageType.CONNECT != messageType) {
            return message;
        }

        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
            String token = (String) sessionAttributes.get("TOKEN");
            logger.info("TOKEN ------>" + token);
        }

        return message;
    }
}


