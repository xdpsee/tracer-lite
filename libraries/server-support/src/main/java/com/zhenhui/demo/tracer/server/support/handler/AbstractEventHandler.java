package com.zhenhui.demo.tracer.server.support.handler;


import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import com.zhenhui.demo.tracer.server.support.server.DataMessage;
import com.zhenhui.demo.tracer.server.support.server.ServerContext;
import com.zhenhui.demo.tracer.storage.api.domain.Event;
import org.springframework.util.CollectionUtils;

import java.util.List;

public abstract class AbstractEventHandler extends AbstractDataHandler {

    public AbstractEventHandler(ServerConnector connector) {
        super(connector);
    }

    @Override
    protected final DataMessage handleMessage(DataMessage message) {

        List<Event> events = analyzeEvent(message);
        if (!CollectionUtils.isEmpty(events)) {
            ServerContext.eventService().saveEvents(events);
        }

        return message;
    }

    protected abstract List<Event> analyzeEvent(DataMessage message);

}


