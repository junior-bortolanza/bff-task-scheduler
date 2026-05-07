package com.bortolanza.bff_agendadortarefas.infrastructure.exceptions;

public class BusinessException extends RuntimeException{
    public BusinessException(String mensage){
        super(mensage);
    }
    public BusinessException(String mensage, Throwable throwable){
        super(mensage,throwable);
    }
}
