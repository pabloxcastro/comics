package com.p4bloh.comics.domain.service;

import com.p4bloh.comics.domain.exception.EntidadeNaoEncontradaException;
import com.p4bloh.comics.domain.model.Livro;
import com.p4bloh.comics.domain.model.Usuario;
import com.p4bloh.comics.domain.repository.LivroRepository;
import com.p4bloh.comics.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Livro salvar(Livro livro){
        Long usuarioId = livro.getUsuario().getId();

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(()->new EntidadeNaoEncontradaException("Usuário", usuarioId));

        livro.setUsuario(usuario);

        Livro livroNovo = livroRepository.save(livro);

        return livroNovo;
    }
}
