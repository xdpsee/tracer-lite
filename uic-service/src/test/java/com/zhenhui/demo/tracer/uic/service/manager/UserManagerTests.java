package com.zhenhui.demo.tracer.uic.service.manager;


import com.google.common.collect.Sets;
import com.zhenhui.demo.tracer.uic.Application;
import com.zhenhui.demo.tracer.uic.api.domain.Role;
import com.zhenhui.demo.tracer.uic.api.domain.User;
import com.zhenhui.demo.tracer.uic.api.exception.UserException;
import com.zhenhui.demo.tracer.uic.config.AppConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(AppConfig.class)
public class UserManagerTests {

    @Autowired
    private UserManager userManager;

    @Test
    public void testCreateSubUsers() throws UserException {

        User user = new User();
        user.setUsername("t0");
        user.setPassword(RandomStringUtils.randomAlphanumeric(8));

        Long userId = userManager.createUser(user, Sets.newHashSet(Role.ROLE_USER));


        User subUser = new User();
        subUser.setUsername("t0.1");
        subUser.setPassword(RandomStringUtils.randomAlphanumeric(8));

        Long subUserId = userManager.createSubUser(userId, subUser, Sets.newHashSet(Role.ROLE_USER));



    }

}
