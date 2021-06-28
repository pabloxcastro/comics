package com.p4bloh.comics.domain.exception;

public class EntidadeJaExisteException extends RuntimeException {

    public EntidadeJaExisteException(String entidade, String chave, String valor){
        super(String.format("%s com o %s %s já existe e não pode ser cadastrado", entidade, chave, valor));
    }
}
