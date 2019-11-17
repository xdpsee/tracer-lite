package com.zhenhui.demo.tracer.uicc.service.dal.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "users", indexes = {
        @Index(name = "uk_username", columnList = "username", unique = true)
})
@Entity
public class UserDO implements Serializable {

    private static final long serialVersionUID = -5262320981076747370L;

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "bool")
    private Boolean locked = false;

    @Column(nullable = false, columnDefinition = "bool")
    private Boolean deleted = false;
}

