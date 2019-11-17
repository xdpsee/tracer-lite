package com.zhenhui.demo.tracer.udm.service;

import com.zhenhui.demo.tracer.common.Device;
import com.zhenhui.demo.tracer.storage.api.service.DeviceService;
import com.zhenhui.demo.tracer.udm.api.service.BindingQueryService;
import com.zhenhui.demo.tracer.udm.service.manager.BindingManager;
import com.zhenhui.demo.tracer.uicc.api.domain.User;
import com.zhenhui.demo.tracer.uicc.api.service.UserQueryService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Service(version = "1.0.0")
public class BindingQueryServiceImpl implements BindingQueryService {

    @Autowired
    private BindingManager bindingManager;

    @Reference(version = "1.0.0")
    private DeviceService deviceService;

    @Reference(version = "1.0.0")
    private UserQueryService userQueryService;

    @Override
    public List<Device> queryUserDevices(Long userId) {

        List<Long> deviceIds = bindingManager.getUserDeviceIds(userId);
        if (deviceIds.isEmpty()) {
            return new ArrayList<>();
        }

        return deviceService.queryDevices(deviceIds);
    }

    @Override
    public List<User> queryDeviceUsers(Long deviceId) {

        List<Long> userIds = bindingManager.getDeviceUsers(deviceId);
        if (userIds.isEmpty()) {
            return new ArrayList<>();
        }

        return userQueryService.queryByIds(userIds);
    }
}


