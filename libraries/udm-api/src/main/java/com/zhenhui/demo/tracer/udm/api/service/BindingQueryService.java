package com.zhenhui.demo.tracer.udm.api.service;

import com.zhenhui.demo.tracer.domain.Device;
import com.zhenhui.demo.tracer.uic.api.domain.User;

import java.util.List;

public interface BindingQueryService {

    List<Device> queryUserDevices(Long userId);

    List<User> queryDeviceUsers(Long deviceId);
}

