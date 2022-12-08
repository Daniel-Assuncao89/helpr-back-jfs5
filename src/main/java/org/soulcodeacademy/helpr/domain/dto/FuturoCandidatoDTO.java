package org.soulcodeacademy.helpr.domain.dto;

import org.soulcodeacademy.helpr.domain.enums.Setor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FuturoCandidatoDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nomeCompleto;

    @NotBlank(message = "Email é obrigatório")
    @Email
    private String email;

    @NotBlank(message = "Descrição de habilidades é obrigatória")
    @Size(max = 120)
    private String descricaoHabilidades;

    @NotBlank(message = "Setor é obrigatório")
    private Setor setor;

}
