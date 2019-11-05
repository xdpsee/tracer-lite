package com.zhenhui.demo.tracer.storage.service.api;

import com.zhenhui.demo.tracer.domain.Event;

import java.util.List;

public interface EventService {

    void saveEvents(List<Event> events);

}

