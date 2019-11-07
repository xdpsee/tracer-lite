package com.zhenhui.demo.tracer.webapi.security;

import com.zhenhui.demo.tracer.webapi.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.messaging.DefaultSimpUserRegistry;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class WebSocketSecurityInterceptor implements ChannelInterceptor {

    @Autowired
    private SimpUserRegistry userRegistry;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        String header = accessor.getFirstNativeHeader("Authorization");
        String token = null;
        if (!StringUtils.isEmpty(header)) {
            token = header.replaceAll("Bearer", "").trim();
        }

        if (StompCommand.CONNECT.equals(accessor.getCommand())
                || StompCommand.SUBSCRIBE.equals(accessor.getCommand())
                || StompCommand.SEND.equals(accessor.getCommand())) {
            final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null) {
                Authentication user = authentication(token); // access authentication header(s)
                SecurityContextHolder.getContext().setAuthentication(user);
                ((DefaultSimpUserRegistry) userRegistry).onApplicationEvent(new SessionConnectedEvent(this, (Message<byte[]>) message, user));
                accessor.setUser(user);
            } else {
                accessor.setUser(auth);
                ((DefaultSimpUserRegistry) userRegistry).onApplicationEvent(new SessionConnectedEvent(this, (Message<byte[]>) message, auth));
            }
        }
        accessor.setLeaveMutable(true);

        return MessageBuilder.createMessage(message.getPayload(), accessor.getMessageHeaders());
    }

    private Authentication authentication(String token) {
        UserPrincipal principal = tokenUtils.parse(token);
        return new UsernamePasswordAuthenticationToken(principal.getUsername(), "");
    }
}


