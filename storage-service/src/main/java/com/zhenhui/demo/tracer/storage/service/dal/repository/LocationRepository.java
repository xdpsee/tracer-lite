package com.zhenhui.demo.tracer.storage.service.dal.repository;

import com.zhenhui.demo.tracer.storage.service.dal.entity.LocationDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationDO, Long> {


}

