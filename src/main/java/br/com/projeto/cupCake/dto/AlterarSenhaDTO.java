package br.com.projeto.cupCake.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AlterarSenhaDTO {

    @NotBlank
    private String senha;

    @NotBlank
    @Length(min = 6, max = 30)
    private String novaSenha;

    @NotBlank
    @Length(min = 6, max = 30)
    private String confirmarSenha;

}
