package com.p4bloh.comics.domain.repository;

import com.p4bloh.comics.domain.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
