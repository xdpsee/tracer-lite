package com.zhenhui.demo.tracer.storage.service.impl;

import com.zhenhui.demo.tracer.domain.Event;
import com.zhenhui.demo.tracer.storage.service.dal.entity.EventDO;
import com.zhenhui.demo.tracer.storage.service.api.EventService;
import com.zhenhui.demo.tracer.storage.service.dal.repository.EventRepository;
import com.zhenhui.demo.tracer.storage.service.conv.EventConverter;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private EventConverter converter;

    @Override
    public void saveEvents(List<Event> events) {

        List<EventDO> eventDOs = converter.convert(events);

        repository.saveAll(eventDOs);

    }
}
