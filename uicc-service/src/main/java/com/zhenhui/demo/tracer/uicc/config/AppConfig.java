package com.zhenhui.demo.tracer.uicc.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableCaching
@EntityScan("com.zhenhui.demo.tracer.uicc.service.dal.entity")
@EnableJpaRepositories(basePackages = "com.zhenhui.demo.tracer.uicc.service.dal.repository")
public class AppConfig {
}
