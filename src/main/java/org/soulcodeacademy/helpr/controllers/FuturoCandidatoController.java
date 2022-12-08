package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.FuturoCandidato;
import org.soulcodeacademy.helpr.domain.dto.FuturoCandidatoDTO;
import org.soulcodeacademy.helpr.domain.enums.Setor;
import org.soulcodeacademy.helpr.services.FuturoCandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FuturoCandidatoController {

    @Autowired
    private FuturoCandidatoService futuroCandidatoService;

    @GetMapping("/candidatos")
    public List<FuturoCandidato> listar(){
        return this.futuroCandidatoService.list();
    }

    @GetMapping("/candidatos/setor")
    public List<FuturoCandidato> listarPorSetor(@RequestParam Setor setor){
        return this.futuroCandidatoService.listarPorSetor(setor);
    }

    @GetMapping("/candidatos/email")
    public FuturoCandidato litarPorEmail(@RequestParam String email){
        return this.futuroCandidatoService.listarPorEmail(email);
    }

    @GetMapping("/candidatos/nome")
    public List<FuturoCandidato> listarPornome(@RequestParam String nome){
        return this.futuroCandidatoService.listarPorNome(nome);
    }

    @PostMapping("/candidatos")
    public FuturoCandidato salvaar(@Valid @RequestBody FuturoCandidatoDTO dto){
        return this.futuroCandidatoService.salvar(dto);
    }

    @DeleteMapping("/candidatos/{id}")
    public void delete(@PathVariable Integer id){
        this.futuroCandidatoService.delete(id);
    }
}
