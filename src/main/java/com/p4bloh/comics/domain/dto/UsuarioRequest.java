package com.p4bloh.comics.domain.dto;

import com.p4bloh.comics.domain.model.Usuario;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UsuarioRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @CPF(message = "CPF é invalido")
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @Email(message = "Email é inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotNull(message = "Data de nascimento é obrigatório")
    private LocalDate dataNascimento;

    public Usuario toObj(){
        return new Usuario(nome, cpf, email, dataNascimento);
    }

    public UsuarioRequest(String nome, String cpf, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public UsuarioRequest() {
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
