package com.example.demo.consumer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: Consumer
 * Description:
 * date: 6/14/23 8:21 AM
 *
 * @author chenbo
 * @since JDK 1.8
 */
@Component
public class Consumer {
    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("rabbitmq_queue"))
    public void process(String message) {
        System.out.println("消费者消费消息111=====" + message);
    }
}
