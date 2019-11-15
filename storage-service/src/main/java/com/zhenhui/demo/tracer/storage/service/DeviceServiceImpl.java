package com.zhenhui.demo.tracer.storage.service;

import com.zhenhui.demo.tracer.domain.Device;
import com.zhenhui.demo.tracer.domain.DeviceID;
import com.zhenhui.demo.tracer.storage.api.service.DeviceService;
import com.zhenhui.demo.tracer.storage.service.dal.entity.DeviceDO;
import com.zhenhui.demo.tracer.storage.service.dal.repository.DeviceRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service(version = "1.0.0")
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device queryDevice(DeviceID deviceId) {
        Optional<DeviceDO> deviceDO = deviceRepository.findByDeviceId(deviceId);

        if (deviceDO.isPresent()) {
            Device device = new Device();
            device.setDeviceId(deviceDO.get().getDeviceId());
            device.setCaption(deviceDO.get().getCaption());
            device.setAttributes(deviceDO.get().getAttributes());

            return device;
        }

        return null;
    }


}
