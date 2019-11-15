package com.zhenhui.demo.tracer.udm.service;

import com.zhenhui.demo.tracer.storage.api.service.DeviceService;
import com.zhenhui.demo.tracer.udm.api.exception.BindingException;
import com.zhenhui.demo.tracer.udm.api.exception.ErrorCode;
import com.zhenhui.demo.tracer.udm.api.service.DeviceBindService;
import com.zhenhui.demo.tracer.udm.service.manager.BindingManager;
import com.zhenhui.demo.tracer.uic.api.domain.User;
import com.zhenhui.demo.tracer.uic.api.service.UserReadService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class DeviceBindServiceImpl implements DeviceBindService {

    @Autowired
    private BindingManager bindingManager;

    @Reference(version = "1.0.0")
    private UserReadService userReadService;

    @Reference(version = "1.0.0")
    private DeviceService deviceService;

    @Override
    public boolean bindDevice(long userId, long deviceId) throws BindingException {

        final User user = userReadService.queryById(userId);
        if (null == user) {
            throw new BindingException(ErrorCode.USER_NOT_FOUND);
        }

        if (!deviceService.existsDevice(deviceId)) {
            throw new BindingException(ErrorCode.DEVICE_NOT_FOUND);
        }

        return bindingManager.bind(userId, deviceId);
    }

    @Override
    public boolean unbindDevice(long userId, long deviceId) throws BindingException {

        final User user = userReadService.queryById(userId);
        if (null == user) {
            throw new BindingException(ErrorCode.USER_NOT_FOUND);
        }

        if (!deviceService.existsDevice(deviceId)) {
            throw new BindingException(ErrorCode.DEVICE_NOT_FOUND);
        }

        return bindingManager.unbind(userId, deviceId);
    }
}

