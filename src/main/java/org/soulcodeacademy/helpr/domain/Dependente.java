package org.soulcodeacademy.helpr.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.soulcodeacademy.helpr.domain.enums.StatusChamado;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Dependente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDependente;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataDeNascimento;

    @Column
    private String escolaridade;


    @ManyToOne // Muitos Dependentes P/ Um Funcionario
    @JoinColumn(name = "id_funcionario") // FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario)
    private Funcionario funcionario;


    public Dependente() {
    }

    public Dependente(Integer idDependente, String nome, String cpf, LocalDate dataDeNascimento, String escolaridade, Funcionario funcionario) {
        this.idDependente = idDependente;
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.escolaridade = escolaridade;
        this.funcionario = funcionario;
    }

    public Integer getIdDependente() {
        return idDependente;
    }

    public void setIdDependente(Integer idDependente) {
        this.idDependente = idDependente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
