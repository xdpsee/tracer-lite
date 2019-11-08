package com.zhenhui.demo.tracer.storage.service.dal.entity.convertor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenhui.demo.tracer.common.utils.JsonUtils;
import com.zhenhui.demo.tracer.domain.Event;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;

public class EventAttrsConverter implements AttributeConverter<Event.Attributes, String> {

    @Override
    public String convertToDatabaseColumn(Event.Attributes attributes) {
        if (attributes != null) {
            return JsonUtils.toJsonString(attributes);
        }

        return null;
    }

    @Override
    public Event.Attributes convertToEntityAttribute(String s) {

        if (!StringUtils.isEmpty(s)) {
            return JsonUtils.fromJsonString(s, new TypeReference<Event.Attributes>() {
            });
        }

        return null;
    }
}
