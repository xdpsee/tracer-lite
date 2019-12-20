package com.zhenhui.demo.tracer.storage.service.conv;

import com.zhenhui.demo.tracer.storage.api.domain.Event;
import com.zhenhui.demo.tracer.storage.service.dal.entity.EventDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventConverter {

    List<EventDO> convert(List<Event> events);

    EventDO convert(Event event);

}
