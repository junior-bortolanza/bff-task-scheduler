package com.bortolanza.bff_agendadortarefas.infrastructure.message.producer;


import com.bortolanza.bff_agendadortarefas.business.dto.out.TasksDTOResponse;
import com.bortolanza.bff_agendadortarefas.infrastructure.message.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendEmail(TasksDTOResponse dto) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_EMAIL, RabbitMqConfig.ROUTING_KEY, dto);
    }
}
