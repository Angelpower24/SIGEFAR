package com.iestp.sigefar.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ResponseEntity<String> manejarClienteNoEncontrado(
            ClienteNoEncontradoException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DniDuplicadoException.class)
    public ResponseEntity<String> manejarDniDuplicado(
            DniDuplicadoException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> manejarErrorGenerico(
            RuntimeException ex) {

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
}
