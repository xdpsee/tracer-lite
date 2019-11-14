package com.zhenhui.demo.tracer.uic.api.service;

import com.zhenhui.demo.tracer.uic.api.domain.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface UserReadService {

    @Nullable
    User queryByName(@NonNull String username);

    @Nullable
    User queryById(@NonNull Long userId);

    @NonNull
    List<User> queryDirectSubUsers(@NonNull Long userId);

    @NonNull
    List<User> queryAllSubUsers(@NonNull Long userId);

}
