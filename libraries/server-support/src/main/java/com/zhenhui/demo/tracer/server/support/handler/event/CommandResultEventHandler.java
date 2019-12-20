/*
 * Copyright 2016 Anton Tananaev (anton@traccar.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhenhui.demo.tracer.server.support.handler.event;


import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import com.zhenhui.demo.tracer.server.support.handler.AbstractEventHandler;
import com.zhenhui.demo.tracer.server.support.server.DataMessage;
import com.zhenhui.demo.tracer.storage.api.domain.Event;
import com.zhenhui.demo.tracer.storage.api.domain.EventType;
import com.zhenhui.demo.tracer.storage.api.domain.Location;

import java.util.ArrayList;
import java.util.List;

public class CommandResultEventHandler extends AbstractEventHandler {

    public CommandResultEventHandler(ServerConnector connector) {
        super(connector);
    }

    @Override
    protected List<Event> analyzeEvent(DataMessage message) {

        final Location position = message.getLocation();
        final List<Event> events = new ArrayList<>();

        String commandResult = position.getAttributes().getString(Location.Attributes.KEY_RESULT);
        if (commandResult != null) {
            Event event = new Event(EventType.TYPE_COMMAND_RESULT, position);
            event.getAttributes().put(Location.Attributes.KEY_RESULT, commandResult);
            events.add(event);
        }

        return events;

    }

}
