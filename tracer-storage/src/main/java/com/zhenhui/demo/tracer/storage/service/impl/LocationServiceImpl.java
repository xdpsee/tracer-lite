package com.zhenhui.demo.tracer.storage.service.impl;

import com.zhenhui.demo.tracer.domain.DeviceID;
import com.zhenhui.demo.tracer.domain.Location;
import com.zhenhui.demo.tracer.storage.service.api.LocationService;
import com.zhenhui.demo.tracer.storage.service.conv.LocationConverter;
import com.zhenhui.demo.tracer.storage.service.dal.entity.LocationDO;
import com.zhenhui.demo.tracer.storage.service.dal.repository.LocationRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;

    @Autowired
    private LocationConverter converter;

    @Override
    public Location saveLocation(Location position) {

        LocationDO location = converter.convert(position);

        LocationDO result = repository.save(location);

        return converter.convert(result);
    }

    @Override
    public Location queryLastLocation(DeviceID deviceId) {
        return null;
    }

}
