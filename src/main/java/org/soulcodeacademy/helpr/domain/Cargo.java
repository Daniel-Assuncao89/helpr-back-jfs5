package org.soulcodeacademy.helpr.domain;

import javax.persistence.*; // JPA = JAVA PERSISTENCE API

// Transformar a classe em uma entidade/tabela no banco
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


    public Cargo() {}

    public Cargo(Integer idCargo, String nome, String descricao, Double salario) {
        this.idCargo = idCargo;
        this.nome = nome;
        this.descricao = descricao;
        this.salario = salario;
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
}
