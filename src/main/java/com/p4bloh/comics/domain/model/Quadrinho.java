package com.p4bloh.comics.domain.model;

import javax.persistence.*;

@Entity
public class Quadrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @Column
    private String titulo;

    @Column
    private String isbn;

    @Lob
    private String descricao;

    @Column
    private int diaDesconto;

    public Quadrinho() {
        //
    }

    public Quadrinho(Long id, Usuario usuario, String titulo, String isbn, String descricao, int diaDesconto) {
        this.id = id;
        this.usuario = usuario;
        this.titulo = titulo;
        this.isbn = isbn;
        this.descricao = descricao;
        this.diaDesconto = diaDesconto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public int getDiaDesconto() {
        return diaDesconto;
    }

    public void setDiaDesconto(int diaDesconto) {
        this.diaDesconto = diaDesconto;
    }
}
