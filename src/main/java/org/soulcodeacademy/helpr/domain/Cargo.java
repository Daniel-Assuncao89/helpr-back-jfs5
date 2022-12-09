package org.soulcodeacademy.helpr.domain;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Cargo {
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCargo;

    @Column(nullable = false, length = 50)
    private String nome;
    @Column(nullable = false, length = 120)
    private String descricao;
    @Column(nullable = false)
    private Double salario;

    @Column(nullable = false, length = 10)
    private Integer limiteFuncionario;

    public Cargo() {}

    public Cargo(Integer idCargo, String nome, String descricao, Double salario, Integer limiteFuncionario) {
        this.idCargo = idCargo;
        this.nome = nome;
        this.descricao = descricao;
        this.salario = salario;
        this.limiteFuncionario = limiteFuncionario;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Integer getLimiteFuncionario() {
        return limiteFuncionario;
    }

    public void setLimiteFuncionario(Integer limiteFuncionario) {
        this.limiteFuncionario = limiteFuncionario;
    }
}
