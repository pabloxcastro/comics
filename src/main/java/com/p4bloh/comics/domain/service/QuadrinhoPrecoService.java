package com.p4bloh.comics.domain.service;

import com.p4bloh.comics.domain.model.Quadrinho;
import com.p4bloh.comics.domain.model.QuadrinhoPreco;
import com.p4bloh.comics.domain.repository.QuadrinhoPrecoRepository;
import com.p4bloh.comics.marvel.model.ComicPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuadrinhoPrecoService {

    @Autowired
    private QuadrinhoPrecoRepository quadrinhoPrecoRepository;

    public List<QuadrinhoPreco> salvar(Quadrinho quadrinho, List<ComicPrice> precos){

        List<QuadrinhoPreco> quadrinhoPrecos = new ArrayList<>();

        for (ComicPrice comicPrice : precos) {
            QuadrinhoPreco quadrinhoPreco = new QuadrinhoPreco();

            quadrinhoPreco.setQuadrinho(quadrinho);
            quadrinhoPreco.setTipo(comicPrice.getType());
            quadrinhoPreco.setPreco(comicPrice.getPrice());

            quadrinhoPrecos.add(quadrinhoPrecoRepository.save(quadrinhoPreco));
        }
        return quadrinhoPrecos;
    }
}
