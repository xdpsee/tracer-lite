package com.zhenhui.demo.tracer.webapi.security;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;

@Data
public class UserPrincipal implements UserDetails {

    private Long id;

    private HashSet<UserAuthority> authorities = new HashSet<>();

    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;
}

