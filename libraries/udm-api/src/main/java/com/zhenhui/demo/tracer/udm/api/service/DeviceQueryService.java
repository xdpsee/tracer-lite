package com.zhenhui.demo.tracer.udm.api.service;

import com.zhenhui.demo.tracer.domain.Device;

import java.util.List;

public interface DeviceQueryService {

    List<Device> queryUserDevices(Long userId);

}

