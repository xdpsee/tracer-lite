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


import com.zhenhui.demo.tracer.domain.server.Configs;
import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import com.zhenhui.demo.tracer.server.support.handler.AbstractEventHandler;
import com.zhenhui.demo.tracer.server.support.server.DataMessage;
import com.zhenhui.demo.tracer.server.support.server.ServerContext;
import com.zhenhui.demo.tracer.storage.api.domain.Event;
import com.zhenhui.demo.tracer.storage.api.domain.EventType;
import com.zhenhui.demo.tracer.storage.api.domain.Location;

import java.util.ArrayList;
import java.util.List;

public class MotionEventHandler extends AbstractEventHandler {

    private double speedThreshold;

    public MotionEventHandler(ServerConnector connector) {
        super(connector);
        speedThreshold = configs().getDouble(Configs.EVENT_MOTION_SPEED_THRESHOLD, 0.01);
    }
    
    @Override
    protected List<Event> analyzeEvent(DataMessage message) {

        final Location currPos = message.getLocation();
        final List<Event> events = new ArrayList<>();

        double speed = currPos.getSpeed(), oldSpeed = 0;
        Location lastPos = ServerContext.locationService().queryLastLocation(currPos.getDeviceId());
        if (lastPos != null) {
            oldSpeed = lastPos.getSpeed();
        }

        if (speed > speedThreshold && oldSpeed <= speedThreshold) {
            events.add(new Event(EventType.TYPE_DEVICE_MOVING, currPos));
        } else if (speed <= speedThreshold && oldSpeed > speedThreshold) {
            events.add(new Event(EventType.TYPE_DEVICE_STOPPED, currPos));
        }

        return events;
    }

}
