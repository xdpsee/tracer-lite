package com.zhenhui.demo.tracer.webapi.restful.token;

import com.zhenhui.demo.tracer.webapi.restful.common.Result;
import com.zhenhui.demo.tracer.webapi.security.UserPrincipal;
import com.zhenhui.demo.tracer.webapi.utils.Timestamp;
import com.zhenhui.demo.tracer.webapi.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/auth/token")
public class AuthTokenController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @PostMapping(path = "/claim")
    public Result claim(@RequestBody TokenClaimParam param) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(param.getUsername(), param.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

        return Result.builder()
                .status(HttpStatus.OK.value())
                .data(tokenUtils.generate(user))
                .message("OK")
                .timestamp(Timestamp.now())
                .build();
    }

    @PostMapping(path = "/refresh")
    public Result refresh(HttpServletRequest request) {

        final String token = tokenUtils.extractToken(request);
        if (StringUtils.isEmpty(token)) {
            return Result.builder()
                    .status(HttpStatus.UNAUTHORIZED.value())
                    .timestamp(Timestamp.now())
                    .message("令牌未提交")
                    .build();
        }

        UserPrincipal user = tokenUtils.parse(token);

        return Result.builder()
                .status(HttpStatus.OK.value())
                .message("OK")
                .timestamp(Timestamp.now())
                .data(tokenUtils.generate(user))
                .build();
    }

    @PostMapping(path = "/invalid")
    public Result invalid(HttpServletRequest request) {

        final String token = tokenUtils.extractToken(request);
        if (StringUtils.isEmpty(token)) {
            return Result.builder()
                    .status(HttpStatus.UNAUTHORIZED.value())
                    .timestamp(Timestamp.now())
                    .message("令牌未提交")
                    .build();
        }


        return Result.builder()
                .status(HttpStatus.OK.value())
                .message("OK")
                .timestamp(Timestamp.now())
                .build();
    }

}

