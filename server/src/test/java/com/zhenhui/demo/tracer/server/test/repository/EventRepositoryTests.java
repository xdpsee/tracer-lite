package com.zhenhui.demo.tracer.server.test.repository;

import com.zhenhui.demo.tracer.domain.DeviceID;
import com.zhenhui.demo.tracer.domain.Event;
import com.zhenhui.demo.tracer.domain.enums.EventType;
import com.zhenhui.demo.tracer.storage.service.dal.entity.EventDO;
import com.zhenhui.demo.tracer.storage.service.dal.repository.EventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;

import static junit.framework.TestCase.assertNotNull;

@DataJpaTest
@RunWith(SpringRunner.class)
public class EventRepositoryTests {

    @Autowired
    private EventRepository repository;


    @Test
    public void testSaveEvent() {

        EventDO event = new EventDO();
        event.setDeviceId(DeviceID.fromString("IMEI:888888888888888"));
        event.setLocationId(1L);
        event.setType(EventType.TYPE_ALARM);
        event.setAttributes(new Event.Attributes());
        event.setDate(LocalDate.now());
        event.setTime(LocalTime.now());


        EventDO result = repository.save(event);

        assertNotNull(result.getId());

    }

}
