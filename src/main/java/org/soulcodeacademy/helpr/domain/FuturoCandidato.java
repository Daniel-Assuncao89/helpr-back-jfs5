package org.soulcodeacademy.helpr.domain;

import org.soulcodeacademy.helpr.domain.enums.Setor;

import javax.persistence.*;

@Entity
public class FuturoCandidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCandidato;

    private String nomeCompleto;

    private String email;

    private String descricaoHabilidades;

    @Enumerated(EnumType.STRING)
    private Setor setor;

    public FuturoCandidato(){}

    public FuturoCandidato(Integer idCandidato, String nomeCompleto, String email, String descricaoHabilidades, Setor setor) {
        this.idCandidato = idCandidato;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.descricaoHabilidades = descricaoHabilidades;
        this.setor = setor;
    }

    public Integer getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricaoHabilidades() {
        return descricaoHabilidades;
    }

    public void setDescricaoHabilidades(String descricaoHabilidades) {
        this.descricaoHabilidades = descricaoHabilidades;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
