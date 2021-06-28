package com.p4bloh.comics.domain.service;

import com.p4bloh.comics.domain.exception.EntidadeJaExisteException;
import com.p4bloh.comics.domain.model.Usuario;
import com.p4bloh.comics.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){

        Usuario emailExiste = usuarioRepository.findByEmailEquals(usuario.getEmail());
        Usuario cpfExiste = usuarioRepository.findByCpfEquals(usuario.getCpf());

        if (emailExiste == null && cpfExiste == null) {
            Usuario usuarioNovo = usuarioRepository.save(usuario);

            return usuarioNovo;

        } else if (emailExiste != null) {
            throw new EntidadeJaExisteException("Usuario", "email", usuario.getEmail());

        } else {
            throw new EntidadeJaExisteException("Usuário", "CPF", usuario.getCpf());
        }
    }
}
