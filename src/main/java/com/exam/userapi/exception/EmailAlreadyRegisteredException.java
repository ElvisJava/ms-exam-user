package com.exam.userapi.exception;

/**
 * Excepción lanzada cuando se intenta registrar un usuario con un correo
 * que ya existe en la base de datos.
 *
 * Se extiende de RuntimeException para que pueda ser manejada por el
 * controlador global `ApiExceptionHandler` y así devolver el JSON
 * requerido: {"mensaje": "..."} con el código HTTP apropiado.
 */

public class EmailAlreadyRegisteredException  extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public EmailAlreadyRegisteredException() {
        super("El correo ya esta registrado");
    }
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
