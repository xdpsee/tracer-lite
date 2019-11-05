package com.zhenhui.demo.tracer.storage.service.dal.entity.convertor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenhui.demo.tracer.domain.Device;
import com.zhenhui.demo.tracer.domain.utils.JsonUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;

public class DeviceAttrsConverter implements AttributeConverter<Device.Attributes, String> {

    @Override
    public String convertToDatabaseColumn(Device.Attributes attributes) {
        return JsonUtils.toJsonString(attributes);
    }

    @Override
    public Device.Attributes convertToEntityAttribute(String s) {
        if (!StringUtils.isEmpty(s)) {
            return JsonUtils.fromJsonString(s, new TypeReference<Device.Attributes>() {
            });
        }

        return null;
    }
}
