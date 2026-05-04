package com.bortolanza.bff_agendadortarefas.controller;

import com.bortolanza.bff_agendadortarefas.business.UserService;
import com.bortolanza.bff_agendadortarefas.business.dto.in.AddressDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.in.LoginRequestDTO;
import com.bortolanza.bff_agendadortarefas.business.dto.in.PhoneDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.in.UserDTORequest;
import com.bortolanza.bff_agendadortarefas.business.dto.out.AddressDTOResponse;
import com.bortolanza.bff_agendadortarefas.business.dto.out.PhoneDTOResponse;
import com.bortolanza.bff_agendadortarefas.business.dto.out.UserDTOResponse;
import com.bortolanza.bff_agendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login e usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UserController {

    private final UserService userService;


    @PostMapping
    @Operation(summary = "Salvar Usuários", description = "Cria um novo usuário!")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso!")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public ResponseEntity<UserDTOResponse> saveUser(@RequestBody UserDTORequest userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuários", description = "Cria um novo usuário!")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso!")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public String login(@RequestBody LoginRequestDTO userDTO) {
        return userService.loginUser(userDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de Usuários por Email"
            , description = "Buscar dados do usuário!")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado!")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public ResponseEntity<UserDTOResponse> searchUserByEmail(@RequestParam("email") String email,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.searchUserByEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuário por Id", description = "Deleta usuário!")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable("email") String email,
                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        userService.deleteUserByEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar Dados de Usuário", description = "Atualizar dados de usuário!")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public ResponseEntity<UserDTOResponse> updateUser(@RequestBody UserDTORequest dto,
                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updateUserData(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar EndereÇo de Usuários", description = "Atualiza endereÇo de usuário!")
    @ApiResponse(responseCode = "200", description = "EndereÇo atualizado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public ResponseEntity<AddressDTOResponse> updateAddress(@RequestBody AddressDTORequest dto,
                                                            @RequestParam("id") Long id,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updateAddress(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar Telefone de Usuários", description = "Atualiza telefone de usuário!")
    @ApiResponse(responseCode = "200", description = "telefone atualizado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public ResponseEntity<PhoneDTOResponse> updateAddress(@RequestBody PhoneDTORequest dto,
                                                          @RequestParam("id") Long id,
                                                          @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updatePhone(dto, id ,token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva EndereÇo de Usuários", description = "Salva endereÇo de usuário!")
    @ApiResponse(responseCode = "200", description = "EndereÇo salvo com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public ResponseEntity<AddressDTOResponse> registerAddress(@RequestBody AddressDTORequest dto,
                                                             @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.registerAddress(dto, token));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva Telfone de Usuários", description = "Salva telefone de usuário!")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public ResponseEntity<PhoneDTOResponse> resisterPhone(@RequestBody PhoneDTORequest dto,
                                                         @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.registerPhone(token, dto));
    }
}

