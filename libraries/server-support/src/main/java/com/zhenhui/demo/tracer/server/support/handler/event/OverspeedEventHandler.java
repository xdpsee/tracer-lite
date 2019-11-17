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


import com.zhenhui.demo.tracer.common.DeviceID;
import com.zhenhui.demo.tracer.domain.Event;
import com.zhenhui.demo.tracer.domain.Location;
import com.zhenhui.demo.tracer.domain.enums.EventType;
import com.zhenhui.demo.tracer.domain.server.Configs;
import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import com.zhenhui.demo.tracer.server.support.handler.AbstractEventHandler;
import com.zhenhui.demo.tracer.server.support.server.ServerContext;
import com.zhenhui.demo.tracer.server.support.utils.DeviceUtils;
import com.zhenhui.demo.tracer.common.Device;

import java.util.ArrayList;
import java.util.List;

public class OverspeedEventHandler extends AbstractEventHandler {

    private final boolean notRepeat;

    public OverspeedEventHandler(ServerConnector connector) {
        super(connector);
        notRepeat = configs().getBoolean(Configs.EVENT_OVERSPEED_NOT_REPEAT, false);
    }

    @Override
    protected List<Event> analyzeEvent(Location currPos) {
        final List<Event> events = new ArrayList<>();

        final DeviceID deviceId = currPos.deviceId();
        final Device device = ServerContext.deviceService().queryDevice(deviceId);

        double speed = currPos.getSpeed();
        double speedLimit = DeviceUtils.getDoubleAttr(device, Device.Attributes.KEY_DOUBLE_SPEED_LIMIT, 0.0f);
        if (speedLimit > 0 && currPos.isLocated()) {
            final Location lastPos = ServerContext.locationService().queryLastLocation(deviceId);
            double oldSpeed = 0;
            if (notRepeat) {
                if (lastPos != null) {
                    oldSpeed = lastPos.getSpeed();
                }
            }

            if (lastPos == null
                    || (!lastPos.getId().equals(currPos.getId())
                    && speed > speedLimit
                    && oldSpeed <= speedLimit)
            ) {
                Event event = new Event(EventType.TYPE_DEVICE_OVERSPEED, currPos);
                event.getAttributes().put(Event.Attributes.KEY_DOUBLE_SPEED, speed);
                event.getAttributes().put(Device.Attributes.KEY_DOUBLE_SPEED_LIMIT, speedLimit);
                events.add(event);
            }
        }

        return events;
    }
}



