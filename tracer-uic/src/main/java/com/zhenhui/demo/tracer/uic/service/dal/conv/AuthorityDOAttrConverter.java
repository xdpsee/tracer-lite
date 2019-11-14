package com.zhenhui.demo.tracer.uic.service.dal.conv;

import com.zhenhui.demo.tracer.uic.service.dal.enums.AuthorityDO;

import javax.persistence.AttributeConverter;

public class AuthorityDOAttrConverter implements AttributeConverter<AuthorityDO, Long> {

    @Override
    public Long convertToDatabaseColumn(AuthorityDO authorityDO) {
        return authorityDO.getId();
    }

    @Override
    public AuthorityDO convertToEntityAttribute(Long aLong) {
        return AuthorityDO.valueOf(aLong);
    }
}
