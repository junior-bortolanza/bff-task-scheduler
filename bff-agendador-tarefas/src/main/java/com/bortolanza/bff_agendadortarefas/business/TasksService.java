package com.bortolanza.bff_agendadortarefas.business;

import com.bortolanza.bff_agendadortarefas.business.dto.in.TasksDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.out.TasksDTOResponse;

import com.bortolanza.bff_agendadortarefas.business.enums.StatusNotificationEnum;
import com.bortolanza.bff_agendadortarefas.infrastructure.client.TasksClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final TasksClient tasksClient;

    public TasksDTOResponse saveTask(String token, TasksDTORequest dto) {
        return tasksClient.save(dto, token);
    }

    public List<TasksDTOResponse> searchScheduledTasksByPeriod(LocalDateTime initialDate,
                                                               LocalDateTime finalDate,
                                                               String token) {
        return tasksClient.searchListTasksForPeriod(initialDate, finalDate, token);
    }

    public List<TasksDTOResponse> searchTasksByEmail(String token) {
      return tasksClient.searchTasksByEmail(token);
    }

    public void deleteTaskById(String id, String token) {
        tasksClient.deleteTaskById(id, token);
    }

    public TasksDTOResponse changeStatus(StatusNotificationEnum status, String id, String token) {
        return tasksClient.changingStatusNotification(status, id, token);
    }

    public TasksDTOResponse updateTasks(TasksDTORequest dto, String id, String token) {
       return tasksClient.updateTasks(dto, id, token);
    }
}
