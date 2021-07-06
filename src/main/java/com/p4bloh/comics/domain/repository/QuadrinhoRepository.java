package com.p4bloh.comics.domain.repository;

import com.p4bloh.comics.domain.model.Quadrinho;
import com.p4bloh.comics.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface QuadrinhoRepository extends JpaRepository<Quadrinho, Long> {

    ArrayList<Quadrinho> findByUsuarioEquals(Usuario usuario);
}
