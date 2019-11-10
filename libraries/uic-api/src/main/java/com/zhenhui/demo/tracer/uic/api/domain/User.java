package com.zhenhui.demo.tracer.uic.api.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Long id;

    private String username;

    private String password;

    private Boolean locked = false;
}

