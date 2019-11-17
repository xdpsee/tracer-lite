package com.zhenhui.demo.tracer.uicc.service.dal.repository;

import com.zhenhui.demo.tracer.uicc.service.dal.entity.RelationDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RelationRepository extends JpaRepository<RelationDO, Long> {

    List<RelationDO> findByAncestorIdOrderByDepthAsc(Long ancestorId);

    List<RelationDO> findByAncestorIdAndDepthOrderByDepthAsc(Long ancestorId, Integer depth);

    List<RelationDO> findByDescendantId(Long descendantId);

    List<RelationDO> findByAncestorId(Long ancestorId);

    Optional<RelationDO> findByAncestorIdAndDescendantId(Long ancestorId, Long descendantId);


}
