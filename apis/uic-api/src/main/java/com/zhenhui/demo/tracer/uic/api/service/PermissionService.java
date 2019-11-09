package com.zhenhui.demo.tracer.uic.api.service;

import com.zhenhui.demo.tracer.uic.api.domain.Authority;
import com.zhenhui.demo.tracer.uic.api.domain.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public interface PermissionService {

    void addUserRole(Long userId, Set<Role> roles);

    void removeUserRoles(Long userId, Set<Role> roles);

    void addRoleAuthority(Role role, Set<Authority> authorities);

    void removeRoleAuthority(Role role, Set<Authority> authorities);

    Set<Role> queryUserRoles(Long userId);

    Set<Authority> queryRoleAuthorities(Role role);

    Set<? extends GrantedAuthority> queryUserAuthorities(Long userId);


}
