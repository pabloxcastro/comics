package com.p4bloh.comics.domain.repository;

import com.p4bloh.comics.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmailEquals(String email);
    Usuario findByCpfEquals(String cpf);
}
