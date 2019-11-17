package com.zhenhui.demo.tracer.udm.service.manager;

import com.zhenhui.demo.tracer.udm.service.dal.entity.DeviceBindingDO;
import com.zhenhui.demo.tracer.udm.service.dal.repository.BindingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BindingManager {

    private static final Logger logger = LoggerFactory.getLogger(BindingManager.class);

    @Autowired
    private BindingRepository repository;

    public boolean bind(long userId, long deviceId) {

        try {
            DeviceBindingDO bindingDO = new DeviceBindingDO();
            bindingDO.setUserId(userId);
            bindingDO.setDeviceId(deviceId);
            bindingDO.setBoundAt(LocalDateTime.now());
            repository.save(bindingDO);
        } catch (Exception e) {
            logger.error(String.format("BindingManager.bind userId=%d, deviceId=%d exception", userId, deviceId), e);
            return false;
        }

        return true;
    }

    public boolean unbind(long userId, long deviceId) {

        try {
            repository.deleteByUserIdAndDeviceId(userId, deviceId);
        } catch (Exception e) {
            logger.error(String.format("BindingManager.unbind userId=%d, deviceId=%d exception", userId, deviceId), e);
            return false;
        }

        return true;
    }

    public boolean isBound(long userId, long deviceId) {
        return repository.existsByUserIdAndDeviceId(userId, deviceId);
    }

    public List<Long> getUserDeviceIds(long userId) {
        List<DeviceBindingDO> bindings = repository.findByUserId(userId);
        return bindings.stream()
                .map(DeviceBindingDO::getDeviceId)
                .collect(Collectors.toList());
    }

    public List<Long> getDeviceUsers(long deviceId) {
        List<DeviceBindingDO> bindings = repository.findByDeviceId(deviceId);
        return bindings.stream()
                .map(DeviceBindingDO::getDeviceId)
                .collect(Collectors.toList());
    }
}

