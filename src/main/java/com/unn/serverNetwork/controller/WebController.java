package com.unn.serverNetwork.controller;

import com.unn.serverNetwork.model.ServerMessage;
import com.unn.serverNetwork.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebController {

    @MessageMapping("/hello")
    @SendTo("/topic/messages")
    public ServerMessage greeting(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ServerMessage(message.getFrom(), message.getStatus(),
                message.getIdNeA(), message.getIdNeZ() ,"1");
    }
}
