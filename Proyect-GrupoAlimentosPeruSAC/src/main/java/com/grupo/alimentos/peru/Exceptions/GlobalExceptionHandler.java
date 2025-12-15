package com.grupo.alimentos.peru.Exceptions;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;




@RestControllerAdvice //para aplicar manejo global de errores para todos los controladores rest
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            e.getMessage(),
            HttpStatus.NOT_FOUND.value(),
            LocalDateTime.now(),
            "Parece que lo que estas buscando no existe",
            request.getRequestURI(),
            request.getMethod());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);                        
    }

   

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleAlreadyExistsException(AlreadyExistsException e, HttpServletRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            e.getMessage(),
            HttpStatus.CONFLICT.value(),
            LocalDateTime.now(),
            "No se puede crear porque el recurso ya está registrado",
            request.getRequestURI(),
            request.getMethod());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
                        
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException e, HttpServletRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            e.getMessage(),
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now(),
            "Los datos enviados son incorrectos",
            request.getRequestURI(),
            request.getMethod());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
                        
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ExceptionResponse> handleBusinessRuleException(BusinessRuleException e, HttpServletRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            e.getMessage(),
            HttpStatus.NOT_FOUND.value(),
            LocalDateTime.now(),
            "Parece que estas intentando hacer algo que van en contra de las reglas de negocio",
            request.getRequestURI(),
            request.getMethod());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);                        
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)                //ya existe en Spring
    public ResponseEntity<ExceptionResponse> handleValidationsExceptions(MethodArgumentNotValidException e, HttpServletRequest request){
        
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors()
        .forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));
        
       
        
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            "Se encontraron errores en los campos enviados",
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now(),
            errors,
            request.getRequestURI(),
            request.getMethod()
        );

            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
                        
    }



}
