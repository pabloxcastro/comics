package com.p4bloh.comics.domain.dto;

import com.p4bloh.comics.domain.model.Usuario;

import java.time.LocalDate;

public class UsuarioPutRequest {

    private String nome;

    private LocalDate dataNascimento;

    public Usuario toObj(){
        return new Usuario(nome, dataNascimento);
    }

    public UsuarioPutRequest(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public UsuarioPutRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
