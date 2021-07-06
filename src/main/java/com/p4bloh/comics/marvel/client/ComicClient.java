package com.p4bloh.comics.marvel.client;

import com.p4bloh.comics.marvel.model.ComicDataWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "comicClient", url = "http://gateway.marvel.com/v1/public/comics")
public interface ComicClient {

    @GetMapping(value = "?ts=1624907750&apikey=0da79380c003525285af682022c4573b&hash=9bfb4e3f0b160939770a311ec87e4edd&limit=6")
    ComicDataWrapper catalogo();

    @GetMapping(value = "/{comicId}?ts=1624907750&apikey=0da79380c003525285af682022c4573b&hash=9bfb4e3f0b160939770a311ec87e4edd")
    ComicDataWrapper getComic(@PathVariable Long comicId);

}
