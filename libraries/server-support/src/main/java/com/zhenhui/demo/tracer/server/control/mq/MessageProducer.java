package com.zhenhui.demo.tracer.server.control.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String destination, final Object message) throws JmsException {
        jmsTemplate.convertAndSend(destination, message);
    }

}

