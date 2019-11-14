package com.zhenhui.demo.tracer.uic.service.impl;

import com.zhenhui.demo.tracer.uic.api.domain.Role;
import com.zhenhui.demo.tracer.uic.api.domain.User;
import com.zhenhui.demo.tracer.uic.api.exception.UserException;
import com.zhenhui.demo.tracer.uic.api.service.UserWriteService;
import com.zhenhui.demo.tracer.uic.service.impl.manager.UserManager;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;

import java.util.Set;

@Service(version = "1.0.0")
public class UserWriteServiceImpl implements UserWriteService {

    @Autowired
    private UserManager userManager;

    @Override
    public Long createUser(User user, Set<Role> roles) {
        return userManager.createUser(user, roles);
    }

    @CacheEvict(cacheNames = "direct-sub-users", key = "#userId")
    @Override
    public Long createSubUser(Long userId, User subUser, Set<Role> roles) throws UserException {
        return userManager.createSubUser(userId, subUser, roles);
    }

    @CacheEvict(cacheNames = "all-sub-users", key = "#userId")
    @Override
    public void removeSubUser(Long userId, Long subUserId) throws UserException {
        userManager.removeSubUser(userId, subUserId);
    }
}


