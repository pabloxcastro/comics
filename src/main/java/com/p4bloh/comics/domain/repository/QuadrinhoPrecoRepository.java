package com.p4bloh.comics.domain.repository;

import com.p4bloh.comics.domain.model.Quadrinho;
import com.p4bloh.comics.domain.model.QuadrinhoPreco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface QuadrinhoPrecoRepository extends JpaRepository<QuadrinhoPreco, Long> {

    ArrayList<QuadrinhoPreco> findByQuadrinhoEquals(Quadrinho quadrinho);
}
