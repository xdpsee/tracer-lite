package com.zhenhui.demo.tracer.storage.service.dal.entity.convertor;


import com.zhenhui.demo.tracer.common.DeviceID;

import javax.persistence.AttributeConverter;

public class DeviceIDConverter implements AttributeConverter<DeviceID, String> {

    @Override
    public String convertToDatabaseColumn(DeviceID deviceID) {
        return deviceID != null ? deviceID.toString() : null;
    }

    @Override
    public DeviceID convertToEntityAttribute(String s) {
        return DeviceID.fromString(s);
    }
}
