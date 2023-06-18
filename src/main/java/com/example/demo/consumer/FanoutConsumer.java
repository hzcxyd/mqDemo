package com.example.demo.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class FanoutConsumer {
    //这里把两种@RabbitListener的注解都写出来了，这两种写法都要认得，第二种写法比较健壮
    //@RabbitListener(queues = "FanoutA")
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = "FanoutA",durable = "true"),
            exchange = @Exchange(value = "FanoutExchange",durable = "true",type = "fanout"),
            key = ""
    )})
    @RabbitHandler
    public void processA(Map map, Channel channel, Message message) throws IOException {
        System.out.println("收到的FanoutA队列的消息是："+map.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = "FanoutB",durable = "true"),
            exchange = @Exchange(value = "FanoutExchange",durable = "true",type = "fanout"),
            key = ""
    )})
    @RabbitHandler
    public void processB(Map map, Channel channel, Message message) throws IOException {
        System.out.println("收到的FanoutB队列的消息是："+map.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = "FanoutC",durable = "true"),
            exchange = @Exchange(value = "FanoutExchange",durable = "true",type = "fanout"),
            key = ""
    )})
    @RabbitHandler
    public void processC(Map map, Channel channel, Message message) throws IOException {
        System.out.println("收到的FanoutC队列的消息是："+map.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}