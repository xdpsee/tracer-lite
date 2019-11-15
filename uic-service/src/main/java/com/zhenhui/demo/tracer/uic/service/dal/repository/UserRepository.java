package com.zhenhui.demo.tracer.uic.service.dal.repository;

import com.zhenhui.demo.tracer.uic.service.dal.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDO, Long> {

    Optional<UserDO> findByUsername(String username);

}
