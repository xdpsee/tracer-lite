package com.zhenhui.demo.tracer.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

    public Map<String, Object> info() {

        Map<String, Object> result = new HashMap<>();

        result.put("websocket", true);
        result.put("cookie_needed", false);
        result.put("origins", "*:*");
        result.put("entropy", System.currentTimeMillis());

        return result;
    }
}

