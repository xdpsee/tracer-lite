package com.zhenhui.demo.tracer.storage.api.service;

import com.zhenhui.demo.tracer.domain.Device;
import com.zhenhui.demo.tracer.domain.DeviceID;

public interface DeviceService {

    Device queryDevice(DeviceID deviceId);

    Device queryDevice(Long deviceId);

    boolean existsDevice(Long deviceId);
}
