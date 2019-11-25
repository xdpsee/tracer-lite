package com.zhenhui.demo.tracer.server.test.repository;

import com.zhenhui.demo.tracer.common.DeviceID;
import com.zhenhui.demo.tracer.storage.service.dal.entity.DeviceDO;
import com.zhenhui.demo.tracer.storage.service.dal.repository.DeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
public class DeviceRepositoryTests {

    @Autowired
    private DeviceRepository repository;

    @Test
    public void testFindById() {
        Long id = newDevice(DeviceID.fromString("IMEI:888888888888888"), "test");

        Optional<DeviceDO> device = repository.findById(id);

        assertTrue(device.isPresent());
    }

    @Test
    public void testFindByDeviceId() {

        DeviceID deviceId = DeviceID.fromString("IMEI:888888888888889");

        Long id = newDevice(deviceId, "test");
        assertNotNull(id);

        Optional<DeviceDO> device = repository.findByDeviceId(deviceId);

        assertTrue(device.isPresent());

    }

    private Long newDevice(DeviceID deviceId, String caption) {
        DeviceDO deviceDO = new DeviceDO();
        deviceDO.setDeviceId(deviceId);
        deviceDO.setCaption(caption);
        deviceDO.setAttributes(null);

        return repository.save(deviceDO).getId();
    }
}
