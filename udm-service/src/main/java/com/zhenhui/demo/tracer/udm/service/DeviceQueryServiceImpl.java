package com.zhenhui.demo.tracer.udm.service;

import com.zhenhui.demo.tracer.domain.Device;
import com.zhenhui.demo.tracer.udm.api.service.DeviceQueryService;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

@Service(version = "1.0.0")
public class DeviceQueryServiceImpl implements DeviceQueryService {

    @Override
    public List<Device> queryUserDevices(Long userId) {
        return null;
    }
}

