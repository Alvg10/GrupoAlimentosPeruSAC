package com.grupo.alimentos.peru.exception;

public class ResourceNotFoundException extends RuntimeException{    
    public ResourceNotFoundException(String message){
        super (message);
    }   

}



// Para cuando NO existe una categoría, tienda o producto.Www