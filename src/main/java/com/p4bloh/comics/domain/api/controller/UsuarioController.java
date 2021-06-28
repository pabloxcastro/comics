package com.p4bloh.comics.domain.api.controller;

import com.p4bloh.comics.domain.exception.EntidadeJaExisteException;
import com.p4bloh.comics.domain.model.Usuario;
import com.p4bloh.comics.domain.repository.UsuarioRepository;
import com.p4bloh.comics.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody @Valid Usuario usuario){
        Usuario usuarioNovo = usuarioService.salvar(usuario);
        return ResponseEntity.status(201).body(usuarioNovo);
    }
}