package com.example.demo.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * ClassName: Producer
 * Description:
 * date: 6/14/23 8:23 AM
 *
 * @author chenbo
 * @since JDK 1.8
 */
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void produce() {
        String message = new Date() + "Beijing";
        System.out.println("生产者产生消息=====" + message);
        rabbitTemplate.convertAndSend("rabbitmq_queue", message);
        }
    }
