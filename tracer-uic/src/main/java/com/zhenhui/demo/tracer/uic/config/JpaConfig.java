package com.zhenhui.demo.tracer.uic.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.zhenhui.demo.tracer.uic.service.dal.entity")
@EnableJpaRepositories(basePackages = "com.zhenhui.demo.tracer.uic.service.dal.repository")
public class JpaConfig {
}
