package com.p4bloh.comics.domain.model;

import com.p4bloh.comics.marvel.model.ComicPrice;

import javax.persistence.*;
import java.util.List;

@Entity
public class Quadrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @Column//(nullable = false)
    //@NotBlank(message = "Titulo é obrigatório")
    private String titulo;

    @Column
    private String isbn;

    @Column
    private String descricao;

    public Quadrinho() {
        //
    }

    public Quadrinho(Usuario usuario, String titulo, String isbn, String descricao) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.isbn = isbn;
        this.descricao = descricao;
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

}
