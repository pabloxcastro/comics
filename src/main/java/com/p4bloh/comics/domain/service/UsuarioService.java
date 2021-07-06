package com.p4bloh.comics.domain.service;

import com.p4bloh.comics.domain.dto.UsuarioRequest;
import com.p4bloh.comics.domain.exception.EntidadeJaExisteException;
import com.p4bloh.comics.domain.model.Usuario;
import com.p4bloh.comics.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(UsuarioRequest usuarioRequest){

        Usuario emailExiste = usuarioRepository.findByEmailEquals(usuarioRequest.getEmail());
        Usuario cpfExiste = usuarioRepository.findByCpfEquals(usuarioRequest.getCpf());

        if (emailExiste == null && cpfExiste == null) {
            Usuario usuarioNovo = usuarioRepository.save(usuarioRequest.toObj());

            return usuarioNovo;

        } else if (emailExiste != null) {
            throw new EntidadeJaExisteException("Usuario", "email", usuarioRequest.getEmail());

        } else {
            throw new EntidadeJaExisteException("Usuário", "CPF", usuarioRequest.getCpf());
        }
    }
}
