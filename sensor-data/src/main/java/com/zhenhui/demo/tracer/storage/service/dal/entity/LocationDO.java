package com.zhenhui.demo.tracer.storage.service.dal.entity;

import com.zhenhui.demo.tracer.storage.api.domain.DeviceID;
import com.zhenhui.demo.tracer.storage.api.domain.Location;
import com.zhenhui.demo.tracer.storage.api.domain.Network;
import com.zhenhui.demo.tracer.storage.service.dal.entity.convertor.DeviceIDConverter;
import com.zhenhui.demo.tracer.storage.service.dal.entity.convertor.LocationAttrsConverter;
import com.zhenhui.demo.tracer.storage.service.dal.entity.convertor.NetworkConverter;
import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "locations")
public class LocationDO implements Serializable {

    private static final long serialVersionUID = 6854705659135363409L;
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "device_id", length = 32, nullable = false)
    @Convert(converter = DeviceIDConverter.class)
    private DeviceID deviceId;

    @Column(name = "timestamp", columnDefinition = "DATETIME", nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime timestamp;

    @Column(name = "located")
    private boolean located;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "altitude")
    private double altitude;

    @Column(name = "speed")
    private double speed; // value in knots

    @Column(name = "course")
    private double course;

    @Column(name = "accuracy")
    private double accuracy;

    @Column(name = "network")
    @Convert(converter = NetworkConverter.class)
    private Network network;

    @Column(name = "attributes")
    @Convert(converter = LocationAttrsConverter.class)
    private Location.Attributes attributes;
}
