package com.zhenhui.demo.tracer.webapi.security;

import com.zhenhui.demo.tracer.common.utils.JsonUtils;
import com.zhenhui.demo.tracer.security.TokenException;
import com.zhenhui.demo.tracer.webapi.common.Result;
import com.zhenhui.demo.tracer.webapi.utils.Timestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (TokenException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            //noinspection deprecation
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(JsonUtils.toJsonString(Result.builder()
                    .status(HttpStatus.UNAUTHORIZED.value())
                    .message(e.getMessage())
                    .timestamp(Timestamp.now())
                    .build()));
            response.getWriter().close();
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            //noinspection deprecation
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(JsonUtils.toJsonString(Result.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .timestamp(Timestamp.now())
                    .build()));
            response.getWriter().close();
        }
    }
}
