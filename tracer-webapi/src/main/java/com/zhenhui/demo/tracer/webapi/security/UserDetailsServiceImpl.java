package com.zhenhui.demo.tracer.webapi.security;

import com.zhenhui.demo.tracer.security.UserPrincipal;
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


        return user;
    }
}

