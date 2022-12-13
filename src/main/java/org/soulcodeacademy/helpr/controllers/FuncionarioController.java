package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.FuncionarioDTO;
import org.soulcodeacademy.helpr.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/funcionarios")
    public List<Funcionario> listar() {
        return this.funcionarioService.listar();
    }


    @GetMapping("/funcionarios/salario")
    public List<Funcionario> listarPorFaixaSalarial(@RequestParam Double valor1, @RequestParam Double valor2) {
        return this.funcionarioService.listarPorFaixaSalarial(valor1, valor2);
    }


    @GetMapping("/funcionarios/{idFuncionario}")
    public Funcionario getFuncionario(@PathVariable Integer idFuncionario) {
        return this.funcionarioService.getFuncionario(idFuncionario);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/funcionarios")
    public Funcionario salvar(@Valid @RequestBody FuncionarioDTO dto) {
        Funcionario funcionario = this.funcionarioService.salvar(dto);
        return funcionario;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/funcionarios/{idFuncionario}")
    public Funcionario atualizar(@PathVariable Integer idFuncionario, @Valid @RequestBody FuncionarioDTO dto) {
        Funcionario atualizado = this.funcionarioService.atualizar(idFuncionario, dto);
        return atualizado;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/funcionarios/{idFuncionario}")
    public void deletar(@PathVariable Integer idFuncionario) {
        this.funcionarioService.deletar(idFuncionario);
    }

    @GetMapping("/funcionarios/email/{email}")
    public Funcionario findByEmail(@PathVariable String email) {
        return this.funcionarioService.findByEmail(email);
    }

    @PostMapping(value = "/funcionarios/foto/{idFuncionario}")
    public Funcionario uploadToDatabase(@PathVariable Integer idFuncionario, @RequestParam MultipartFile file)
            throws IOException {
        return this.funcionarioService.salvarFoto(file, idFuncionario);

    }
}