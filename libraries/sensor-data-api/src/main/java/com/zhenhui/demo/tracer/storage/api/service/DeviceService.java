package com.zhenhui.demo.tracer.storage.api.service;


import com.zhenhui.demo.tracer.storage.api.domain.Device;
import com.zhenhui.demo.tracer.storage.api.domain.DeviceID;

import java.util.List;

public interface DeviceService {

    Device queryDevice(DeviceID deviceId);

    Device queryDevice(Long deviceId);

    List<Device> queryDevices(List<Long> deviceIds);

    boolean existsDevice(Long deviceId);
}
