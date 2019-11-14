package com.zhenhui.demo.tracer.uic.service.impl;

import com.zhenhui.demo.tracer.uic.api.domain.User;
import com.zhenhui.demo.tracer.uic.api.service.UserReadService;
import com.zhenhui.demo.tracer.uic.service.impl.manager.UserManager;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Service(version = "1.0.0")
public class UserReadServiceImpl implements UserReadService {

    @Autowired
    private UserManager userManager;

    @Cacheable(cacheNames = "user", key = "#username", unless = "#result == null")
    @Override
    public User queryByName(String username) {
        return userManager.getByName(username);
    }

    @Cacheable(cacheNames = "user", key = "#userId", unless = "#result == null")
    @Override
    public User queryById(Long userId) {
        return userManager.getById(userId);
    }

    @Cacheable(cacheNames = "direct-sub-users", key = "#userId", unless = "#result == null")
    @Override
    public List<User> queryDirectSubUsers(Long userId) {
        return userManager.getDirectSubUsers(userId);
    }

    @Cacheable(cacheNames = "all-sub-users", key = "#userId", unless = "#result == null")
    @Override
    public List<User> queryAllSubUsers(Long userId) {
        return userManager.getAllSubUsers(userId);
    }
}

