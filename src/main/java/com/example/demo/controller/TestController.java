package com.example.demo.controller;

import com.example.demo.model.BusinessClass;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class TestController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage")
    public String sendDirectMessage(){
        String messageId = UUID.randomUUID().toString();
        String messageData = "test message,hello!";
        String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Map<String,Object> map = new HashMap<>();
        map.put("messageId",messageId);
        map.put("data",messageData);
        map.put("current",current);
        rabbitTemplate.convertAndSend("TestDirectExchange", "123", map, new CorrelationData(UUID.randomUUID().toString()));
        return "ok";
    }

    @GetMapping("/sendMessage2")
    public String sendFanoutMessage(){
        String messageId = UUID.randomUUID().toString();
        String messageData = "test message of fanout,hello!";
        String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Map<String,Object> map = new HashMap<>();
        map.put("messageId",messageId);
        map.put("data",messageData);
        map.put("current",current);
        rabbitTemplate.convertAndSend("FanoutExchange", "", map, new CorrelationData(UUID.randomUUID().toString()));
        return "ok";
    }

    @GetMapping("/sendMessage3")
    public String sendTopicMessage(){
        String messageId = UUID.randomUUID().toString();
        BusinessClass businessClass = new BusinessClass();
        businessClass.setId("1");
        businessClass.setName("jason");
        businessClass.setContent("this is topic test");
        String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Map<String,Object> map = new HashMap<>();
        map.put("messageId",messageId);
        map.put("data",businessClass);
        map.put("current",current);
        rabbitTemplate.convertAndSend("TopicExchange", "test.topic.a", map, new CorrelationData(UUID.randomUUID().toString()));
        return "ok";
    }


}

