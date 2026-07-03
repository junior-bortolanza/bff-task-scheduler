package com.bortolanza.bff_agendadortarefas.infrastructure.message.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    //Nome da fila
    public static final String QUEUE_EMAIL = "email-queue";
    //Nome da exchange
    public static final String EXCHANGE_EMAIL = "email-exchange";
    //Routing key
    public static final String ROUTING_KEY= "email-key";


    @Bean
    public Queue queueEmail() {
        return new Queue(QUEUE_EMAIL);
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(EXCHANGE_EMAIL);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queueEmail())
                .to(exchange())
                .with(ROUTING_KEY);
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
