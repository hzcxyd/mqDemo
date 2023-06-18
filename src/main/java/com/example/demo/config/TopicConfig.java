package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("TopicExchange",true,false);
    }

    @Bean
    Queue TopicqueueA(){
        return new Queue("TopicA",true,false,false);
    }

    @Bean
    Queue TopicqueueB(){
        return new Queue("TopicB",true,false,false);
    }

    @Bean
    Queue TopicqueueC(){
        return new Queue("TopicC",true,false,false);
    }

    @Bean
    Binding TopicbindingA(){
        return BindingBuilder.bind(TopicqueueA()).to(topicExchange()).with("test.#");
    }
    @Bean
    Binding TopicbindingB(){
        return BindingBuilder.bind(TopicqueueB()).to(topicExchange()).with("test.*");
    }

    @Bean
    Binding TopicbindingC(){
        return BindingBuilder.bind(TopicqueueC()).to(topicExchange()).with("test.topic");
    }
}