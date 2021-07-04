package com.p4bloh.comics.domain.service;

import com.p4bloh.comics.domain.model.Quadrinho;
import com.p4bloh.comics.domain.model.QuadrinhoAutor;
import com.p4bloh.comics.domain.repository.QuadrinhoAutorRepository;
import com.p4bloh.comics.marvel.model.CreatorSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuadrinhoAutorService {

    @Autowired
    private QuadrinhoAutorRepository quadrinhoAutorRepository;

    public List<QuadrinhoAutor> salvar(Quadrinho quadrinho, List<CreatorSummary> nomes){

        List<QuadrinhoAutor> quadrinhoAutores = new ArrayList<>();

        for (CreatorSummary creatorSummary: nomes){

            QuadrinhoAutor quadrinhoAutor = new QuadrinhoAutor();

            quadrinhoAutor.setQuadrinho(quadrinho);
            quadrinhoAutor.setName(creatorSummary.getName());

            quadrinhoAutores.add(quadrinhoAutorRepository.save(quadrinhoAutor));
        }

        return quadrinhoAutores;

    }
}
