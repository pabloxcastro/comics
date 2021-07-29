package com.p4bloh.comics.domain.service;

import com.p4bloh.comics.domain.exception.EntidadeEmUsoException;
import com.p4bloh.comics.domain.exception.EntidadeJaExisteException;
import com.p4bloh.comics.domain.exception.EntidadeNaoEncontradaException;
import com.p4bloh.comics.domain.model.Usuario;
import com.p4bloh.comics.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){

        Usuario emailExiste = usuarioRepository.findByEmailEquals(usuario.getEmail());
        Usuario cpfExiste = usuarioRepository.findByCpfEquals(usuario.getCpf());

        if (emailExiste == null && cpfExiste == null) {

            return usuarioRepository.save(usuario);

        } else if (emailExiste != null) {
            throw new EntidadeJaExisteException("Usuario", "email", usuario.getEmail());

        } else {
            throw new EntidadeJaExisteException("Usuário", "CPF", usuario.getCpf());
        }
    }

    public void excluir(Long id){
        try {
            usuarioRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException("Usuário", id);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException("Usuário");
        }
    }
}
