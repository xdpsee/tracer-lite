package com.zhenhui.demo.tracer.uic.api.service;

import com.zhenhui.demo.tracer.uic.api.domain.User;

public interface UserQueryService {

    User queryByName(String username);

    User queryById(Long userId);

}
