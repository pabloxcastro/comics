package com.p4bloh.comics.domain.service;

import com.p4bloh.comics.domain.dto.QuadrinhoDto;
import com.p4bloh.comics.domain.dto.QuadrinhoResponseDto;
import com.p4bloh.comics.domain.exception.EntidadeNaoEncontradaException;
import com.p4bloh.comics.domain.model.Quadrinho;
import com.p4bloh.comics.domain.model.QuadrinhoAutor;
import com.p4bloh.comics.domain.model.QuadrinhoPreco;
import com.p4bloh.comics.domain.model.Usuario;
import com.p4bloh.comics.domain.repository.QuadrinhoRepository;
import com.p4bloh.comics.domain.repository.UsuarioRepository;
import com.p4bloh.comics.marvel.client.ComicClient;
import com.p4bloh.comics.marvel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuadrinhoService {

    @Autowired
    private QuadrinhoRepository quadrinhoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComicClient comicClient;

    @Autowired
    private QuadrinhoPrecoService quadrinhoPrecoService;

    @Autowired
    private QuadrinhoAutorService quadrinhoAutorService;

    public QuadrinhoResponseDto salvar(QuadrinhoDto quadrinhoDto){

        Long usuarioId = quadrinhoDto.getUsuarioId();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(()->new EntidadeNaoEncontradaException("Usuário", usuarioId));

        Quadrinho quadrinho = new Quadrinho();
        quadrinho.setUsuario(usuario);

        int comicId = quadrinhoDto.getComicId();
        ComicDataWrapper comicMarvel = comicClient.getComic(comicId);

        List<Comic> comics = comicMarvel.getData().getResults();
        List<ComicPrice> precos = new ArrayList<>();
        CreatorList autores = new CreatorList();

        for (Comic comic: comics) {
            quadrinho.setDescricao(comic.getDescription());
            quadrinho.setIsbn(comic.getIsbn());
            quadrinho.setTitulo(comic.getTitle());
            precos = comic.getPrices();
            autores = comic.getCreators();
        }

        Quadrinho quadrinhoNovo = quadrinhoRepository.save(quadrinho);

        List<QuadrinhoPreco> quadrinhoPrecos = new ArrayList<>();

        if (precos.size() > 0 ) {
             quadrinhoPrecos = quadrinhoPrecoService.salvar(quadrinhoNovo, precos);
        }

        List<CreatorSummary> nomesAutores = autores.getItems();
        List<QuadrinhoAutor> quadrinhoAutores = new ArrayList<>();

        if (nomesAutores.size() > 0){
             quadrinhoAutores = quadrinhoAutorService.salvar(quadrinhoNovo, nomesAutores);
        }

        return QuadrinhoResponseDto.toDto(quadrinhoNovo, quadrinhoPrecos, quadrinhoAutores);
    }
}
