package com.zhenhui.demo.tracer.uic.service.impl.manager;

import com.zhenhui.demo.tracer.uic.api.domain.Role;
import com.zhenhui.demo.tracer.uic.api.domain.User;
import com.zhenhui.demo.tracer.uic.api.exception.UserException;
import com.zhenhui.demo.tracer.uic.service.dal.entity.RelationDO;
import com.zhenhui.demo.tracer.uic.service.dal.entity.UserDO;
import com.zhenhui.demo.tracer.uic.service.dal.entity.UserRoleDO;
import com.zhenhui.demo.tracer.uic.service.dal.enums.RoleDO;
import com.zhenhui.demo.tracer.uic.service.dal.repository.RelationRepository;
import com.zhenhui.demo.tracer.uic.service.dal.repository.RoleRepository;
import com.zhenhui.demo.tracer.uic.service.dal.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Component
public class UserManager {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RelationRepository relationRepository;

    public User getByName(String username) {

        Optional<UserDO> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User result = new User();
            BeanUtils.copyProperties(user.get(), result);
            return result;
        }

        return null;
    }

    public User getById(Long userId) {
        Optional<UserDO> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User result = new User();
            BeanUtils.copyProperties(user.get(), result);
            return result;
        }

        return null;
    }

    public List<User> getDirectSubUsers(Long userId) {

        List<RelationDO> relations = relationRepository.findByAncestorIdAndDepthOrderByDepthAsc(userId, 1);
        return getSubUsersFromRelations(relations);
    }

    public List<User> getAllSubUsers(Long userId) {
        List<RelationDO> relations = relationRepository.findByAncestorIdOrderByDepthAsc(userId);
        return getSubUsersFromRelations(relations);
    }

    @Transactional(rollbackOn = Exception.class)
    public Long createUser(User user, Set<Role> roles) {

        if (null == user) {
            throw new IllegalArgumentException("user == null");
        }

        final UserDO userDO = saveUser(user, roles);

        RelationDO relation = new RelationDO();
        relation.setAncestorId(0L);
        relation.setDescendantId(userDO.getId());
        relation.setDepth(0);
        relationRepository.save(relation);

        return userDO.getId();
    }

    @Transactional(rollbackOn = Exception.class)
    public Long createSubUser(Long userId, User subUser, Set<Role> roles) throws UserException {

        if (!userRepository.existsById(userId)) {
            throw new UserException(String.format("user %d not found!", userId));
        }

        UserDO userDO = saveUser(subUser, roles);

        saveRelation(userId, userDO.getId());

        return userDO.getId();
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeSubUser(Long userId, Long subUserId) throws UserException {
        if (!userRepository.existsById(subUserId)) {
            throw new UserException(String.format("user %d not found!", subUserId));
        }

        Optional<RelationDO> relation = relationRepository.findByAncestorIdAndDescendantId(userId, subUserId);
        if (!relation.isPresent()) {
            throw new UserException(String.format("users %d, %d have no relation!", userId, subUserId));
        }

        List<RelationDO> all = relationRepository.findByAncestorId(subUserId);
        relationRepository.deleteInBatch(all);

        List<Long> allSubUserIds = all.stream().map(RelationDO::getDescendantId).distinct().collect(toList());
        List<UserDO> allSubUsers = userRepository.findAllById(allSubUserIds);
        allSubUsers.forEach(u -> u.setDeleted(true));
        userRepository.saveAll(allSubUsers);
    }

    private UserDO saveUser(User user, Set<Role> roles) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(user, userDO);

        UserDO result = userRepository.save(userDO);

        roleRepository.saveAll(roles.stream().map(role -> {
            UserRoleDO ur = new UserRoleDO();
            ur.setUserId(result.getId());
            ur.setRole(RoleDO.valueOf(role.name()));
            return ur;
        }).collect(toList()));

        return result;
    }

    private void saveRelation(long parentId, long userId) {

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

    private List<User> getSubUsersFromRelations(List<RelationDO> relations) {

        List<Long> userIds = relations.stream().map(RelationDO::getDescendantId).distinct().collect(toList());
        if (!CollectionUtils.isEmpty(userIds)) {
            List<UserDO> userDOs = userRepository.findAllById(userIds);
            return userDOs.stream().map(u -> {
                User user = new User();
                BeanUtils.copyProperties(u, user);
                return user;
            }).collect(toList());
        }

        return new ArrayList<>();

    }
}
