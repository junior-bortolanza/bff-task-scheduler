package com.bortolanza.bff_agendadortarefas.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTORequest {
    private String email;
    private String password;
}
