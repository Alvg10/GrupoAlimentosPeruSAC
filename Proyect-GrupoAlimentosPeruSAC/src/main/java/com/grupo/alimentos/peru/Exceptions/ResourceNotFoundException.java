package com.grupo.alimentos.peru.Exceptions;

public class ResourceNotFoundException extends RuntimeException{    
    public ResourceNotFoundException(String message){
        super (message);
    }   

}



// Para cuando NO existe una categoría, tienda o producto.