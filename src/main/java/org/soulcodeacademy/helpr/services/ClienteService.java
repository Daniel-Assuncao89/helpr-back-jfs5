package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.dto.ClienteDTO;
import org.soulcodeacademy.helpr.repositories.ClienteRepository;
import org.soulcodeacademy.helpr.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // torna o objeto da classe injetavel
public class ClienteService {
    @Autowired // injeção
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<Cliente> listarTodos() {
        return this.clienteRepository.findAll();
    }

    public Cliente getCliente(Integer idCliente) {
        return this.clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RecursoNaoEncontradoError("Cliente não encontrado!"));
    }

    public Cliente salvar(ClienteDTO dto) {
        // Criação da entidade Cliente, a partir dos dados validados do DTO
        Cliente novoCliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpf(), encoder.encode(dto.getSenha()), dto.getTelefone());

        return this.clienteRepository.save(novoCliente);
    }

    public Cliente atualizar(Integer idCliente, ClienteDTO dto) {
        Cliente clienteAtual = this.getCliente(idCliente);

        if(dto.getEmail().equals(clienteAtual.getEmail()) && dto.getCpf().equals(clienteAtual.getCpf())){
            clienteAtual.setEmail(dto.getEmail());
            clienteAtual.setCpf(dto.getCpf());
        } else {
            throw new DataIntegrityViolationException("Não é possivel alterar o email/cpf");
        }

        clienteAtual.setNome(dto.getNome());
        clienteAtual.setSenha(encoder.encode(dto.getSenha()));
        clienteAtual.setTelefone(dto.getTelefone());

        return this.clienteRepository.save(clienteAtual);
    }

    public void deletar(Integer idCliente) {
        Cliente cliente = this.getCliente(idCliente);
        this.clienteRepository.delete(cliente);
    }
}

// Quando usar entidade e dto?
// Entidade = retorno dos dados
// DTO = entrada de dados
