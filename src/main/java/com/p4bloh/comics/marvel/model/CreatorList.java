package com.p4bloh.comics.marvel.model;

import java.util.List;

public class CreatorList {
    private int available;
    private int returned;
    private List<CreatorSummary> items;

    public CreatorList(int available, int returned, List<CreatorSummary> items) {
        this.available = available;
        this.returned = returned;
        this.items = items;
    }

    public CreatorList() {
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public List<CreatorSummary> getItems() {
        return items;
    }

    public void setItems(List<CreatorSummary> items) {
        this.items = items;
    }

}
