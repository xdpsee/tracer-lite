package com.zhenhui.demo.tracer.server.support.handler.filter;


import com.zhenhui.demo.tracer.domain.Location;
import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import com.zhenhui.demo.tracer.server.support.handler.AbstractDataHandler;
import com.zhenhui.demo.tracer.server.support.server.ServerContext;
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
    protected Location handleLocation(Location currPos) {

        if (filterPolicy != null) {
            Location lastPos = ServerContext.locationService().queryLastLocation(currPos.deviceId());

            if (!filterPolicy.accept(currPos, lastPos)) {
                return null;
            }
        }

        return currPos;
    }

}
