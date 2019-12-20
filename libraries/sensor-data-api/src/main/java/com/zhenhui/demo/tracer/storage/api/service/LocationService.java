package com.zhenhui.demo.tracer.storage.api.service;

import com.zhenhui.demo.tracer.storage.api.domain.DeviceID;
import com.zhenhui.demo.tracer.storage.api.domain.Location;

public interface LocationService {

    Location saveLocation(Location position);

    Location queryLastLocation(DeviceID deviceId);

}
