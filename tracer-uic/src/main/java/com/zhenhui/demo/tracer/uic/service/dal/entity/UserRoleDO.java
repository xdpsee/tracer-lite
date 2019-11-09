package com.zhenhui.demo.tracer.uic.service.dal.entity;

import com.zhenhui.demo.tracer.uic.service.dal.conv.RoleDOAttrConverter;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_roles", indexes = {
        @Index(name = "uk_user_id_role_id", columnList = "user_id,role_id", unique = true)
})
public class UserRoleDO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "role_id", nullable = false)
    @Convert(converter = RoleDOAttrConverter.class)
    private RoleDO role;
}

