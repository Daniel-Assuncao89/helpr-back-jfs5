package org.soulcodeacademy.helpr.controllers;

import org.hibernate.validator.constraints.br.CPF;
import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.Usuario;
import org.soulcodeacademy.helpr.domain.enums.Perfil;
import org.soulcodeacademy.helpr.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;

@RestController
public class UsuarioController {

        @Autowired
        private UsuarioService usuarioService;

        @GetMapping("/usuarios/{email}")
        public Usuario getByEmail(@PathVariable String Email){
            return this.usuarioService.findByEmail(Email);
        }

        @GetMapping("/usuarios/{cpf}")
        public Usuario getByCPF(@PathVariable String CPF){
            return this.usuarioService.findByCPF(CPF);
        }

        @GetMapping("/usuarios/busca")
        public List <Usuario> getUsuario(@RequestParam String busca){
            return this.usuarioService.findByName(busca);
        }

        @GetMapping("/usuarios/{id}/perfil")
        public Usuario gePerfilById (@PathVariable Integer id){
            return this.usuarioService.findPerfilById(id);
        }

    }
