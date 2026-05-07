package com.bortolanza.bff_agendadortarefas.business.dto.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTORequest {

    private String name;
    private String email;
    private String password;
    private List<AddressDTORequest> address;
    private List<PhoneDTORequest> phone;

}
