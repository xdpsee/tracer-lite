package com.zhenhui.demo.tracer.domain.server;


public interface TraceServer {

    void listen(int port, String host);

    void close();

}


