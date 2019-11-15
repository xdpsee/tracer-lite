package com.zhenhui.demo.tracer.udm.service;

import com.zhenhui.demo.tracer.udm.api.exception.BindingException;
import com.zhenhui.demo.tracer.udm.api.service.DeviceBindService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class DeviceBindServiceImpl implements DeviceBindService {

    @Override
    public boolean bindDevice(long userId, long deviceId) throws BindingException {
        return false;
    }

    @Override
    public boolean unbindDevice(long userId, long deviceId) throws BindingException {
        return false;
    }
}

