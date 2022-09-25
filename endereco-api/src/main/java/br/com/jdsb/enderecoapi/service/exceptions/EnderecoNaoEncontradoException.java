package br.com.jdsb.enderecoapi.service.exceptions;

public class EnderecoNaoEncontradoException extends RuntimeException{

    public EnderecoNaoEncontradoException(String message) {
        super(message);
    }
}
