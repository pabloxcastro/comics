package com.p4bloh.comics.domain.dto;

public class QuadrinhoDto {
    private Long usuarioId;
    private int comicId;

    public QuadrinhoDto(Long usuarioId, int comicId) {
        this.usuarioId = usuarioId;
        this.comicId = comicId;
    }

    public QuadrinhoDto() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getComicId() {
        return comicId;
    }

    public void setComicId(int comicId) {
        this.comicId = comicId;
    }
}
