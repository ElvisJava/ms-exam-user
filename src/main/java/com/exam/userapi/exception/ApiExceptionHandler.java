package com.exam.userapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<Map<String,String>>
    handleEmailExists(EmailAlreadyRegisteredException ex) {
        return
                ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("mensaje", "El correo ya esta registrado" ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>>
    handleBadRequest(IllegalArgumentException ex) {
        return
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje",
                        ex.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>
    handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .findFirst().orElse("Datos inválidos");
        return
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", msg));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleAll(Exception ex) {
        return
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("mensaje",
                        "Error interno"));
    }
}
