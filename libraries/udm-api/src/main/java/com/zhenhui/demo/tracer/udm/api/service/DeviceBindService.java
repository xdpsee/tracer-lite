package com.zhenhui.demo.tracer.udm.api.service;

import com.zhenhui.demo.tracer.udm.api.domain.BindingResult;

public interface DeviceBindService {

    BindingResult bindDevice(long userId, long deviceId);

    BindingResult unbindDevice(long userId, long deviceId);
}
