package com.zhenhui.demo.tracer.webapp.websocket;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.TimerTask;

@Controller
public class GreetingController implements InitializingBean {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        System.out.println("收到：" + message.toString() + "消息");
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        new Thread(() -> {

            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // ignore
                }

                //messagingTemplate.convertAndSendToUser("test", "/topic/greetings", new Greeting(String.valueOf(System.currentTimeMillis())));
            }

        }).start();

    }
}
