package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.FuturoCandidato;
import org.soulcodeacademy.helpr.domain.dto.FuturoCandidatoDTO;
import org.soulcodeacademy.helpr.domain.dto.FuturoClienteDTO;
import org.soulcodeacademy.helpr.domain.enums.Setor;
import org.soulcodeacademy.helpr.repositories.FuturoCandidatoRepository;
import org.soulcodeacademy.helpr.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuturoCandidatoService {

    @Autowired
    private FuturoCandidatoRepository futuroCandidatoRepository;

    public List<FuturoCandidato> list(){
        return this.futuroCandidatoRepository.findAll();
    }

    public FuturoCandidato getFuturoCandidato(Integer idFuturoCandidato){
        return this.futuroCandidatoRepository.findById(idFuturoCandidato).orElseThrow(() -> new RecursoNaoEncontradoError("Futuro Candidato não encontrado"));
    }

    public List<FuturoCandidato> listarPorNome(String nome){
        return this.futuroCandidatoRepository.findByNomeCompleto(nome);
    }

    public List<FuturoCandidato> listarPorSetor(Setor setor){
        return this.futuroCandidatoRepository.findBySetor(setor);
    }

    public FuturoCandidato listarPorEmail(String email){
        return this.futuroCandidatoRepository.findByEmail(email).orElseThrow(() -> new RecursoNaoEncontradoError("Email não encontrado"));
    }

    public FuturoCandidato salvar(FuturoCandidatoDTO dto){
        FuturoCandidato futuroCandidato = new FuturoCandidato(null,dto.getNomeCompleto(), dto.getEmail(), dto.getDescricaoHabilidades(), dto.getSetor());

        return this.futuroCandidatoRepository.save(futuroCandidato);
    }

    public void delete(Integer idFuturoCandidato){
        FuturoCandidato futuroCandidato = this.getFuturoCandidato(idFuturoCandidato);

        this.futuroCandidatoRepository.delete(futuroCandidato);
    }
}
