package com.p4bloh.comics.domain.dto;

import com.p4bloh.comics.domain.model.Quadrinho;
import com.p4bloh.comics.domain.model.QuadrinhoAutor;
import com.p4bloh.comics.domain.model.QuadrinhoPreco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuadrinhoResponse {
    private String nomeUsuario;
    private String emailUsuario;
    private String titulo;
    private String isbn;
    private String descricao;
    private int diaDesconto;
    private Boolean descontoAtivo;
    private Map<String, Float> precos;
    private List<String> autores;

    public QuadrinhoResponse(String nomeUsuario, String emailUsuario, String titulo, String isbn, String descricao,
                             int diaDesconto, Boolean descontoAtivo, Map<String, Float> precos, List<String> autores)
    {
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.titulo = titulo;
        this.isbn = isbn;
        this.descricao = descricao;
        this.diaDesconto = diaDesconto;
        this.descontoAtivo = descontoAtivo;
        this.precos = precos;
        this.autores = autores;
    }

    public static QuadrinhoResponse toDto(Quadrinho quadrinho, Boolean descontoAtivo, List<QuadrinhoPreco> quadrinhoPrecos,
                                          List<QuadrinhoAutor> quadrinhoAutores){

        Map<String, Float> quadrinhoPrecosDto = new HashMap<>();

        for (QuadrinhoPreco quadrinhoPrecoItem: quadrinhoPrecos){
            quadrinhoPrecosDto.put(quadrinhoPrecoItem.getTipo(), quadrinhoPrecoItem.getPreco());
        }

        List<String> quadrinhoAutorDto = new ArrayList<>();

        for (QuadrinhoAutor quadrinhoAutor: quadrinhoAutores){
            quadrinhoAutorDto.add(quadrinhoAutor.getName());
        }

        QuadrinhoResponse quadrinhoResponse = new QuadrinhoResponse(
                quadrinho.getUsuario().getNome(),
                quadrinho.getUsuario().getEmail(),
                quadrinho.getTitulo(),
                quadrinho.getIsbn(),
                quadrinho.getDescricao(),
                quadrinho.getDiaDesconto(),
                descontoAtivo,
                quadrinhoPrecosDto,
                quadrinhoAutorDto
        );

        return quadrinhoResponse;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Map<String, Float> getPrecos() {
        return precos;
    }

    public void setPrecos(Map<String, Float> precos) {
        this.precos = precos;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public int getDiaDesconto() {
        return diaDesconto;
    }

    public void setDiaDesconto(int diaDesconto) {
        this.diaDesconto = diaDesconto;
    }

    public Boolean getDescontoAtivo() {
        return descontoAtivo;
    }

    public void setDescontoAtivo(Boolean descontoAtivo) {
        this.descontoAtivo = descontoAtivo;
    }
}
