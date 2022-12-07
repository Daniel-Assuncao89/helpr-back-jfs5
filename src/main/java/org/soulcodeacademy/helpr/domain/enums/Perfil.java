package org.soulcodeacademy.helpr.domain.enums;

public enum Perfil {
    ADMIN("ROLE_ADMIN"),
    FUNCIONARIO("ROLE_FUNCIONARIO"),
    CLIENTE("ROLE_CLIENTE");

    private String descricao;

    Perfil(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}