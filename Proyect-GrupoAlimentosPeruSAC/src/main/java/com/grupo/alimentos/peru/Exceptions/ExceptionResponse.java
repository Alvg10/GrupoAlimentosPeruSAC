package com.grupo.alimentos.peru.Exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ExceptionResponse {
    
    private String message; //Explicacion clara para el user
    private int statusCode; //Codigo HTTP
    private LocalDateTime timestamp; //Para saber cuando paso
    private Object errorDetails; //lista/objetos con detalles ej. Campos invalidos, etc.
    private String path; // Para ver el endpoint donde fue el error.
    private String method;

}
