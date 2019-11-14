package com.zhenhui.demo.tracer.uic.api.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 5784337593266557053L;

    private Long id;

    private String username;

    private String password;

    private Boolean locked = false;
}

