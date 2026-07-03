package com.bortolanza.bff_agendadortarefas.infrastructure.client;


import com.bortolanza.bff_agendadortarefas.business.dto.in.AddressDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.in.LoginDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.in.PhoneDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.in.UserDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.out.AddressDTOResponse;
import com.bortolanza.bff_agendadortarefas.business.dto.out.PhoneDTOResponse;
import com.bortolanza.bff_agendadortarefas.business.dto.out.UserDTOResponse;
import com.bortolanza.bff_agendadortarefas.business.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping
    UserDTOResponse searchUserByEmail(@RequestParam("email") String email,
                                      @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping
    UserDTOResponse saveUser(@RequestBody UserDTORequest userDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest userDTO);

    @DeleteMapping("{email}")
    Void deleteUserByEmail(@PathVariable String email,
                           @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping
    UserDTOResponse updateUser(@RequestBody UserDTORequest dto,
                               @RequestHeader(value = "Authorization", required = false ) String token);

    @PutMapping("/endereco")
    AddressDTOResponse updateAddress(@RequestBody AddressDTORequest dto,
                                     @RequestParam("id") Long id,
                                     @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping("/telefone")
    PhoneDTOResponse updatePhone(@RequestBody PhoneDTORequest dto,
                                 @RequestParam("id") Long id,
                                 @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping("/endereco")
    AddressDTOResponse registerAddress(@RequestBody AddressDTORequest dto,
                                       @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping("/telefone")
    PhoneDTOResponse registerPhone(@RequestBody PhoneDTORequest dto,
                                   @RequestHeader(value = "Authorization", required = false) String token);

    @GetMapping("/endereco/{cep}")
    ResponseEntity<ViaCepDTOResponse> searchDataCep(@PathVariable("cep") String cep);
    }
