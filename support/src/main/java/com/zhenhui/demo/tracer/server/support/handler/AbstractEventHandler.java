package com.zhenhui.demo.tracer.server.support.handler;


import com.zhenhui.demo.tracer.domain.Event;
import com.zhenhui.demo.tracer.domain.Location;
import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import com.zhenhui.demo.tracer.server.support.server.ServerContext;
import org.springframework.util.CollectionUtils;

import java.util.List;

public abstract class AbstractEventHandler extends AbstractDataHandler {

    public AbstractEventHandler(ServerConnector connector) {
        super(connector);
    }

    @Override
    protected final Location handleLocation(Location position) {

        List<Event> events = analyzeEvent(position);
        if (!CollectionUtils.isEmpty(events)) {
            ServerContext.eventService().saveEvents(events);
        }

        return position;
    }

    protected abstract List<Event> analyzeEvent(Location position);

}


