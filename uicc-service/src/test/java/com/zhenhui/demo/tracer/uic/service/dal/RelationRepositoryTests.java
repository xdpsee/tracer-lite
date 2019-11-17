package com.zhenhui.demo.tracer.uicc.service.dal;

import com.zhenhui.demo.tracer.uicc.Application;
import com.zhenhui.demo.tracer.uicc.config.AppConfig;
import com.zhenhui.demo.tracer.uicc.service.dal.entity.RelationDO;
import com.zhenhui.demo.tracer.uicc.service.dal.repository.RelationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(AppConfig.class)
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
