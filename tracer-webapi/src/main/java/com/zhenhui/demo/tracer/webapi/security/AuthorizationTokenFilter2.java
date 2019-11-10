package com.zhenhui.demo.tracer.webapi.security;

import com.zhenhui.demo.tracer.common.utils.JsonUtils;
import com.zhenhui.demo.tracer.security.TokenUtils;
import com.zhenhui.demo.tracer.security.UserPrincipal;
import com.zhenhui.demo.tracer.webapi.common.Result;
import com.zhenhui.demo.tracer.webapi.utils.Timestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationTokenFilter2 extends OncePerRequestFilter {

    private final AntPathRequestMatcher requestMatcher;

    public AuthorizationTokenFilter2(String requestUrlPattern) {
        this.requestMatcher = new AntPathRequestMatcher(requestUrlPattern);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String token = TokenUtils.extractToken(request);
        if (requestMatcher.matches(request)) {
            try {
                UserPrincipal user = TokenUtils.parse(token);
                SecurityContextHolder.getContext()
                        .setAuthentication(new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities()));
            } catch (Exception e) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                //noinspection deprecation
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.getWriter().write(JsonUtils.toJsonString(Result.builder()
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .message(e.getMessage())
                        .timestamp(Timestamp.now())
                        .build()));
                response.getWriter().close();
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
