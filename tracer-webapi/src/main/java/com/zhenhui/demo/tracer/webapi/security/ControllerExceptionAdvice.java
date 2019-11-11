package com.zhenhui.demo.tracer.webapi.security;

import com.zhenhui.demo.tracer.security.TokenException;
import com.zhenhui.demo.tracer.webapi.common.Result;
import com.zhenhui.demo.tracer.webapi.restful.exception.ServiceException;
import com.zhenhui.demo.tracer.webapi.utils.Timestamp;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result usernameNotFoundException() {

        return Result.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("用户不存在")
                .timestamp(Timestamp.now())
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result badCredentialsException() {

        return Result.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("密码错误")
                .timestamp(Timestamp.now())
                .build();
    }

    @ExceptionHandler(TokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result tokenException(TokenException e) {

        return Result.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .timestamp(Timestamp.now())
                .build();
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result serviceException(ServiceException e) {

        return Result.builder()
                .status(e.errorCode.code)
                .message(e.getMessage())
                .timestamp(Timestamp.now())
                .build();

    }
}

