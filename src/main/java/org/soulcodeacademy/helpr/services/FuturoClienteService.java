package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.FuturoCliente;
import org.soulcodeacademy.helpr.domain.dto.FuturoClienteDTO;
import org.soulcodeacademy.helpr.repositories.FuturoClienteRepository;
import org.soulcodeacademy.helpr.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuturoClienteService {

    @Autowired
    private FuturoClienteRepository futuroClienteRepository;

    public List<FuturoCliente> listar(){
        return this.futuroClienteRepository.findAll();
    }

    public FuturoCliente getFuturoCliente(Integer idFuturoCliente){
        return this.futuroClienteRepository.findById(idFuturoCliente).orElseThrow(() -> new RecursoNaoEncontradoError("Futuro Cliente não encontrado"));
    }

    public FuturoCliente getFuturoClienteByCpf(String cpf){
        return this.futuroClienteRepository.findByCpf(cpf).orElseThrow(() -> new RecursoNaoEncontradoError("CPF não encontrado"));
    }

    public FuturoCliente getFuturoClienteByEmail(String email){
        return this.futuroClienteRepository.findByEmail(email).orElseThrow(() -> new RecursoNaoEncontradoError("Email não encontrado"));
    }

    public FuturoCliente salvar(FuturoClienteDTO dto){
        FuturoCliente futuroCliente = new FuturoCliente(null, dto.getNome(), dto.getTelefone(), dto.getEmail(), dto.getCpf());

        return this.futuroClienteRepository.save(futuroCliente);
    }

    public void deletar(Integer idFuturoCliente){
        FuturoCliente futuroCliente = getFuturoCliente(idFuturoCliente);
        this.futuroClienteRepository.delete(futuroCliente);
    }
}
