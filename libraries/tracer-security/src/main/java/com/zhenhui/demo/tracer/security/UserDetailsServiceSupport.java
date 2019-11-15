package com.zhenhui.demo.tracer.security;

import com.zhenhui.demo.tracer.uic.api.domain.Role;
import com.zhenhui.demo.tracer.uic.api.domain.User;
import com.zhenhui.demo.tracer.uic.api.service.PermissionService;
import com.zhenhui.demo.tracer.uic.api.service.UserReadService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsServiceSupport implements UserDetailsService {

    @Reference(version = "1.0.0")
    private UserReadService userReadService;

    @Reference(version = "1.0.0")
    private PermissionService permissionService;

    @Override
    public final UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            final User user = userReadService.queryByName(username);
            if (null == user) {
                throw new UsernameNotFoundException(username);
            }

            UserPrincipal principal = new UserPrincipal();
            principal.setId(user.getId());
            principal.setUsername(username);
            principal.setPassword(user.getPassword());

            Set<? extends GrantedAuthority> authorities = permissionService.queryUserAuthorities(user.getId());
            if (authorities.isEmpty()) {
                principal.getAuthorities().add(new Authority(Role.ROLE_USER.name()));
            } else {
                principal.getAuthorities().addAll(authorities.stream()
                        .map(a -> new Authority(a.getAuthority()))
                        .collect(Collectors.toSet())
                );
            }

            return principal;
        } catch (Exception e) {
            throw new AuthenticationServiceException("获取用户信息错误", e);
        }
    }
}
