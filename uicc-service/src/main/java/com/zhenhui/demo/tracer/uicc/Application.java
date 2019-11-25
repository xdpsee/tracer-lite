package com.zhenhui.demo.tracer.uicc;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@EnableDubbo
@EnableCaching
@SpringBootApplication
public class Application {

    @Autowired

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}

