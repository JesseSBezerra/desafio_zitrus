package br.com.jdsb.clienteapi.service.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException{
    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}
