package com.grupo.alimentos.peru.exception;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String message){
        super (message);
    }   

}


/*cuando el usuario intenta:
    Crear una categoría duplicada
    Registrar un producto con nombre repetido
    Registrar una tienda repetida
*/
