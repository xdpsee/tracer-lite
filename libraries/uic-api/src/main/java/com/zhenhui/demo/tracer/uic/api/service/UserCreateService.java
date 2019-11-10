package com.zhenhui.demo.tracer.uic.api.service;

import com.zhenhui.demo.tracer.uic.api.domain.Role;
import com.zhenhui.demo.tracer.uic.api.domain.User;

import java.util.Set;

public interface UserCreateService {

    Long createUser(User user, Set<Role> roles);

}

