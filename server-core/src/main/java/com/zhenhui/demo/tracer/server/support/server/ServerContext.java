package com.zhenhui.demo.tracer.server.support.server;


import com.zhenhui.demo.tracer.domain.server.ConnectionManager;
import com.zhenhui.demo.tracer.storage.api.service.DeviceService;
import com.zhenhui.demo.tracer.storage.api.service.EventService;
import com.zhenhui.demo.tracer.storage.api.service.LocationService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class ServerContext implements ApplicationContextAware {

    private static volatile ApplicationContext context = null;

    private static volatile boolean initialized = false;

    @Reference(version = "1.0.0")
    private DeviceService deviceService;

    @Reference(version = "1.0.0")
    private LocationService locationService;

    @Reference(version = "1.0.0")
    private EventService eventService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        initialized = true;
    }

    public static DeviceService deviceService() {

        while (!initialized) ;
        
        return context.getBean(DeviceService.class);
    }

    public static LocationService locationService() {
        while (!initialized) ;

        return context.getBean(LocationService.class);
    }

    public static EventService eventService() {
        while (!initialized) ;

        return context.getBean(EventService.class);
    }

    public static ConnectionManager connectionManager() {
        return context.getBean(LocalConnectionManager.class);
    }
}


