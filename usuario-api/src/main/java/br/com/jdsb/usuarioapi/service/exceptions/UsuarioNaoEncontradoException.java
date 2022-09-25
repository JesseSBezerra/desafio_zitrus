package br.com.jdsb.usuarioapi.service.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }
}
