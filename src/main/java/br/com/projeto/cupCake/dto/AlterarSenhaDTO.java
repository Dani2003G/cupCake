package br.com.projeto.cupCake.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlterarSenhaDTO {

    @NotBlank
    private String senha;

    @NotBlank
    private String novaSenha;

    @NotBlank
    private String confirmarSenha;

}
