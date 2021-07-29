package com.p4bloh.comics.domain.exception;

public class EntidadeEmUsoException extends RuntimeException {
        public EntidadeEmUsoException(String entidade){
            super(String.format("%s está em uso e não pode ser excluído", entidade));
        }
}
