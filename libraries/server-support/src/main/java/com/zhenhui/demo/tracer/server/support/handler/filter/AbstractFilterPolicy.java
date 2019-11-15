package com.zhenhui.demo.tracer.server.support.handler.filter;


import com.zhenhui.demo.tracer.domain.server.ServerConnector;

public abstract class AbstractFilterPolicy implements FilterPolicy {

    protected final ServerConnector connector;

    public AbstractFilterPolicy(ServerConnector connector) {
        this.connector = connector;
    }
}


