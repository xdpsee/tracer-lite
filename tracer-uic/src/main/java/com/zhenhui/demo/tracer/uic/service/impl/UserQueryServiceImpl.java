package com.zhenhui.demo.tracer.uic.service.impl;

import com.zhenhui.demo.tracer.uic.api.domain.User;
import com.zhenhui.demo.tracer.uic.api.service.UserQueryService;
import com.zhenhui.demo.tracer.uic.service.dal.entity.UserDO;
import com.zhenhui.demo.tracer.uic.service.dal.repository.UserRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

@Service(version = "1.0.0")
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(cacheNames = "user", key = "#username", unless = "#result == null")
    @Override
    public User queryByName(String username) {

        Optional<UserDO> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User result = new User();
            BeanUtils.copyProperties(user.get(), result);
            return result;
        }

        return null;
    }

    @Cacheable(cacheNames = "user", key = "#userId", unless = "#result == null")
    @Override
    public User queryById(Long userId) {
        Optional<UserDO> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User result = new User();
            BeanUtils.copyProperties(user.get(), result);
            return result;
        }

        return null;
    }
}
