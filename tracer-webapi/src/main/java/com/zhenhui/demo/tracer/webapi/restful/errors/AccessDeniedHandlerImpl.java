package com.zhenhui.demo.tracer.webapi.restful.errors;

import com.zhenhui.demo.tracer.common.utils.JsonUtils;
import com.zhenhui.demo.tracer.webapi.common.Result;
import com.zhenhui.demo.tracer.webapi.utils.Timestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

        //noinspection deprecation
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JsonUtils.toJsonString(Result.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(e.getMessage())
                .timestamp(Timestamp.now())
                .build()));
        response.getWriter().close();

    }
}
