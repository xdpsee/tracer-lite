package com.zhenhui.demo.tracer.server.support.handler.filter;


import com.zhenhui.demo.tracer.storage.api.domain.Location;

public interface FilterPolicy {

    boolean accept(Location newPos, Location lastPos);

}
