package com.kriloleg.mqtt.config;

import com.kriloleg.mqtt.service.MqttSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@ComponentScan("com.kriloleg.mqtt.service")
public class MqttInputConfig {

    @Autowired
    private MqttSubscriber subscriber;

    @Bean
    public MessageChannel inputChanel() {
        return new DirectChannel();
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter inputChannelAdapter() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter("tcp://localhost:1883","test_user", "testTopic");
        adapter.setOutputChannel(inputChanel());
        adapter.setConverter(new DefaultPahoMessageConverter());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "inputChanel")
    public MessageHandler mqttInbound() {
        return message -> {
            subscriber.sendToSocket(message.getPayload().toString());
            System.out.println(message.getPayload());
        };
    }
}
