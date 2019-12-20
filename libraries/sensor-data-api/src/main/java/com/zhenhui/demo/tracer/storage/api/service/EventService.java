package com.zhenhui.demo.tracer.storage.api.service;


import com.zhenhui.demo.tracer.storage.api.domain.Event;

import java.util.List;

public interface EventService {

    void saveEvents(List<Event> events);

}

