package com.zhenhui.demo.tracer.webapi.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserPrincipal user = new UserPrincipal();
        user.setUsername(username);
        user.setPassword("123456");

        if (username.equals("zhcen")) {
            user.setId(1L);
            user.setAuthorities(Sets.newHashSet(UserAuthority.NORMAL));
        } else if (username.equals("admin")) {
            user.setId(2L);
            user.setAuthorities(Sets.newHashSet(UserAuthority.ADMIN));
        } else {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }
}

