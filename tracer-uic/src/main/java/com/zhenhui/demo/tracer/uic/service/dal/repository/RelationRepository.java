package com.zhenhui.demo.tracer.uic.service.dal.repository;

import com.zhenhui.demo.tracer.uic.service.dal.entity.RelationDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationRepository extends JpaRepository<RelationDO, Long> {

    List<RelationDO> findByDescendantId(Long descendantId);


}
