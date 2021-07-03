package com.p4bloh.comics.marvel.model;

import java.util.List;

public class Comic {
    private int id;
    private String title;
    private List<ComicPrice> prices;
    private String isbn;
    private String description;
    private CreatorList creators;

    public Comic(int id, String title, List prices, String isbn, String description, CreatorList creators ) {
        this.id = id;
        this.title = title;
        this.prices = prices;
        this.isbn = isbn;
        this.description = description;
        this.creators = creators;
    }

    public Comic() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ComicPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<ComicPrice> prices) {
        this.prices = prices;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CreatorList getCreators() {
        return creators;
    }

    public void setCreators(CreatorList creators) {
        this.creators = creators;
    }
}
