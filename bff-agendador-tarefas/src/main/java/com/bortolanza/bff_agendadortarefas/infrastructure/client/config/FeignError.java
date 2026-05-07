package com.bortolanza.bff_agendadortarefas.infrastructure.client.config;

import com.bortolanza.bff_agendadortarefas.infrastructure.exceptions.BusinessException;
import com.bortolanza.bff_agendadortarefas.infrastructure.exceptions.ConflictException;
import com.bortolanza.bff_agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.bortolanza.bff_agendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Configuration
public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        String messageError = messageError(response);

        switch (response.status()) {
            case 409:
                return new ConflictException("Erro: " + messageError);
            case 403:
                return new ResourceNotFoundException("Erro: " + messageError);
            case 401:
                return new UnauthorizedException("Erro: " + messageError);
            case 400:
                return new IllegalArgumentException("Erro: " + messageError);

            default:
                return new BusinessException("Erro: " + messageError);

        }
    }

    private String messageError(Response response) {
        try {
            if(Objects.isNull(response.body())) {
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
