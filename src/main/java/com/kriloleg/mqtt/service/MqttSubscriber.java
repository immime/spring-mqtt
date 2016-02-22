package com.kriloleg.mqtt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class MqttSubscriber {
    private final String destination = "/socket/topic";

    @Autowired
    @Qualifier("brokerMessagingTemplate")
    private MessageSendingOperations<String> messageSendingOperations;

    public void sendToSocket(String message) {
        messageSendingOperations.convertAndSend(destination, message);
    }
}
