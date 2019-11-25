package com.zhenhui.demo.tracer.uicc;

import com.zhenhui.demo.tracer.uicc.api.domain.Role;
import com.zhenhui.demo.tracer.uicc.api.domain.User;
import com.zhenhui.demo.tracer.uicc.api.service.UserQueryService;
import com.zhenhui.demo.tracer.uicc.api.service.UserWriteService;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
public class TestUserGen implements CommandLineRunner {

    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private UserWriteService userWriteService;

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setLocked(false);

        if (null == userQueryService.queryByName("test")) {
            userWriteService.createUser(user, Sets.newTreeSet(Role.ROLE_USER));
        }
        user = new User();
        user.setUsername("zhcen");
        user.setPassword("123456");
        user.setLocked(false);

        if (null == userQueryService.queryByName("zhcen")) {

            userWriteService.createUser(user, Sets.newTreeSet(Role.ROLE_USER));
        }
        user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setLocked(false);

        if (null == userQueryService.queryByName("admin")) {

            userWriteService.createUser(user, Sets.newTreeSet(Role.ROLE_USER, Role.ROLE_ADMIN));
        }
    }
}
