package com.p4bloh.comics.domain.dto;

public class QuadrinhoRequest {
    private Long usuarioId;
    private Long comicId;
    private String isbn;

    public QuadrinhoRequest(Long usuarioId, Long comicId, String isbn) {
        this.usuarioId = usuarioId;
        this.comicId = comicId;
        this.isbn = isbn;
    }

    public QuadrinhoRequest() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getComicId() {
        return comicId;
    }

    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
