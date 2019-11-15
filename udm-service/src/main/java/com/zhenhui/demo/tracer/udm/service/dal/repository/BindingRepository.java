package com.zhenhui.demo.tracer.udm.service.dal.repository;

import com.zhenhui.demo.tracer.udm.service.dal.entity.DeviceBindingDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BindingRepository extends JpaRepository<DeviceBindingDO, Long> {

    Optional<DeviceBindingDO> findByUserIdAndDeviceId(long userId, long deviceId);

    void deleteByUserIdAndDeviceId(long userId, long deviceId);
}
