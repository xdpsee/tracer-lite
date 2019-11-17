package com.zhenhui.demo.tracer.storage.service;

import com.zhenhui.demo.tracer.common.DeviceID;
import com.zhenhui.demo.tracer.storage.api.service.DeviceService;
import com.zhenhui.demo.tracer.storage.service.dal.entity.DeviceDO;
import com.zhenhui.demo.tracer.storage.service.dal.repository.DeviceRepository;
import com.zhenhui.demo.tracer.common.Device;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Device queryDevice(Long deviceId) {
        Optional<DeviceDO> deviceDO = deviceRepository.findById(deviceId);

        if (deviceDO.isPresent()) {
            Device device = new Device();
            device.setDeviceId(deviceDO.get().getDeviceId());
            device.setCaption(deviceDO.get().getCaption());
            device.setAttributes(deviceDO.get().getAttributes());

            return device;
        }

        return null;
    }

    @Override
    public List<Device> queryDevices(List<Long> deviceIds) {

        List<DeviceDO> devices = deviceRepository.findAllById(deviceIds);

        return devices.stream().map(d -> {
            Device device = new Device();
            device.setDeviceId(d.getDeviceId());
            device.setCaption(d.getCaption());
            device.setAttributes(d.getAttributes());
            return device;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean existsDevice(Long deviceId) {
        return deviceRepository.existsById(deviceId);
    }
}
