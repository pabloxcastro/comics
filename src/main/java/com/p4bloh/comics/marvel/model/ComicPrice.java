package com.p4bloh.comics.marvel.model;

public class ComicPrice {
    private String type;
    private Float price;

    public ComicPrice(String type, Float price) {
        this.type = type;
        this.price = price;
    }

    public ComicPrice() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
