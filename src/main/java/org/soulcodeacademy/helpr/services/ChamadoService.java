package org.soulcodeacademy.helpr.services;
import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.ChamadoDTO;
import org.soulcodeacademy.helpr.domain.enums.StatusChamado;
import org.soulcodeacademy.helpr.repositories.ChamadoRepository;
import org.soulcodeacademy.helpr.services.errors.LimiteQuantidadeError;
import org.soulcodeacademy.helpr.services.errors.ParametrosInsuficientesError;
import org.soulcodeacademy.helpr.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FuncionarioService funcionarioService;

    public List<Chamado> listarChamados() {
        return this.chamadoRepository.findAll();
    }

    public Chamado getChamado(Integer idChamado) {

        return this.chamadoRepository.findById(idChamado)
                .orElseThrow(() -> new RecursoNaoEncontradoError("Chamado não encontrado"));
    }

    public Chamado salvar(ChamadoDTO dto) {

        Cliente cliente = this.clienteService.getCliente(dto.getIdCliente());
        Chamado chamado = new Chamado(null, dto.getTitulo(), dto.getDescricao());
        chamado.setCliente(cliente);

        return this.chamadoRepository.save(chamado);
    }

    public Chamado atualizar(Integer idChamado, ChamadoDTO dto) {
        Chamado chamadoAtual = this.getChamado(idChamado);
        Cliente cliente = this.clienteService.getCliente(dto.getIdCliente());
        chamadoAtual.setTitulo(dto.getTitulo());
        chamadoAtual.setDescricao(dto.getDescricao());
        chamadoAtual.setCliente(cliente);

        if (dto.getIdFuncionario() == null) {
            throw new ParametrosInsuficientesError("idFuncionario obrigatório");
        } else {
            if(this.chamadoRepository.findByFuncionario(this.funcionarioService.getFuncionario(dto.getIdFuncionario())).size() >= 5){
                throw new LimiteQuantidadeError("Funcionário com mais de 5 chamados abertos");
            } else {
                Funcionario funcionario = this.funcionarioService.getFuncionario(dto.getIdFuncionario());
                switch (dto.getStatus()) {
                    case RECEBIDO -> {
                        chamadoAtual.setStatus(StatusChamado.RECEBIDO);
                        chamadoAtual.setFuncionario(null);
                        chamadoAtual.setDataFechamento(null);
                    }
                    case ATRIBUIDO -> {
                        chamadoAtual.setStatus(StatusChamado.ATRIBUIDO);
                        chamadoAtual.setFuncionario(funcionario);
                        chamadoAtual.setDataFechamento(null);
                    }
                    case CONCLUIDO -> {
                        chamadoAtual.setStatus(StatusChamado.CONCLUIDO);
                        chamadoAtual.setFuncionario(funcionario);
                        chamadoAtual.setDataFechamento(LocalDate.now());
                    }

                    case ARQUIVADO -> {
                        chamadoAtual.setStatus(StatusChamado.ARQUIVADO);
                        if(chamadoAtual.getDataFechamento() == null){
                            chamadoAtual.setDataFechamento(LocalDate.now());
                        }
                    }
                }
            }
        }

        return this.chamadoRepository.save(chamadoAtual);
    }

    public List<Chamado> listarPorStatus(StatusChamado status) {
        return this.chamadoRepository.findByStatus(status);
    }

    public List<Chamado> listarPorFuncionario(Integer idFuncionario) {
        Funcionario funcionario = this.funcionarioService.getFuncionario(idFuncionario);
        return this.chamadoRepository.findByFuncionario(funcionario);
    }

    public List<Chamado> listarPorCliente(Integer idCliente) {
        Cliente cliente = this.clienteService.getCliente(idCliente);
        return this.chamadoRepository.findByCliente(cliente);
    }

    public List<Chamado> listarPorIntervaloDatas(LocalDate data1, LocalDate data2) {
        return this.chamadoRepository.buscarEntreDatas(data1, data2);
    }

    public List<Chamado> listarArquivados() {
        return this.chamadoRepository.buscarArquivados();
    }
}
