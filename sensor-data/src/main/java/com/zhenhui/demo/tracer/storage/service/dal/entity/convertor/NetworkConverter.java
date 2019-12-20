package com.zhenhui.demo.tracer.storage.service.dal.entity.convertor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenhui.demo.tracer.common.utils.JsonUtils;
import com.zhenhui.demo.tracer.storage.api.domain.Network;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;

public class NetworkConverter implements AttributeConverter<Network, String> {

    @Override
    public String convertToDatabaseColumn(Network network) {
        if (network != null) {
            JsonUtils.toJsonString(network);
        }

        return null;
    }

    @Override
    public Network convertToEntityAttribute(String s) {

        if (!StringUtils.isEmpty(s)) {
            return JsonUtils.fromJsonString(s, new TypeReference<Network>() {
            });
        }

        return null;
    }
}
