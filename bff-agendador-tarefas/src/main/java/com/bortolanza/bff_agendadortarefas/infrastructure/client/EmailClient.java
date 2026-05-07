package com.bortolanza.bff_agendadortarefas.infrastructure.client;


import com.bortolanza.bff_agendadortarefas.business.dto.out.TasksDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notification", url = "${notification.url}")
public interface EmailClient {

    @PostMapping
    void sendEmail(@RequestBody TasksDTOResponse dto);
}
