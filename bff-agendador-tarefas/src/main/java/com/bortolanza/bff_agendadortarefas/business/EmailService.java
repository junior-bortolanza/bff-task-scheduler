package com.bortolanza.bff_agendadortarefas.business;

import com.bortolanza.bff_agendadortarefas.business.dto.out.TasksDTOResponse;
import com.bortolanza.bff_agendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailClient emailClient;

    public void sendEmail(TasksDTOResponse dto) {
         emailClient.sendEmail(dto);
    }

}
