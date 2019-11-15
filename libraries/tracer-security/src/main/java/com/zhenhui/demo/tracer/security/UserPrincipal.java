package com.zhenhui.demo.tracer.security;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserPrincipal implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private final Set<Authority> authorities = new HashSet<>();

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

}
