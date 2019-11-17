package com.zhenhui.demo.tracer.uicc.service;

import com.zhenhui.demo.tracer.uicc.api.domain.Authority;
import com.zhenhui.demo.tracer.uicc.api.domain.Role;
import com.zhenhui.demo.tracer.uicc.api.service.PermissionService;
import com.zhenhui.demo.tracer.uicc.service.dal.enums.AuthorityDO;
import com.zhenhui.demo.tracer.uicc.service.dal.entity.RoleAuthorityDO;
import com.zhenhui.demo.tracer.uicc.service.dal.enums.RoleDO;
import com.zhenhui.demo.tracer.uicc.service.dal.entity.UserRoleDO;
import com.zhenhui.demo.tracer.uicc.service.dal.repository.AuthorityRepository;
import com.zhenhui.demo.tracer.uicc.service.dal.repository.RoleRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service(version = "1.0.0")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PermissionServiceImpl self;

    @CacheEvict(cacheNames = {"user-roles", "user-roles-and-authorities"}, key = "#userId")
    @Override
    public void addUserRole(Long userId, Set<Role> roles) {

        List<UserRoleDO> urs = roles.stream().map(r -> {
            UserRoleDO ur = new UserRoleDO();
            ur.setUserId(userId);
            ur.setRole(RoleDO.valueOf(r.name()));
            return ur;
        }).collect(Collectors.toList());
        roleRepository.saveAll(urs);
    }

    @CacheEvict(cacheNames = {"user-roles", "user-roles-and-authorities"}, key = "#userId")
    @Override
    public void removeUserRoles(Long userId, Set<Role> roles) {
        List<UserRoleDO> urs = roles.stream().map(r -> {
            UserRoleDO ur = new UserRoleDO();
            ur.setUserId(userId);
            ur.setRole(RoleDO.valueOf(r.name()));
            return ur;
        }).collect(Collectors.toList());
        roleRepository.deleteAll(urs);
    }

    @CacheEvict(cacheNames = "role-authorities", key = "#role")
    @Transactional(rollbackOn = Exception.class)
    @Override
    public void addRoleAuthority(Role role, Set<Authority> authorities) {

        List<RoleAuthorityDO> ras = authorities.stream().map(authority -> {
            RoleAuthorityDO ra = new RoleAuthorityDO();
            ra.setRole(RoleDO.valueOf(role.name()));
            ra.setAuthority(AuthorityDO.valueOf(authority.name()));
            return ra;
        }).collect(Collectors.toList());

        authorityRepository.saveAll(ras);
    }

    @CacheEvict(cacheNames = "role-authorities", key = "#role")
    @Transactional(rollbackOn = Exception.class)
    @Override
    public void removeRoleAuthority(Role role, Set<Authority> authorities) {
        List<RoleAuthorityDO> ras = authorities.stream().map(authority -> {
            RoleAuthorityDO ra = new RoleAuthorityDO();
            ra.setRole(RoleDO.valueOf(role.name()));
            ra.setAuthority(AuthorityDO.valueOf(authority.name()));
            return ra;
        }).collect(Collectors.toList());

        authorityRepository.deleteAll(ras);
    }

    @Cacheable(cacheNames = "user-roles", key = "#userId")
    @Override
    public Set<Role> queryUserRoles(Long userId) {

        return roleRepository.findByUserId(userId)
                .stream()
                .map(ur -> Role.valueOf(ur.getRole().name()))
                .collect(Collectors.toSet());
    }

    @Cacheable(cacheNames = "role-authorities", key = "#role")
    @Override
    public Set<Authority> queryRoleAuthorities(Role role) {

        List<RoleAuthorityDO> ras = authorityRepository.findByRole(RoleDO.valueOf(role.name()));

        return ras.stream()
                .map(ra -> Authority.valueOf(ra.getAuthority().name()))
                .collect(Collectors.toSet());
    }

    @Cacheable(cacheNames = "user-roles-and-authorities", key = "#userId")
    @Override
    public Set<? extends GrantedAuthority> queryUserAuthorities(Long userId) {

        Set<GrantedAuthority> result = new HashSet<>();

        Set<Role> roles = self.queryUserRoles(userId);
        result.addAll(roles);

        roles.forEach(role -> result.addAll(self.queryRoleAuthorities(role)));

        return result;
    }
}


