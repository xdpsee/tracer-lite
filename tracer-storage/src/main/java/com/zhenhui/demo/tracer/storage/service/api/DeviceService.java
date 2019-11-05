package com.zhenhui.demo.tracer.storage.service.api;

import com.zhenhui.demo.tracer.domain.Device;
import com.zhenhui.demo.tracer.domain.DeviceID;

public interface DeviceService {

    Device queryDevice(DeviceID deviceId);

}
