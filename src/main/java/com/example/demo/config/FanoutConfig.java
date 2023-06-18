package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    //创建FanoutExchange
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("FanoutExchange",true,false);
    }

    //创建队列A
    @Bean
    Queue queueA(){
        return new Queue("FanoutA",true,false,false);
    }

    //创建队列B
    @Bean
    Queue queueB(){
        return new Queue("FanoutB",true,false,false);
    }

    //创建队列C
    @Bean
    Queue queueC(){
        return new Queue("FanoutC",true,false,false);
    }

    //将创建的队列绑定到创建的交换机上
    @Bean
    Binding bindingA(){
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }
    @Bean
    Binding bindingB(){
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }
    @Bean
    Binding bindingC(){
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }
}


