package org.soulcodeacademy.helpr.domain.dto;

import javax.validation.constraints.*;


public class CargoDTO {


    @NotBlank(message = "Campo nome é obrigatório")
    private String nome;

    @NotBlank(message = "Campo descrição é obrigatório")
    private String descricao;


    @NotNull(message = "Campo salário é obrigatório")
    @Min(value = 500, message = "Campo salário inválido")
    @Max(value = 100000, message = "Campo salário inválido")
    private Double salario;

    @NotNull(message = "Limite de funcionário com esse cargo é obrigatório")
    private Integer limiteFuncionario;

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
