package com.zhenhui.demo.tracer.udm.service;

import com.zhenhui.demo.tracer.storage.api.service.DeviceService;
import com.zhenhui.demo.tracer.udm.api.exception.BindingException;
import com.zhenhui.demo.tracer.udm.api.exception.ErrorCode;
import com.zhenhui.demo.tracer.udm.api.service.BindingService;
import com.zhenhui.demo.tracer.udm.service.manager.BindingManager;
import com.zhenhui.demo.tracer.uicc.api.domain.User;
import com.zhenhui.demo.tracer.uicc.api.service.UserQueryService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unused")
@Service(version = "1.0.0")
public class BindingServiceImpl implements BindingService {

    @Autowired
    private BindingManager bindingManager;

    @Reference(version = "1.0.0")
    private UserQueryService userQueryService;

    @Reference(version = "1.0.0")
    private DeviceService deviceService;

    @Override
    public boolean bindDevice(long userId, long deviceId) throws BindingException {

        checkUserAndDevice(userId, deviceId);

        return bindingManager.bind(userId, deviceId);
    }

    @Override
    public boolean unbindDevice(long userId, long deviceId) throws BindingException {

        checkUserAndDevice(userId, deviceId);

        return bindingManager.unbind(userId, deviceId);
    }

    @Override
    public boolean isBound(long userId, long deviceId) {
        return bindingManager.isBound(userId, deviceId);
    }

    private void checkUserAndDevice(long userId, long deviceId) throws BindingException {
        final User user = userQueryService.queryById(userId);
        if (null == user) {
            throw new BindingException(ErrorCode.USER_NOT_FOUND);
        }

        if (!deviceService.existsDevice(deviceId)) {
            throw new BindingException(ErrorCode.DEVICE_NOT_FOUND);
        }
    }
}

