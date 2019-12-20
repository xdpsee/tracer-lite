package com.zhenhui.demo.tracer.storage.service.dal.entity.convertor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenhui.demo.tracer.common.utils.JsonUtils;
import com.zhenhui.demo.tracer.storage.api.domain.Location;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;

public class LocationAttrsConverter implements AttributeConverter<Location.Attributes, String> {

    @Override
    public String convertToDatabaseColumn(Location.Attributes attributes) {
        if (attributes != null) {
            return JsonUtils.toJsonString(attributes);
        }

        return null;
    }

    @Override
    public Location.Attributes convertToEntityAttribute(String s) {

        if (!StringUtils.isEmpty(s)) {
            return JsonUtils.fromJsonString(s, new TypeReference<Location.Attributes>() {
            });
        }

        return null;
    }
}

