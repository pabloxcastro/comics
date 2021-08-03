package com.p4bloh.comics.domain.api.controller;

import com.p4bloh.comics.domain.dto.UsuarioPutRequest;
import com.p4bloh.comics.domain.dto.UsuarioPutResponse;
import com.p4bloh.comics.domain.dto.UsuarioRequest;
import com.p4bloh.comics.domain.dto.UsuarioResponse;
import com.p4bloh.comics.domain.exception.EntidadeNaoEncontradaException;
import com.p4bloh.comics.domain.model.Usuario;
import com.p4bloh.comics.domain.repository.UsuarioRepository;
import com.p4bloh.comics.domain.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @ApiOperation(value = "Cadastro de usuários")
    public ResponseEntity<?> adicionar(@Valid @RequestBody UsuarioRequest usuarioRequest){
        Usuario usuarioNovo = usuarioService.salvar(usuarioRequest.toObj());
        return ResponseEntity.status(201).body(UsuarioResponse.toDto(usuarioNovo));
    }

    @GetMapping
    @ApiOperation(value = "Lista de usuários")
    public ResponseEntity<?> listar(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios == null) return ResponseEntity.notFound().build();

        List<UsuarioResponse> usuarioResponses = new ArrayList<>();

        for (Usuario usuario: usuarios){
            usuarioResponses.add(UsuarioResponse.toDto(usuario));
        }

        return ResponseEntity.ok(usuarioResponses);

    }
    
    @PatchMapping("/{id}")
    @ApiOperation(value = "Alteração de nome e data de nascimento de usuário")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id,
                                              @RequestBody UsuarioPutRequest usuarioRequest){
        try {
            Optional<Usuario> usuarioAtual = usuarioRepository.findById(id);

            if (usuarioAtual.isEmpty()){
                throw new EntidadeNaoEncontradaException("Usuário", id);
            }

            BeanUtils.copyProperties(usuarioRequest.toObj(), usuarioAtual.get(),
                    "id", "cpf", "email");

            Usuario usuarioAtualizado = usuarioRepository.save(usuarioAtual.get());

            return ResponseEntity.ok(UsuarioPutResponse.toDto(usuarioAtualizado));

        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclusão de usuário")
    public ResponseEntity<?> excluir(@PathVariable Long id){
        usuarioService.excluir(id);
        return ResponseEntity.ok().build();
    }

}
