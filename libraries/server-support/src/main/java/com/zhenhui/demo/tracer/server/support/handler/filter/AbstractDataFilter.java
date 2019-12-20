package com.zhenhui.demo.tracer.server.support.handler.filter;


import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import com.zhenhui.demo.tracer.server.support.handler.AbstractDataHandler;
import com.zhenhui.demo.tracer.server.support.server.DataMessage;
import com.zhenhui.demo.tracer.server.support.server.ServerContext;
import com.zhenhui.demo.tracer.storage.api.domain.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDataFilter extends AbstractDataHandler {

    private static final Logger logger = LoggerFactory.getLogger(AbstractDataFilter.class);

    private final FilterPolicy filterPolicy;

    protected AbstractDataFilter(ServerConnector connector, FilterPolicy filterPolicy) {
        super(connector);
        this.filterPolicy = filterPolicy;
    }

    @Override
    protected DataMessage handleMessage(DataMessage message) {

        if (filterPolicy != null) {
            Location lastPos = ServerContext.locationService().queryLastLocation(message.deviceId());

            if (!filterPolicy.accept(message.getLocation(), lastPos)) {
                return null;
            }
        }

        return message;
    }

}
