package com.zhenhui.demo.tracer.uic;


import com.alibaba.nacos.client.utils.JSONUtils;
import com.zhenhui.demo.tracer.uic.api.service.PermissionService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@EnableDubbo
@EnableCaching
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private PermissionService permissionService;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Set<? extends GrantedAuthority> authorities = permissionService.queryUserAuthorities(7L);
        System.out.println(JSONUtils.serializeObject(authorities));
    }

}

