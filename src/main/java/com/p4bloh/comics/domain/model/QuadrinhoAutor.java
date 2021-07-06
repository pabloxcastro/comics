package com.p4bloh.comics.domain.model;

import javax.persistence.*;

@Entity
public class QuadrinhoAutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Quadrinho quadrinho;

    @Column
    private String name;

    public QuadrinhoAutor(Quadrinho quadrinho, String name) {
        this.quadrinho = quadrinho;
        this.name = name;
    }

    public QuadrinhoAutor() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Quadrinho getQuadrinho() {
        return quadrinho;
    }

    public void setQuadrinho(Quadrinho quadrinho) {
        this.quadrinho = quadrinho;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
