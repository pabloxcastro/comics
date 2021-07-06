package com.p4bloh.comics.domain.repository;

import com.p4bloh.comics.domain.model.Quadrinho;
import com.p4bloh.comics.domain.model.QuadrinhoAutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface QuadrinhoAutorRepository extends JpaRepository<QuadrinhoAutor, Long> {

    ArrayList<QuadrinhoAutor> findByQuadrinhoEquals(Quadrinho quadrinho);
}
