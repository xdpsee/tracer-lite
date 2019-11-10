package com.zhenhui.demo.tracer.storage.api.service;

import com.zhenhui.demo.tracer.domain.DeviceID;
import com.zhenhui.demo.tracer.domain.Location;

public interface LocationService {

    Location saveLocation(Location position);

    Location queryLastLocation(DeviceID deviceId);

}
