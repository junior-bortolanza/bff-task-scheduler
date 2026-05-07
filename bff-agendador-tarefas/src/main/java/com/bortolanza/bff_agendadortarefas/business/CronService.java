package com.bortolanza.bff_agendadortarefas.business;

import com.bortolanza.bff_agendadortarefas.business.dto.in.LoginDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.out.TasksDTOResponse;
import com.bortolanza.bff_agendadortarefas.business.enums.StatusNotificationEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TasksService service;
    private final EmailService emailService;
    private final UserService userService;

    @Value("${user.email}")
    private String email;

    @Value("${user.password}")
    private String password;

    @Scheduled(cron = "${cron.hour}")
    public void searchTasksNextHour() {
        String token = login(converterForRequestDTO());
        log.info("Iniciada a busca de tarefas");
        LocalDateTime presentHour = LocalDateTime.now();
        LocalDateTime futureHourPlusFive = LocalDateTime.now().plusHours(1);

        List<TasksDTOResponse> taskList = service.searchScheduledTasksByPeriod(presentHour, futureHourPlusFive, token);
        log.info("Tarefas encontradas: " + taskList);
        taskList.forEach(task -> {
            emailService.sendEmail(task);
            log.info("Email enviado para o usuario " + task.getUserEmail());
            service.changeStatus(StatusNotificationEnum.NOTIFIED, task.getId(),
                    token);
        });
        log.info("Tarefa finalizada com sucesso");
    }

    public String login(LoginDTORequest dto) {
        return userService.loginUser(dto);

    }

    public LoginDTORequest converterForRequestDTO() {
        return LoginDTORequest.builder()
                .email(email)
                .password(password)
                .build();
    }
}
