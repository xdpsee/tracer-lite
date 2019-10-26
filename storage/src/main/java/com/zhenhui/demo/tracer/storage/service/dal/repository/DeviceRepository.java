package com.zhenhui.demo.tracer.storage.service.dal.repository;

import com.zhenhui.demo.tracer.domain.DeviceID;
import com.zhenhui.demo.tracer.storage.service.dal.entity.DeviceDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<DeviceDO, Long> {

    Optional<DeviceDO> findByDeviceId(DeviceID deviceId);
}

