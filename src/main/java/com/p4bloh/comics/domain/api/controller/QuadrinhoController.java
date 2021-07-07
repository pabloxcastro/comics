package com.p4bloh.comics.domain.api.controller;

import com.p4bloh.comics.domain.dto.QuadrinhoRequest;
import com.p4bloh.comics.domain.dto.QuadrinhoResponse;
import com.p4bloh.comics.domain.exception.EntidadeNaoEncontradaException;
import com.p4bloh.comics.domain.model.Quadrinho;
import com.p4bloh.comics.domain.model.QuadrinhoAutor;
import com.p4bloh.comics.domain.model.QuadrinhoPreco;
import com.p4bloh.comics.domain.model.Usuario;
import com.p4bloh.comics.domain.repository.QuadrinhoAutorRepository;
import com.p4bloh.comics.domain.repository.QuadrinhoPrecoRepository;
import com.p4bloh.comics.domain.repository.QuadrinhoRepository;
import com.p4bloh.comics.domain.repository.UsuarioRepository;
import com.p4bloh.comics.domain.service.QuadrinhoService;
import com.p4bloh.comics.marvel.client.ComicClient;
import com.p4bloh.comics.marvel.model.ComicDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/quadrinhos")
public class QuadrinhoController {

    @Autowired
    private QuadrinhoService quadrinhoService;

    @Autowired
    private QuadrinhoRepository quadrinhoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private QuadrinhoPrecoRepository quadrinhoPrecoRepository;

    @Autowired
    private QuadrinhoAutorRepository quadrinhoAutorRepository;

    @Autowired
    private ComicClient comicClient;

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody QuadrinhoRequest quadrinhoRequest) {
        try {
            QuadrinhoResponse quadrinhoNovo = quadrinhoService.salvar(quadrinhoRequest);
            return ResponseEntity.status(201).body(quadrinhoNovo);

        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/marvel")
    public ComicDataWrapper catalogoComic() {
        ComicDataWrapper catalogo = comicClient.catalogo();
        return catalogo;
    }

    @GetMapping("/marvel/{comicId}")
    public ComicDataWrapper buscarComic(@PathVariable Long comicId) {
        ComicDataWrapper comic = comicClient.getComic(comicId);
        return comic;
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> findQuadrinhosUsuario(@PathVariable Long usuarioId) {

        try {
            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário", usuarioId));

            List<Quadrinho> quadrinhos = quadrinhoRepository.findByUsuarioEquals(usuario);

            if (quadrinhos.size() == 0) {
                return ResponseEntity.badRequest().body("Não há quadrinhos para o usuário informado");
            }

            List<QuadrinhoResponse> quadrinhoResponses = new ArrayList<>();

            for (Quadrinho quadrinho : quadrinhos) {
                Long quadrinhoId = quadrinho.getId();

                Boolean descontoAtivo = quadrinhoService.getDescontoAtivo(quadrinho.getDiaDesconto());

                List<QuadrinhoPreco> quadrinhoPrecos = quadrinhoPrecoRepository.findByQuadrinhoEquals(quadrinho);
                List<QuadrinhoAutor> quadrinhoAutores = quadrinhoAutorRepository.findByQuadrinhoEquals(quadrinho);

                quadrinhoResponses.add(QuadrinhoResponse.toDto(quadrinho, descontoAtivo, quadrinhoPrecos, quadrinhoAutores));
            }

            return ResponseEntity.ok(quadrinhoResponses);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
