package com.p4bloh.comics.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String entidade, Long id){
        super(String.format("%s com o código %d não encontrado", entidade, id));
    }
}
