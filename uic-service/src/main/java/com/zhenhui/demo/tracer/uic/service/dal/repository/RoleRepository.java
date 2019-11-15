package com.zhenhui.demo.tracer.uic.service.dal.repository;

import com.zhenhui.demo.tracer.uic.service.dal.entity.UserRoleDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<UserRoleDO, Long> {

    List<UserRoleDO> findByUserId(Long userId);

}
