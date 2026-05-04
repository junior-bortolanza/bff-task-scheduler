package com.bortolanza.bff_agendadortarefas.business.dto.out;


import com.bortolanza.bff_agendadortarefas.business.enums.StatusNotificationEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TasksDTOResponse {

    private String id;
    private String taskName;
    private String taskDescription;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime eventDate;
    private String userEmail;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime alterationDate;
    private StatusNotificationEnum statusNotificationEnum;
}
