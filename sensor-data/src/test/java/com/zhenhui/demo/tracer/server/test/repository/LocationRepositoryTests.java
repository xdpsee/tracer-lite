package com.zhenhui.demo.tracer.server.test.repository;

import com.zhenhui.demo.tracer.storage.api.domain.DeviceID;
import com.zhenhui.demo.tracer.storage.api.domain.Location;
import com.zhenhui.demo.tracer.storage.api.domain.Network;
import com.zhenhui.demo.tracer.storage.service.dal.entity.LocationDO;
import com.zhenhui.demo.tracer.storage.service.dal.repository.LocationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertNotNull;

@DataJpaTest
@RunWith(SpringRunner.class)
public class LocationRepositoryTests {

    @Autowired
    private LocationRepository repository;

    @Test
    public void testSaveLocation() {

        LocationDO location = new LocationDO();
        location.setDeviceId(DeviceID.fromString("IMEI:120120888888888"));
        location.setTimestamp(LocalDateTime.now());
        location.setLocated(true);
        location.setAttributes(new Location.Attributes());
        location.setNetwork(new Network());

        LocationDO result = repository.save(location);
        assertNotNull(result.getId());
    }

}
