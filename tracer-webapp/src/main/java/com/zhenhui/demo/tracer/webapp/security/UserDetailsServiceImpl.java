package com.zhenhui.demo.tracer.webapp.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if ("test".equals(username)) {
            UserPrincipal principal = new UserPrincipal();
            principal.setId(1L);
            principal.setUsername(username);
            principal.setPassword("123456");
            principal.getAuthorities().add(Authority.NORMAL);
            principal.getAuthorities().add(Authority.SUPER);
            return principal;
        }

        throw new UsernameNotFoundException(username);
    }
}

