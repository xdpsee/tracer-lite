package com.zhenhui.demo.tracer.uic.service.dal.repository;

import com.zhenhui.demo.tracer.uic.service.dal.entity.RoleAuthorityDO;
import com.zhenhui.demo.tracer.uic.service.dal.entity.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<RoleAuthorityDO, Long> {

    List<RoleAuthorityDO> findByRole(RoleDO role);

}

