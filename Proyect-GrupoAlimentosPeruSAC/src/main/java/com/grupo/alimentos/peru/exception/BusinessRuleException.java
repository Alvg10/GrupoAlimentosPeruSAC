package com.grupo.alimentos.peru.exception;

public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message){
        super (message);
    }   

}


/*

para intentar vender un producto sin stock,
eliminar una categoría que tiene productos dentro o 
registrar un producto sin tienda válida

*/