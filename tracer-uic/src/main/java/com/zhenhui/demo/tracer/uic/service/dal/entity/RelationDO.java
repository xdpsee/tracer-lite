package com.zhenhui.demo.tracer.uic.service.dal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_relations", indexes = {
        @Index(name = "uk_ancestor_id_descendant_id", columnList = "ancestor_id,descendant_id", unique = true)
})
@EqualsAndHashCode
public class RelationDO implements Serializable {

    private static final long serialVersionUID = 6727384356925901639L;

    @Id
    @GeneratedValue
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "ancestor_id", nullable = false)
    private Long ancestorId;

    @EqualsAndHashCode.Include
    @Column(name = "descendant_id", nullable = false)
    private Long descendantId;

    @Column(name = "depth")
    private int depth;
}



