package com.zhenhui.demo.tracer.security;

import com.zhenhui.demo.tracer.uic.api.domain.Role;
import com.zhenhui.demo.tracer.uic.api.domain.User;
import com.zhenhui.demo.tracer.uic.api.service.PermissionService;
import com.zhenhui.demo.tracer.uic.api.service.UserQueryService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class UserDetailsServiceSupport implements UserDetailsService {

    protected abstract UserQueryService userQueryService();

    protected abstract PermissionService permissionService();

    @Override
    public final UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userQueryService().queryByName(username);
        if (null == user) {
            throw new UsernameNotFoundException(username);
        }

        UserPrincipal principal = new UserPrincipal();
        principal.setId(user.getId());
        principal.setUsername(username);
        principal.setPassword(user.getPassword());

        Set<? extends GrantedAuthority> authorities = permissionService().queryUserAuthorities(user.getId());
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
