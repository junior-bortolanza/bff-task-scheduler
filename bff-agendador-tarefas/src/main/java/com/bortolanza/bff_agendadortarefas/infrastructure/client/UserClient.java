package com.bortolanza.bff_agendadortarefas.infrastructure.client;


import com.bortolanza.bff_agendadortarefas.business.dto.in.AddressDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.in.LoginRequestDTO;
import com.bortolanza.bff_agendadortarefas.business.dto.in.PhoneDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.in.UserDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.out.AddressDTOResponse;
import com.bortolanza.bff_agendadortarefas.business.dto.out.PhoneDTOResponse;
import com.bortolanza.bff_agendadortarefas.business.dto.out.UserDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {


    @GetMapping("/usuario")
    UserDTOResponse searchUserByEmail(@RequestParam("email") String email,
                                      @RequestHeader("Authorization") String token);

    @PostMapping("/usuario")
    UserDTOResponse saveUser(@RequestBody UserDTORequest userDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO userDTO);


    @DeleteMapping("/{email}")
    Void deleteUserByEmail(@PathVariable String email,
                           @RequestHeader("Authorization") String token);

    @PutMapping
    UserDTOResponse updateUser(@RequestBody UserDTORequest dto,
                              @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    AddressDTOResponse updateAddress(@RequestBody AddressDTORequest dto,
                                     @RequestParam("id") Long id,
                                     @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    PhoneDTOResponse updatePhone(@RequestBody PhoneDTORequest dto,
                                 @RequestParam("id") Long id,
                                 @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    AddressDTOResponse registerAddress(@RequestBody AddressDTORequest dto,
                                      @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    PhoneDTOResponse registerPhone(@RequestBody PhoneDTORequest dto,
                                  @RequestHeader("Authorization") String token);
}
