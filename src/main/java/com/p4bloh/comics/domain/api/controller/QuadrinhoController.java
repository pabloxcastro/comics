package com.p4bloh.comics.domain.api.controller;

import com.p4bloh.comics.domain.dto.QuadrinhoDto;
import com.p4bloh.comics.domain.dto.QuadrinhoResponseDto;
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
import java.util.Optional;

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
    public ResponseEntity<?> adicionar(@RequestBody QuadrinhoDto quadrinhoDto){
        QuadrinhoResponseDto quadrinhoNovo = quadrinhoService.salvar(quadrinhoDto);
        return ResponseEntity.ok(quadrinhoNovo);
    }

    @PutMapping
    public ResponseEntity<?> gerarIsbn(@RequestBody Boolean gerar){

        List<Quadrinho> quadrinhos = quadrinhoRepository.findByIsbnIsNull();

        for (Quadrinho quadrinho: quadrinhos){

            Long quadrinhoId = quadrinho.getId();
            Optional<Quadrinho> quadrinhoAtual = quadrinhoRepository.findById(quadrinhoId);

            if (quadrinhoAtual.isPresent()) {
                quadrinhoAtual.get().setIsbn("123456789");
                quadrinhoRepository.save(quadrinhoAtual.get());
            }
        }

        return ResponseEntity.ok(quadrinhos);
    }


    @GetMapping
    public ComicDataWrapper catalogoComic() {
        ComicDataWrapper catalogo = comicClient.catalogo();
        return catalogo;
    }

    @GetMapping({"/{comicId}"})
    public ComicDataWrapper buscarComic(@PathVariable int comicId ) {
        ComicDataWrapper comic = comicClient.getComic(comicId);
        return comic;
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> listarQuadrinhos(@PathVariable Long usuarioId){

        try {
            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário", usuarioId));

            List<Quadrinho> quadrinhos = quadrinhoRepository.findByUsuarioEquals(usuario);

            if (quadrinhos.size() == 0){
                return ResponseEntity.badRequest().body("Não há quadrinhos para o usuário informado");
            }

            List<QuadrinhoResponseDto> quadrinhoResponseDtos = new ArrayList<>();

            for (Quadrinho quadrinho: quadrinhos){
                Long quadrinhoId = quadrinho.getId();

                List<QuadrinhoPreco> quadrinhoPrecos = quadrinhoPrecoRepository.findByQuadrinhoEquals(quadrinho);
                List<QuadrinhoAutor> quadrinhoAutores = quadrinhoAutorRepository.findByQuadrinhoEquals(quadrinho);

                quadrinhoResponseDtos.add(QuadrinhoResponseDto.toDto(quadrinho, quadrinhoPrecos, quadrinhoAutores));
            }

            return ResponseEntity.ok(quadrinhoResponseDtos);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }



       

    }


}
