package com.zhenhui.demo.tracer.webapp.security;

import com.zhenhui.demo.tracer.security.UserDetailsServiceSupport;
import com.zhenhui.demo.tracer.uic.api.service.PermissionService;
import com.zhenhui.demo.tracer.uic.api.service.UserQueryService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl extends UserDetailsServiceSupport {

    @Reference(version = "1.0.0")
    private UserQueryService userQueryService;

    @Reference(version = "1.0.0")
    private PermissionService permissionService;

    @Override
    protected UserQueryService userQueryService() {
        return this.userQueryService;
    }

    @Override
    protected PermissionService permissionService() {
        return this.permissionService;
    }
}
