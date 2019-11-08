package com.zhenhui.demo.tracer.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class AuthorizationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String token = getTokenFromCookies(request);
        if (StringUtils.isEmpty(token)) {
            token = getTokenFromHeaders(request);
        }

        if (!StringUtils.isEmpty(token)) {
            UserPrincipal principal = TokenUtils.parse(token);

            if (principal != null) {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal.getUsername(), null, principal.getAuthorities()));
            }
        }

        chain.doFilter(request, response);
    }

    private String getTokenFromCookies(HttpServletRequest request) throws UnsupportedEncodingException {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Authorization")) {
                String tokenValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
                if (tokenValue != null) {
                    if (tokenValue.startsWith("Bearer ")) {
                        String[] components = tokenValue.split("\\s+");
                        if (components.length == 2) {
                            return components[1];
                        }
                    }
                }
            }
        }

        return null;
    }

    private String getTokenFromHeaders(HttpServletRequest request) throws UnsupportedEncodingException {
        return TokenUtils.extractToken(request);
    }

}

