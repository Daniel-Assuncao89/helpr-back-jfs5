package org.soulcodeacademy.helpr.services;
import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.dto.ClienteDTO;
import org.soulcodeacademy.helpr.repositories.ClienteRepository;
import org.soulcodeacademy.helpr.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
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

        Cliente novoCliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpf(), encoder.encode(dto.getSenha()), dto.getTelefone());

        return this.clienteRepository.save(novoCliente);
    }

    public Cliente atualizar(Integer idCliente, ClienteDTO dto) {
        Cliente clienteAtual = this.getCliente(idCliente);
        clienteAtual.setNome(dto.getNome());
        clienteAtual.setEmail(dto.getEmail());
        clienteAtual.setCpf(dto.getCpf());
        clienteAtual.setSenha(encoder.encode(dto.getSenha()));
        clienteAtual.setTelefone(dto.getTelefone());

        return this.clienteRepository.save(clienteAtual);
    }

    public void deletar(Integer idCliente) {
        Cliente cliente = this.getCliente(idCliente);
        this.clienteRepository.delete(cliente);
    }
}

