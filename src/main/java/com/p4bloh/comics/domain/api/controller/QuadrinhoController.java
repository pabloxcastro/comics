package com.p4bloh.comics.domain.api.controller;

import com.p4bloh.comics.domain.dto.QuadrinhoDto;
import com.p4bloh.comics.domain.exception.EntidadeNaoEncontradaException;
import com.p4bloh.comics.domain.model.Quadrinho;
import com.p4bloh.comics.domain.service.QuadrinhoService;
import com.p4bloh.comics.marvel.client.ComicClient;
import com.p4bloh.comics.marvel.model.ComicDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/quadrinhos")
public class QuadrinhoController {

    @Autowired
    private QuadrinhoService quadrinhoService;

    @Autowired
    private ComicClient comicClient;

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody QuadrinhoDto quadrinhoDto){
        Quadrinho quadrinhoNovo = quadrinhoService.salvar(quadrinhoDto);
        return ResponseEntity.ok(quadrinhoNovo);
    }

    /*@PutMapping
    public ResponseEntity<?> adicionar(@RequestBody Quadrinho quadrinho){
        try {
            Quadrinho quadrinhoNovo = quadrinhoService.salvar(quadrinho);
            return ResponseEntity.ok(quadrinhoNovo);

        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

    @GetMapping
    public ComicDataWrapper catalogoComic() {
        ComicDataWrapper catalogo = comicClient.catalogo();
        return catalogo;
    }

    @GetMapping({"/{comicId}"})
    public ComicDataWrapper buscarComic(@PathVariable int comicId ) {
        ComicDataWrapper comic = comicClient.getComic(comicId);
        return comic;
    }
}
