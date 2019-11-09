package com.zhenhui.demo.tracer.webapp.security;

import com.zhenhui.demo.tracer.security.Authority;
import com.zhenhui.demo.tracer.security.UserPrincipal;
import com.zhenhui.demo.tracer.uic.api.domain.Role;
import com.zhenhui.demo.tracer.uic.api.domain.User;
import com.zhenhui.demo.tracer.uic.api.service.PermissionService;
import com.zhenhui.demo.tracer.uic.api.service.UserQueryService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference(version = "1.0.0")
    private UserQueryService userQueryService;

    @Reference(version = "1.0.0")
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final User user = userQueryService.queryByName(username);
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
    }
}

