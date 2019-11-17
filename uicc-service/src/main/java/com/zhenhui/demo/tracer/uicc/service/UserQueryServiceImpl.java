package com.zhenhui.demo.tracer.uicc.service;

import com.zhenhui.demo.tracer.uicc.api.domain.User;
import com.zhenhui.demo.tracer.uicc.api.service.UserQueryService;
import com.zhenhui.demo.tracer.uicc.service.manager.UserManager;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Service(version = "1.0.0")
public class UserQueryServiceImpl implements UserQueryService {

    private static final Logger logger = LoggerFactory.getLogger(UserQueryServiceImpl.class);

    @Autowired
    private UserManager userManager;

    @Autowired
    private CacheManager cacheManager;

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

    @Override
    public List<User> queryByIds(List<Long> userIds) {

        final Cache cache = cacheManager.getCache("user");
        final List<User> result = new ArrayList<>(userIds.size());

        final Map<Long, User> userMap = new HashMap<>();
        final List<Long> absentUserIds = new ArrayList<>(userIds.size());
        for (Long userId : userIds) {
            if (cache == null) {
                absentUserIds.addAll(userIds);
            } else {
                User user = (User) cache.get(userId);
                if (user != null) {
                    userMap.put(userId, user);
                } else {
                    absentUserIds.add(userId);
                }
            }
        }

        List<User> absentUsers = userManager.getByIds(absentUserIds);
        userMap.putAll(absentUsers.stream().collect(toMap(User::getId, e->e)));

        if (cache != null) {
            absentUsers.forEach(au -> cache.put(au.getId(), au));
        }

        userIds.forEach(userId -> {
            if (userMap.containsKey(userId)) {
                result.add(userMap.get(userId));
            }
        });

        return result;
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

