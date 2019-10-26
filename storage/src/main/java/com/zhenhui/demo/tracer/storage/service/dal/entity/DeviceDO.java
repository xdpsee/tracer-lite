package com.zhenhui.demo.tracer.storage.service.dal.entity;

import com.zhenhui.demo.tracer.domain.Device;
import com.zhenhui.demo.tracer.domain.DeviceID;
import com.zhenhui.demo.tracer.storage.service.dal.entity.convertor.DeviceAttrsConverter;
import com.zhenhui.demo.tracer.storage.service.dal.entity.convertor.DeviceIDConverter;
import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "devices",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_device_id", columnNames = "device_id")
        }
)
public class DeviceDO implements Serializable {

    private static final long serialVersionUID = -6239158928800854471L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "device_id", nullable = false, length = 32)
    @Convert(converter = DeviceIDConverter.class)
    private DeviceID deviceId;

    @Column(length = 64)
    private String caption;

    @Column(name = "created_at", columnDefinition = "DATETIME DEFAULT NOW()")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime updatedAt;

    @Column(length = 4096, columnDefinition = "TEXT")
    @Convert(converter = DeviceAttrsConverter.class)
    private Device.Attributes attributes;
}
