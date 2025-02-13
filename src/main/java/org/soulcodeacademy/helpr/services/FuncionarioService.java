package org.soulcodeacademy.helpr.services;
import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.FuncionarioDTO;
import org.soulcodeacademy.helpr.repositories.FuncionarioRepository;
import org.soulcodeacademy.helpr.services.errors.LimiteQuantidadeError;
import org.soulcodeacademy.helpr.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private PasswordEncoder encoder;

    public List<Funcionario> listar() {
        return this.funcionarioRepository.findAll();
    }

    public List<Funcionario> listarPorFaixaSalarial(Double valor1, Double valor2) {
        return this.funcionarioRepository.findBySalarioEntreFaixas(valor1, valor2);
    }

    public Funcionario getFuncionario(Integer idFuncionario) {
        return this.funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RecursoNaoEncontradoError("O funcionário não foi encontrado!"));
    }

    public Funcionario salvar(FuncionarioDTO dto) {
        Cargo cargo = this.cargoService.getCargo(dto.getIdCargo());
        Integer quantidade = this.funcionarioRepository.CountCargo(dto.getIdCargo());

        if(quantidade < cargo.getLimiteFuncionario()){
            Funcionario funcionario = new Funcionario(null, dto.getNome(), dto.getEmail(), dto.getCpf(), encoder.encode(dto.getSenha()), dto.getFoto(), cargo);
            Funcionario salvo = this.funcionarioRepository.save(funcionario);

            return salvo;
        } else {
            throw new LimiteQuantidadeError("Quantidade de funcionários com esse cargo acima do permitido");
        }
    }

    public Funcionario atualizar(Integer idFuncionario, FuncionarioDTO dto) {

        Funcionario funcionarioAtual = this.getFuncionario(idFuncionario);
        Cargo cargo = this.cargoService.getCargo(dto.getIdCargo());
        Integer quantidade = this.funcionarioRepository.CountCargo(dto.getIdCargo());

        if(quantidade < cargo.getLimiteFuncionario()){

            funcionarioAtual.setNome(dto.getNome());
            funcionarioAtual.setEmail(dto.getEmail());
            funcionarioAtual.setCpf(dto.getCpf());
            funcionarioAtual.setSenha(encoder.encode(dto.getSenha()));
            funcionarioAtual.setCargo(cargo);
            funcionarioAtual.setFoto(dto.getFoto());

            Funcionario atualizado = this.funcionarioRepository.save(funcionarioAtual);
            return atualizado;

        } else {
            throw new LimiteQuantidadeError("Quantidade de funcionários com esse cargo acima do permitido");
        }

    }

    public void deletar(Integer idFuncionario) {
        Funcionario funcionario = this.getFuncionario(idFuncionario);
        this.funcionarioRepository.delete(funcionario);
    }

    public Funcionario findByEmail(String email) {
        return this.funcionarioRepository.findByEmail(email);
    }

    public Funcionario salvarFoto(MultipartFile file, Integer idFuncionario) throws IOException {
        // Set the form data into entity
        Funcionario funcionario = this.getFuncionario(idFuncionario);

        funcionario.setFoto(file.getBytes().toString());

        return this.funcionarioRepository.save(funcionario);


    }

    public List<Funcionario> findFotoNull(){
        return this.funcionarioRepository.findFotoNull();
    }

}
