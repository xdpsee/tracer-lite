package com.zhenhui.demo.tracer.storage.service.dal.repository;

import com.zhenhui.demo.tracer.storage.service.dal.entity.EventDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventDO, Long> {


}
