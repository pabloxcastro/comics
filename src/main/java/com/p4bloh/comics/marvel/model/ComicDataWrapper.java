package com.p4bloh.comics.marvel.model;

public class ComicDataWrapper {

    private int code;
    private String status;
    private ComicDataContainer data;

    public ComicDataWrapper(int code, String status, ComicDataContainer data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public ComicDataWrapper() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ComicDataContainer getData() {
        return data;
    }

    public void setData(ComicDataContainer data) {
        this.data = data;
    }
}
