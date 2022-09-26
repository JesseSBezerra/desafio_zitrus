package br.com.jdsb.usuarioapi.resource.exceptions;

import br.com.jdsb.usuarioapi.service.exceptions.UsuarioJaCadastradoException;
import br.com.jdsb.usuarioapi.service.exceptions.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<StandardError> usuarioNaoEncontrado(UsuarioNaoEncontradoException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new StandardError(LocalDateTime.now(),
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND.value(),
                        request.getRequestURI())
        );
    }

    @ExceptionHandler(UsuarioJaCadastradoException.class)
    public ResponseEntity<StandardError> usuarioJaCadastrado(UsuarioJaCadastradoException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new StandardError(LocalDateTime.now(),
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        request.getRequestURI())
        );
    }
}
