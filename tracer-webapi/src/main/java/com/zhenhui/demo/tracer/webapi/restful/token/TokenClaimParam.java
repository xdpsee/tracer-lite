package com.zhenhui.demo.tracer.webapi.restful.token;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TokenClaimParam {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
