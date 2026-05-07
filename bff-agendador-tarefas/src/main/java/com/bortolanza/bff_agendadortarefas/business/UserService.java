package com.bortolanza.bff_agendadortarefas.business;

import com.bortolanza.bff_agendadortarefas.business.dto.in.AddressDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.in.LoginDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.in.PhoneDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.in.UserDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.out.AddressDTOResponse;
import com.bortolanza.bff_agendadortarefas.business.dto.out.PhoneDTOResponse;
import com.bortolanza.bff_agendadortarefas.business.dto.out.UserDTOResponse;
import com.bortolanza.bff_agendadortarefas.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient client;

    public UserDTOResponse saveUser(UserDTORequest userDTO) {

        return client.saveUser(userDTO);
    }

    public String loginUser(@RequestBody LoginDTORequest userDTO) {

        return client.login(userDTO);
    }


    public UserDTOResponse searchUserByEmail(String email, String token) {

        return client.searchUserByEmail(email,token);
    }

    public void deleteUserByEmail(String email, String token) {

        client.deleteUserByEmail(email,token);
    }

    public UserDTOResponse updateUserData(String token, UserDTORequest dto) {
        return client.updateUser(dto,token);
    }

    public AddressDTOResponse updateAddress(Long idAddress, AddressDTORequest dto, String token) {

      return client.updateAddress(dto,idAddress,token);
    }

    public PhoneDTOResponse updatePhone(PhoneDTORequest dto, Long idPhone, String token) {

        return client.updatePhone(dto,idPhone,token);
    }

    public AddressDTOResponse registerAddress(AddressDTORequest dto, String token ) {

        return client.registerAddress(dto,token);
    }

    public PhoneDTOResponse registerPhone(String token, PhoneDTORequest dto) {
       return client.registerPhone(dto,token);
    }
}
