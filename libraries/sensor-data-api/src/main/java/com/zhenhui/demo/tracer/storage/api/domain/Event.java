package com.zhenhui.demo.tracer.storage.api.domain;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Event implements Serializable {

    private static final long serialVersionUID = 627329605165658568L;

    private Long id;

    private DeviceID deviceId;

    private EventType type;

    private long locationId;

    private LocalDate date;

    private LocalTime time;

    private Attributes attributes = new Attributes();


    public Event(EventType type, Location position) {
        this.type = type;
        this.locationId = position.getId();
        this.date = position.getTimestamp().toLocalDate();
        this.time = position.getTimestamp().toLocalTime();
        this.deviceId = position.getDeviceId();
    }

    /**
     * 扩展属性定义
     */
    public static class Attributes extends AttributesOp {

        public static final String KEY_DOUBLE_SPEED = "speed";

        public Attributes() {
            super();
        }

        public Attributes(Attributes attributes) {
            this();
            putAll(attributes);
        }
    }

}


