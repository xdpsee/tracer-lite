package com.zhenhui.demo.tracer.server.support.handler;


import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import com.zhenhui.demo.tracer.server.support.server.DataMessage;
import com.zhenhui.demo.tracer.server.support.server.ServerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDataHandler extends AbstractDataHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDataHandler.class);

    public DefaultDataHandler(ServerConnector connector) {
        super(connector);
    }

    @Override
    protected DataMessage handleMessage(DataMessage message) {

        ServerContext.locationService().saveLocation(message.getLocation());

        return message;
    }

}
