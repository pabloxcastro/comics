package com.p4bloh.comics.domain.dto;

import com.p4bloh.comics.domain.model.Usuario;

import java.time.LocalDate;

public class UsuarioPutResponse {
    private String nome;
    private LocalDate dataNascimento;

    public static UsuarioPutResponse toDto(Usuario usuario){
        return new UsuarioPutResponse(usuario.getNome(), usuario.getDataNascimento());
    }

    public UsuarioPutResponse(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
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
