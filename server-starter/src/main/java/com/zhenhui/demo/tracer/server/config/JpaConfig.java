package com.zhenhui.demo.tracer.server.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.zhenhui.demo.tracer.storage.service.dal.entity")
@EnableJpaRepositories(basePackages = "com.zhenhui.demo.tracer.storage.service.dal.repository")
public class JpaConfig {
}
