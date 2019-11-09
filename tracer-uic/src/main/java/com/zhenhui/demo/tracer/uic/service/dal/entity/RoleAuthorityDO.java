package com.zhenhui.demo.tracer.uic.service.dal.entity;

import com.zhenhui.demo.tracer.uic.service.dal.conv.AuthorityDOAttrConverter;
import com.zhenhui.demo.tracer.uic.service.dal.conv.RoleDOAttrConverter;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role_authorities", indexes = {
        @Index(name = "uk_role_id_authority_id", columnList = "role_id,authority_id", unique = true)
})
public class RoleAuthorityDO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "role_id", nullable = false)
    @Convert(converter = RoleDOAttrConverter.class)
    private RoleDO role;

    @Column(name = "authority_id", nullable = false)
    @Convert(converter = AuthorityDOAttrConverter.class)
    private AuthorityDO authority;

}

