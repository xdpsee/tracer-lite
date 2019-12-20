/*
 * Copyright 2016 Anton Tananaev (anton@traccar.org)
 * Copyright 2016 Andrey Kunitsyn (andrey@traccar.org)
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
import com.zhenhui.demo.tracer.server.support.server.ServerContext;
import com.zhenhui.demo.tracer.storage.api.domain.DeviceID;
import com.zhenhui.demo.tracer.storage.api.domain.Event;
import com.zhenhui.demo.tracer.storage.api.domain.EventType;
import com.zhenhui.demo.tracer.storage.api.domain.Location;

import java.util.ArrayList;
import java.util.List;

public class IgnitionEventHandler extends AbstractEventHandler {

    public IgnitionEventHandler(ServerConnector connector) {
        super(connector);
    }

    @Override
    protected List<Event> analyzeEvent(DataMessage message) {

        final Location currPos = message.getLocation();
        final List<Event> events = new ArrayList<>();

        final DeviceID deviceId = currPos.getDeviceId();
        final Location lastPos = ServerContext.locationService().queryLastLocation(deviceId);
        final boolean ignition = currPos.getAttributes().getBoolean(Location.Attributes.KEY_IGNITION, false);

        if (lastPos != null && lastPos.getId().equals(currPos.getId())) {
            boolean oldIgnition = lastPos.getAttributes().getBoolean(Location.Attributes.KEY_IGNITION, false);
            if (ignition && !oldIgnition) {
                events.add(new Event(EventType.TYPE_IGNITION_ON, currPos));
            } else if (!ignition && oldIgnition) {
                events.add(new Event(EventType.TYPE_IGNITION_OFF, currPos));
            }
        }

        return events;
    }
}


