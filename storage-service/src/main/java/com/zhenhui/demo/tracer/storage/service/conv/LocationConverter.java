package com.zhenhui.demo.tracer.storage.service.conv;

import com.zhenhui.demo.tracer.domain.Location;
import com.zhenhui.demo.tracer.storage.service.dal.entity.LocationDO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationConverter {

    LocationDO convert(Location position);

    Location convert(LocationDO location);
}
