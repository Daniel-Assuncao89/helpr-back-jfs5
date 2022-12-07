package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.Usuario;
import org.soulcodeacademy.helpr.domain.dto.UsuarioDTO;
import org.soulcodeacademy.helpr.repositories.UsuarioRepository;
import org.soulcodeacademy.helpr.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Usuario findByEmail(String email) {
        return this.usuarioRepository.findByEmail(email).orElseThrow((
        )-> new RecursoNaoEncontradoError("Email não encontrado!"));
    }

    public Usuario findByCPF(String cpf) {
        return this.usuarioRepository.findByCpf(cpf).orElseThrow((
        )-> new RecursoNaoEncontradoError("CPF não encontrado!"));
    }

    public List <Usuario> findByName(String nome) {
        return this.usuarioRepository.findByNomeContaining(nome);
    }
    public Usuario findPerfilByEmail(String email) {
        return this.usuarioRepository.findPerfilByEmail(email).orElseThrow((
        )-> new RecursoNaoEncontradoError("Email não encontrado!"));
    }

    public Usuario findPerfilById(Integer id) {
        return this.usuarioRepository.findPerfilById(id).orElseThrow((
        )-> new RecursoNaoEncontradoError("Perfil não encontrado!"));
    }
}