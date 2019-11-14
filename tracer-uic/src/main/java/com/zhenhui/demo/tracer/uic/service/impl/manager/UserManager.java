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

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        return null;
    }

    public List<User> getAllSubUsers(Long userId) {
        return null;
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
        if (!userRepository.existsById(userId)) {
            throw new UserException(String.format("user %d not found!", userId));
        }


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
        }).collect(Collectors.toList()));

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

}
