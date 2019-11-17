package com.zhenhui.demo.tracer.uicc.service.dal.conv;

import com.zhenhui.demo.tracer.uicc.service.dal.enums.RoleDO;

import javax.persistence.AttributeConverter;

public class RoleDOAttrConverter implements AttributeConverter<RoleDO, Long> {

    @Override
    public Long convertToDatabaseColumn(RoleDO roleDO) {
        return roleDO.getId();
    }

    @Override
    public RoleDO convertToEntityAttribute(Long aLong) {
        return RoleDO.valueOf(aLong);
    }
}
