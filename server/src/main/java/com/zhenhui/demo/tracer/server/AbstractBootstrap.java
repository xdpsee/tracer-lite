package com.zhenhui.demo.tracer.server;

import com.zhenhui.demo.tracer.server.support.server.ServerContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBootstrap implements InitializingBean, DisposableBean {

    protected ServerContext serverContext;

    @Autowired
    public final void setServerContext(ServerContext serverContext) {
        this.serverContext = serverContext;
    }

    @Override
    public final void afterPropertiesSet() throws Exception {
        onStart();
    }

    @Override
    public final void destroy() throws Exception {
        onStop();
    }

    protected abstract void onStart();

    protected abstract void onStop();
}
