package com.zhenhui.demo.tracer.udm.service.dal.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_devices", indexes = {
        @Index(name = "uk_user_id_device_id", columnList = "user_id,device_id", unique = true)
})
public class DeviceBindingDO implements Serializable {

    private static final long serialVersionUID = 7360248872516708460L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "device_id", nullable = false, updatable = false)
    private Long deviceId;

    @Column(name = "bound_at", nullable = false)
    private LocalDateTime boundAt;
}


