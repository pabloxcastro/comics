package com.p4bloh.comics.domain.model;

import javax.persistence.*;

@Entity
public class QuadrinhoPreco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Quadrinho quadrinho;

    @Column
    private String tipo;

    @Column
    private Float preco;

    public QuadrinhoPreco(String tipo, Float preco) {
        this.tipo = tipo;
        this.preco = preco;
    }

    public QuadrinhoPreco() {
    }

    public Quadrinho getQuadrinho() {
        return quadrinho;
    }

    public void setQuadrinho(Quadrinho quadrinho) {
        this.quadrinho = quadrinho;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }
}
