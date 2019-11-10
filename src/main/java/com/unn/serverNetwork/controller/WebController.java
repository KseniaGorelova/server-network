package com.unn.serverNetwork.controller;

import com.unn.serverNetwork.model.Greeting;
import com.unn.serverNetwork.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebController {

    @MessageMapping("/hello")
    @SendTo("/topic/messages")
    public Greeting greeting(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting(message.getFrom(), message.getText(), "1");
    }
}
