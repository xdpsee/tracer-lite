package com.zhenhui.demo.tracer;

import io.netty.util.ResourceLeakDetector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.zhenhui.demo.tracer.storage.service.dal.entity")
@EnableJpaRepositories(basePackages = "com.zhenhui.demo.tracer.storage.service.dal.repository")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);

    }

}
