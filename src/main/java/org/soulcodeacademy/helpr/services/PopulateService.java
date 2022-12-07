package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.enums.Perfil;
import org.soulcodeacademy.helpr.domain.enums.StatusChamado;
import org.soulcodeacademy.helpr.repositories.CargoRepository;
import org.soulcodeacademy.helpr.repositories.ChamadoRepository;
import org.soulcodeacademy.helpr.repositories.ClienteRepository;
import org.soulcodeacademy.helpr.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

// Torna o objeto de PopulateService disponível para toda a aplicação (global)
@Service // indica para o Spring que esta classe será gerenciada por ele
public class PopulateService {
    @Autowired // injetar o objeto direto na classe
    private CargoRepository cargoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void populate() {

        Cargo c1 = new Cargo(null, "Diretor Geral", "Gerencia a empresa", 30000.0);
        Cargo c2 = new Cargo(null, "Diretor de Setor", "Gerencia um setor da empresa", 18000.0);
        Cargo c3 = new Cargo(null, "Técnico geral", "Resolve os chamados urgentes", 12000.0);
        Cargo c4 = new Cargo(null, "Gerente de qualidade", "Supervisão e Controle de Qualidade", 7000.0);
        Cargo c5 = new Cargo(null, "Surpervisor Operacional", "Supervisão dos chamados urgentes", 15000.0);
        Cargo c6 = new Cargo(null, "Desenvolvedor Pleno", "Analisa e desenvolve aplicações", 15000.0);
        Cargo c7 = new Cargo(null, "Desenvolvedor Júnior", "Suporte ao Desenvolvimento", 12000.0);

        Funcionario f1 = new Funcionario(null, "Renato Pereira", "renato.pereira@gmail.com", "68258098144", encoder.encode("12345"), null, c1);
        f1.setPerfil(Perfil.ADMIN);

        Funcionario f2 = new Funcionario(null, "Victor Icoma", "victor.icoma@gmail.com", "51127383671", encoder.encode("12345"), null, c2);
        Funcionario f3 = new Funcionario(null, "José Bonifácio", "j.bonifacio@gmail.com", "68579976081", encoder.encode("12345"), null, c3);
        Funcionario f4 = new Funcionario(null, "Andreia Soares", "soaresandreia@gmail.com", "51699166048", encoder.encode("12345"), null, c4);
        Funcionario f5 = new Funcionario(null, "Heitor Ferreira", "hferreira@gmail.com", "36684307052", encoder.encode("12345"), null, c5);

        Funcionario f6 = new Funcionario(null, "Julia Alves", "ju.alves@gmail.com", "71715732065", encoder.encode("12345"), null, c6);
        f6.setPerfil(Perfil.ADMIN);
        Funcionario f7 = new Funcionario(null, "Francisco Almeida", "franalmeida@gmail.com", "62624487050", encoder.encode("12345"), null, c7);
        f7.setPerfil(Perfil.ADMIN);
        Funcionario f8 = new Funcionario(null, "Gustavo Moraes", "gustavo.moraes@gmail.com", "60198195079", encoder.encode("12345"), null, c7);
        f8.setPerfil(Perfil.ADMIN);
        Funcionario f9 = new Funcionario(null, "Albertina Santos", "albertinasantos@gmail.com", "04999182076", encoder.encode("12345"), null, c7);
        f9.setPerfil(Perfil.ADMIN);
        Funcionario f10 = new Funcionario(null, "Isis Maldonado", "maldonadoisis@gmail.com", "38446581060", encoder.encode("12345"), null, c6);
        f10.setPerfil(Perfil.ADMIN);

        Cliente cl1 = new Cliente(null, "José Almir", "jose.almir@gmail.com", "12659185115", encoder.encode("batata"), "9999999999");
        Cliente cl2 = new Cliente(null, "Pedro João", "pedro@gmail.com", "37734168302", encoder.encode("batata"), "9999999997");
        Cliente cl3 = new Cliente(null, "Maria Isabel", "maria.isabel@gmail.com", "90349411085", encoder.encode("batata"), "11963440585");
        Cliente cl4 = new Cliente(null, "Laura Batista", "batistalaura@gmail.com", "62539204058", encoder.encode("batata"), "15937854400");

        Chamado ch1 = new Chamado(null, "Primeiro chamado do sistema", "Revisar as entidades criadas");
        ch1.setCliente(cl1);

        Chamado ch2 = new Chamado(null, "Ativar VPN do sistema", "Conectar aos servidores remotos.");
        ch2.setCliente(cl2);
        ch2.setFuncionario(f1);
        ch2.setStatus(StatusChamado.ATRIBUIDO);

        Chamado ch3 = new Chamado(null, "Padronizar os títulos e rotas da aplicação", "Corrigir componentes HTML que estão fora da rota.");
        ch3.setCliente(cl3);
        ch3.setFuncionario(f8);
        ch3.setStatus(StatusChamado.RECEBIDO);

        Chamado ch4 = new Chamado(null, "Trocar as cores da aplicação", "Atualizar o estilo de acordo com as novas cores desejadas pelo cliente.");
        ch4.setCliente(cl4);
        ch4.setFuncionario(f5);
        ch4.setStatus(StatusChamado.RECEBIDO);

        Chamado ch5 = new Chamado(null, "Correção de Bug na Autenticação", "Corrigir erro no Google Anthenticator.");
        ch5.setCliente(cl1);
        ch5.setFuncionario(f7);
        ch5.setStatus(StatusChamado.RECEBIDO);

        Chamado ch6 = new Chamado(null, "Relatório de Performance", "Elaborar dashboard para análise de performance da equipe.");
        ch6.setCliente(cl4);
        ch6.setFuncionario(f10);
        ch6.setStatus(StatusChamado.CONCLUIDO);

        Chamado ch7 = new Chamado(null, "Atualização do Banco de Dados", "Cliente passará a considerar mais uma entidade.");
        ch7.setCliente(cl3);
        ch7.setFuncionario(f9);
        ch7.setStatus(StatusChamado.CONCLUIDO);

        this.cargoRepository.saveAll(List.of(c1, c2, c3, c4, c5, c6, c7));
        this.funcionarioRepository.saveAll(List.of(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10));
        this.clienteRepository.saveAll(List.of(cl1, cl2, cl3, cl4));
        this.chamadoRepository.saveAll(List.of(ch1, ch2, ch3, ch4, ch5, ch6, ch7));
    }
}

// O objetivo desta classe é inserir no banco, dados fictícios (de teste)
// IOC = Inversion of Control = Inversão de Controle = É ele quem manda nas instâncias
// Container = é o local onde o Spring guarda os objetos anotados
// @Service = indica que a classe é um serviço
// Injeção de Dependências = quando o Spring injeta os objetos na classe