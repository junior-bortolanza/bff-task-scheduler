package com.bortolanza.bff_agendadortarefas.controller;

import com.bortolanza.bff_agendadortarefas.business.TasksService;
import com.bortolanza.bff_agendadortarefas.business.dto.in.TasksDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.out.TasksDTOResponse;
import com.bortolanza.bff_agendadortarefas.business.enums.StatusNotificationEnum;
import com.bortolanza.bff_agendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TasksController {

    private final TasksService tasksService;

    @PostMapping
    @Operation(summary = "Salvar Tarefas de Usuários", description = "Cria uma nova tarefa!")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public ResponseEntity<TasksDTOResponse> save(@RequestBody TasksDTORequest dto,
                                                 @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.saveTask(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca Tarefas por Período", description = "Busca tarefas cadastras por período!")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public ResponseEntity<List<TasksDTOResponse>> searchListTasksForPeriod(
            @RequestParam("initialDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialDate,
            @RequestParam("finalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.searchScheduledTasksByPeriod(initialDate, finalDate, token));
    }

    @GetMapping
    @Operation(summary = "Busca lista de Tarefas por email de usuário",
               description = "Busca tarefas cadastras por usuário!")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public ResponseEntity<List<TasksDTOResponse>> searchTasksByEmail(@RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tasksService.searchTasksByEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Delte tarefaas por Id", description = "Deleta tarefas cadastradas por Id")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public ResponseEntity<Void> deleteTaskById(@RequestParam("id") String id,
                                               @RequestHeader(name = "Authorization", required = false) String token) {
        tasksService.deleteTaskById(id,token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status de tarefas", description = "Altera status das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public ResponseEntity<TasksDTOResponse> changingStatusNotification(@RequestParam("status") StatusNotificationEnum status,
                                                                       @RequestParam("id") String id,
                                                                       @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.changeStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados de tarefas", description = "Altera status das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefas alteradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
     public ResponseEntity<TasksDTOResponse> updateTasks(@RequestBody TasksDTORequest dto,
                                                         @RequestParam("id") String id,
                                                         @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.updateTasks(dto, id, token));

    }
}