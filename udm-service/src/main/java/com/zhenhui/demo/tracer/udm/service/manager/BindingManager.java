package com.zhenhui.demo.tracer.udm.service.manager;

import com.zhenhui.demo.tracer.udm.service.dal.entity.DeviceBindingDO;
import com.zhenhui.demo.tracer.udm.service.dal.repository.BindingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
            return false;
        }

        return true;
    }

    public boolean unbind(long userId, long deviceId) {

        try {
            repository.deleteByUserIdAndDeviceId(userId, deviceId);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
