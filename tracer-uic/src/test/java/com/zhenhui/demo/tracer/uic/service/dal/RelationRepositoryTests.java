package com.zhenhui.demo.tracer.uic.service.dal;

import com.zhenhui.demo.tracer.uic.service.dal.entity.RelationDO;
import com.zhenhui.demo.tracer.uic.service.dal.repository.RelationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class RelationRepositoryTests {

    @Autowired
    private RelationRepository relationRepository;


    @Test
    public void testInsert() {

        addRelation(0, 1);
        addRelation(1,2);
        addRelation(1,3);
        addRelation(2,4);
        addRelation(2,5);
        addRelation(3, 6);
        addRelation(3, 7);
        addRelation(7,8);

        List<RelationDO> relations = relationRepository.findAll();

        System.out.println(relations);

    }

    @Test(expected = Exception.class)
    public void testUnique() {
        addRelation(0, 1);
        addRelation(0, 1);
    }

    private void addRelation(long parentId, long userId) {

        RelationDO relation = new RelationDO();

        relation.setAncestorId(userId);
        relation.setDescendantId(userId);
        relation.setDepth(0);

        relationRepository.save(relation);

        List<RelationDO> parentRelations = relationRepository.findByDescendantId(parentId);
        for (RelationDO parentRelation : parentRelations) {
            if (!parentRelation.equals(relation)) {
                RelationDO rel = new RelationDO();
                rel.setAncestorId(parentRelation.getAncestorId());
                rel.setDescendantId(userId);
                rel.setDepth(parentRelation.getDepth() + 1);
                relationRepository.save(rel);
            }
        }
    }
}
