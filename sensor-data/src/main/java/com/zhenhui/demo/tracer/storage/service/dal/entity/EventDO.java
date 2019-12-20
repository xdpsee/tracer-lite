package com.zhenhui.demo.tracer.storage.service.dal.entity;

import com.zhenhui.demo.tracer.storage.api.domain.DeviceID;
import com.zhenhui.demo.tracer.storage.api.domain.Event;
import com.zhenhui.demo.tracer.storage.api.domain.EventType;
import com.zhenhui.demo.tracer.storage.service.dal.entity.convertor.DeviceIDConverter;
import com.zhenhui.demo.tracer.storage.service.dal.entity.convertor.EventAttrsConverter;
import com.zhenhui.demo.tracer.storage.service.dal.entity.convertor.EventTypeConverter;
import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "events")
public class EventDO implements Serializable {

    private static final long serialVersionUID = -6892929530232838066L;
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "device_id", length = 32, nullable = false)
    @Convert(converter = DeviceIDConverter.class)
    private DeviceID deviceId;

    @Column(name = "type", length = 32, nullable = false)
    @Convert(converter = EventTypeConverter.class)
    private EventType type;

    @Column(name = "location_id")
    private long locationId;

    @Column(name = "date", nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)
    private LocalTime time;

    @Column(name = "attributes", nullable = false, length = 4096)
    @Convert(converter = EventAttrsConverter.class)
    private Event.Attributes attributes;

}
