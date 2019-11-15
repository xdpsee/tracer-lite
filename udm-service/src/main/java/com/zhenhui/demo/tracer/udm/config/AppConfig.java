package com.zhenhui.demo.tracer.udm.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableCaching
@EntityScan("com.zhenhui.demo.tracer.udm.service.dal.entity")
@EnableJpaRepositories(basePackages = "com.zhenhui.demo.tracer.udm.service.dal.repository")
public class AppConfig {
}

