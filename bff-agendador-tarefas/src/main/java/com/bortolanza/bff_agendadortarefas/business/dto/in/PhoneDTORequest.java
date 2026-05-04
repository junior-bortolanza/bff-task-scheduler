package com.bortolanza.bff_agendadortarefas.business.dto.in;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDTORequest {

    private String number;
    private String ddd;


}
