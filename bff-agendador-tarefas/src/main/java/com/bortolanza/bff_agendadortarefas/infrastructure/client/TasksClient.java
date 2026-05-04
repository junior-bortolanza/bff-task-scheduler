package com.bortolanza.bff_agendadortarefas.infrastructure.client;


import com.bortolanza.bff_agendadortarefas.business.dto.in.TasksDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.out.TasksDTOResponse;
import com.bortolanza.bff_agendadortarefas.business.enums.StatusNotificationEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tasks-scheduler", url = "${tasks-scheduler.url}")
public interface TasksClient {


    @PostMapping
    TasksDTOResponse save(@RequestBody TasksDTORequest dto, @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TasksDTOResponse> searchListTasksForPeriod(
            @RequestParam("initialDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialDate,
            @RequestParam("finalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
            @RequestHeader("Authorization") String token);

    @GetMapping
   List<TasksDTOResponse> searchTasksByEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deleteTaskById(@RequestParam("id") String id, @RequestHeader("Authorization") String token);

    @PatchMapping
    TasksDTOResponse changingStatusNotification(@RequestParam("status") StatusNotificationEnum status,
                                                @RequestParam("id") String id,
                                                @RequestHeader("Authorization") String token);

    @PutMapping
    TasksDTOResponse updateTasks(@RequestBody TasksDTORequest dto,
                                 @RequestParam("id") String id,
                                 @RequestHeader("Authorization") String token);
}
