package com.grupo.alimentos.peru.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super (message);

    }   

}



//Para cuando algo está mal enviado por el usuario.