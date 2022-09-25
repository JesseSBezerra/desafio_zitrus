package br.com.jdsb.usuarioapi.service.exceptions;

public class UsuarioJaCadastradoException extends RuntimeException{

    public UsuarioJaCadastradoException(String message) {
        super(message);
    }
}
