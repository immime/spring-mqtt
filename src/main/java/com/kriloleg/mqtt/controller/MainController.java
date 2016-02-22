package com.kriloleg.mqtt.controller;

import com.kriloleg.mqtt.config.MqttOutputConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private MqttOutputConfig.MyGateway gateway;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "index.html";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public void sendMessageOverHttp(@RequestParam String message) {
        gateway.sendToMqtt(message);
    }
}
