package com.squad3.bemestar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(PerguntaNotFoundException.class)
    public ResponseEntity<String> handlePerguntaNotFoundException(PerguntaNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(PerguntaCreationException.class)
    public ResponseEntity<String> handlePerguntaCreationException(PerguntaCreationException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(RespostaNotFoundException.class)
    public ResponseEntity<String> handleRespostaNotFoundException(RespostaNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(RespostaCreationException.class)
    public ResponseEntity<String> handleRespostaCreationException(RespostaCreationException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}

//Esse manipulador de exceção personalizado, pode tratar as exceções personalizadas
// de forma centralizada e retornar respostas apropriadas com mensagens de erro significativas
// para os clientes.