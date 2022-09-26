package br.com.jdsb.clienteapi.resource.exceptions;


import br.com.jdsb.clienteapi.service.exceptions.ClienteJaCadastradoException;
import br.com.jdsb.clienteapi.service.exceptions.ClienteNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<StandardError> clienteNaoEncontrado(ClienteNaoEncontradoException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new StandardError(LocalDateTime.now(),
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND.value(),
                        request.getRequestURI())
        );
    }

    @ExceptionHandler(ClienteJaCadastradoException.class)
    public ResponseEntity<StandardError> clienteJaCadastrado(ClienteJaCadastradoException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new StandardError(LocalDateTime.now(),
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        request.getRequestURI())
        );
    }
}
