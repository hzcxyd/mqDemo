package com.example.demo.consumer;

import com.example.demo.model.BusinessClass;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author:JZ
 * @date:2020/2/2
 */

@Component
public class TopConsumer {

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = "TopicA",durable = "true"),
            exchange = @Exchange(value = "TopicExchange",durable = "true",type = ExchangeTypes.TOPIC),
            key = "test.#"
    )})
    @RabbitHandler
    public void processA(Map map, Channel channel, Message message) throws IOException {
        System.out.println("收到的TopicA队列的消息是："+map.toString());
        BusinessClass businessClass = (BusinessClass) map.get("data");
        businessClass.getId();
        businessClass.getName();
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = "TopicB",durable = "true"),
            exchange = @Exchange(value = "TopicExchange",durable = "true",type = ExchangeTypes.TOPIC),
            key = "test.*"
    )})
    @RabbitHandler
    public void processB(Map map, Channel channel, Message message) throws IOException {
        System.out.println("收到的TopicB队列的消息是："+map.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = "TopicC",durable = "true"),
            exchange = @Exchange(value = "TopicExchange",durable = "true",type = ExchangeTypes.TOPIC),
            key = "test.topic"
    )})
    @RabbitHandler
    public void processC(Map map, Channel channel, Message message) throws IOException {
        System.out.println("收到的TopicC队列的消息是："+map.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}

