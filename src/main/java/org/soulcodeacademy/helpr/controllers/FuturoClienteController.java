package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.FuturoCliente;
import org.soulcodeacademy.helpr.domain.dto.FuturoClienteDTO;
import org.soulcodeacademy.helpr.services.FuturoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FuturoClienteController {

    @Autowired
    private FuturoClienteService futuroClienteService;

    @GetMapping("/futuros-clientes")
    public List<FuturoCliente> listar(){
        return this.futuroClienteService.listar();
    }

    @GetMapping("/futuros-clientes/email")
    public FuturoCliente filtarPorEmail(@RequestParam String email){
        return this.futuroClienteService.getFuturoClienteByEmail(email);
    }

    @GetMapping("/futuros-clientes/cpf")
    public FuturoCliente filtrarPorCpf(@RequestParam String cpf){
        return this.futuroClienteService.getFuturoClienteByCpf(cpf);
    }

    @PostMapping("/futuros-clientes")
    public FuturoCliente salvar(@Valid @RequestBody FuturoClienteDTO dto){
        return this.futuroClienteService.salvar(dto);
    }

    @DeleteMapping("/futuros-clientes/{idFuturosClientes}")
    public void delete(@PathVariable Integer idFuturosClientes){
         this.futuroClienteService.deletar(idFuturosClientes);
    }
}
