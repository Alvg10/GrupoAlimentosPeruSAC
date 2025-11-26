package com.grupo.alimentos.peru.Exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super (message);

    }   

}



//Para cuando algo está mal enviado por el usuario.