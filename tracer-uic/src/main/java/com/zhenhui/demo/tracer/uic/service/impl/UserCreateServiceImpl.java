package com.zhenhui.demo.tracer.uic.service.impl;

import com.zhenhui.demo.tracer.uic.api.domain.Role;
import com.zhenhui.demo.tracer.uic.api.domain.User;
import com.zhenhui.demo.tracer.uic.api.service.UserCreateService;
import com.zhenhui.demo.tracer.uic.service.dal.entity.RoleDO;
import com.zhenhui.demo.tracer.uic.service.dal.entity.UserDO;
import com.zhenhui.demo.tracer.uic.service.dal.entity.UserRoleDO;
import com.zhenhui.demo.tracer.uic.service.dal.repository.RoleRepository;
import com.zhenhui.demo.tracer.uic.service.dal.repository.UserRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service(version = "1.0.0")
public class UserCreateServiceImpl implements UserCreateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Long createUser(User user, Set<Role> roles) {

        if (null == user) {
            throw new IllegalArgumentException("user == null");
        }

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(user, userDO);

        UserDO result = userRepository.save(userDO);

        roleRepository.saveAll(roles.stream().map(role -> {
            UserRoleDO ur = new UserRoleDO();
            ur.setUserId(result.getId());
            ur.setRole(RoleDO.valueOf(role.name()));
            return ur;
        }).collect(Collectors.toList()));

        return result.getId();
    }
}


