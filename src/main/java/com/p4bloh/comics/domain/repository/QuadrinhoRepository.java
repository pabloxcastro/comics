package com.p4bloh.comics.domain.repository;

import com.p4bloh.comics.domain.model.Quadrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuadrinhoRepository extends JpaRepository<Quadrinho, Long> {
}
