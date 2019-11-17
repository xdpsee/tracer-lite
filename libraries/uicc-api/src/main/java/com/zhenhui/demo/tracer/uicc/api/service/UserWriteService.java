package com.zhenhui.demo.tracer.uicc.api.service;

import com.zhenhui.demo.tracer.uicc.api.domain.Role;
import com.zhenhui.demo.tracer.uicc.api.domain.User;
import com.zhenhui.demo.tracer.uicc.api.exception.UserException;
import org.springframework.lang.NonNull;

import java.util.Set;

public interface UserWriteService {

    Long createUser(@NonNull User user, Set<Role> roles);

    Long createSubUser(@NonNull Long userId, @NonNull User subUser, Set<Role> roles) throws UserException;

    void removeSubUser(@NonNull Long userId, @NonNull Long subUserId) throws UserException;

}

