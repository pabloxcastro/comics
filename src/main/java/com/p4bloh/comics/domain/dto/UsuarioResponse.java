package com.p4bloh.comics.domain.dto;

import com.p4bloh.comics.domain.model.Usuario;

import java.time.LocalDate;

public class UsuarioResponse {
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;

    public static UsuarioResponse toDto(Usuario usuario){
        return new UsuarioResponse(usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getDataNascimento());
    }

    public UsuarioResponse(String nome, String cpf, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
