package com.p4bloh.comics.marvel.model;

import java.util.ArrayList;
import java.util.List;

public class ComicDataContainer {
    private int offset;
    private int limit;
    private int total;
    private int count;
    private List<Comic> results;


    public ComicDataContainer(int offset, int limit, int total, int count, List results) {
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.count = count;
        this.results = results;
    }

    public ComicDataContainer(){
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Comic> getResults() {
        return results;
    }

    public void setResults(List<Comic> results) {
        this.results = results;
    }
}
