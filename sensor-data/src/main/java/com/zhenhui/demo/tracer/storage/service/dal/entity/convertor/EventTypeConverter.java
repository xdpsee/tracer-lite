package com.zhenhui.demo.tracer.storage.service.dal.entity.convertor;

import com.zhenhui.demo.tracer.storage.api.domain.EventType;

import javax.persistence.AttributeConverter;

public class EventTypeConverter implements AttributeConverter<EventType, String> {

    @Override
    public String convertToDatabaseColumn(EventType eventType) {
        return eventType.name();
    }

    @Override
    public EventType convertToEntityAttribute(String s) {
        return EventType.valueOf(s);
    }
}
