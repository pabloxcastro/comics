package com.p4bloh.comics.domain.service;

import com.p4bloh.comics.domain.dto.QuadrinhoRequest;
import com.p4bloh.comics.domain.dto.QuadrinhoResponse;
import com.p4bloh.comics.domain.exception.EntidadeNaoEncontradaException;
import com.p4bloh.comics.domain.model.Quadrinho;
import com.p4bloh.comics.domain.model.QuadrinhoAutor;
import com.p4bloh.comics.domain.model.QuadrinhoPreco;
import com.p4bloh.comics.domain.model.Usuario;
import com.p4bloh.comics.domain.repository.QuadrinhoRepository;
import com.p4bloh.comics.domain.repository.UsuarioRepository;
import com.p4bloh.comics.marvel.client.ComicClient;
import com.p4bloh.comics.marvel.model.*;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
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

    public QuadrinhoResponse salvar(QuadrinhoRequest quadrinhoRequest){

        Long usuarioId = quadrinhoRequest.getUsuarioId();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(()->new EntidadeNaoEncontradaException("Usuário", usuarioId));

        /*
        Inicializando o quadrinho
         */
        Quadrinho quadrinho = new Quadrinho();
        Boolean descontoAtivo = false;
        quadrinho.setUsuario(usuario);

        /*
        Buscando dados do quadrinho na Marvel
         */
        Long comicId = quadrinhoRequest.getComicId();

        try {
            ComicDataWrapper comicMarvel = comicClient.getComic(comicId);
            List<Comic> comics = comicMarvel.getData().getResults();

            List<ComicPrice> precos = new ArrayList<>();
            CreatorList autores = new CreatorList();

            /*
            alimentando o quadrinho com os dados recebidos
             */
            for (Comic comic : comics) {
                quadrinho.setDescricao(comic.getDescription());

                if (!comic.getIsbn().equals("")) {
                    quadrinho.setIsbn(comic.getIsbn());

                } else if (!quadrinhoRequest.getIsbn().equals("")) {
                    quadrinho.setIsbn(quadrinhoRequest.getIsbn());
                }

                if (!comic.getIsbn().equals("") && !quadrinhoRequest.getIsbn().equals("") ) {
                    quadrinho.setDiaDesconto(this.getDiaDesconto(quadrinho.getIsbn()));
                    descontoAtivo = this.getDescontoAtivo(quadrinho.getDiaDesconto());
                }

                quadrinho.setTitulo(comic.getTitle());
                precos = comic.getPrices();
                autores = comic.getCreators();
            }

            /*
            gravando os dados dos quadrinhos no banco de dados
             */
            Quadrinho quadrinhoNovo = quadrinhoRepository.save(quadrinho);

            List<QuadrinhoPreco> quadrinhoPrecos = new ArrayList<>();

            if (precos.size() > 0) {
                quadrinhoPrecos = quadrinhoPrecoService.salvar(quadrinhoNovo, precos);
            }

            List<CreatorSummary> nomesAutores = autores.getItems();
            List<QuadrinhoAutor> quadrinhoAutores = new ArrayList<>();

            if (nomesAutores.size() > 0) {
                quadrinhoAutores = quadrinhoAutorService.salvar(quadrinhoNovo, nomesAutores);
            }

            return QuadrinhoResponse.toDto(quadrinhoNovo, descontoAtivo, quadrinhoPrecos, quadrinhoAutores);

        } catch (FeignException e){
            throw new EntidadeNaoEncontradaException("Comic", comicId);
        }
    }

    public Boolean getDescontoAtivo(int diaDesconto){

        LocalDate hoje = LocalDate.now();
        DayOfWeek diaHoje = hoje.getDayOfWeek();

        if (diaDesconto-1 == diaHoje.getValue() ){
            return true;
        } else {
            return false;
        }
    }

    private int getDiaDesconto(String isbn){

        int tamanho = isbn.length();
        int digito = Integer.parseInt(isbn.substring(tamanho-1, tamanho));

        int diaSemana = 0;

        if (digito <= 1) {
            diaSemana = 2;
        } else if (digito <= 3){
            diaSemana = 3;
        } else if (digito <= 5){
            diaSemana = 4;
        } else if (digito <= 7){
            diaSemana = 5;
        } else if (digito <= 9){
            diaSemana = 6;
        }

        return diaSemana;
    }
}
