package com.zhenhui.demo.tracer.server;

import com.zhenhui.demo.tracer.server.mobile.MobileConnector;
import com.zhenhui.demo.tracer.server.support.server.DefaultTraceServer;
import org.springframework.stereotype.Component;

@Component
public class MobileBootstrap extends AbstractBootstrap {

    private DefaultTraceServer server;

    @Override
    protected void onStart() {
        final MobileConnector connector = new MobileConnector(serverContext);
        server = new DefaultTraceServer(connector);
        server.listen(connector.configs().getInteger("port", 9527), "0.0.0.0");
    }

    @Override
    protected void onStop() {
        if (server != null) {
            server.close();
        }
    }

}


