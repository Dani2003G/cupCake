package br.com.projeto.cupCake.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AlterarDadosDTO {

    @NotBlank
    @Length(min = 1, max = 30)
    private String nome;

    @NotBlank
    @Length(min = 1, max = 30)
    private String sobrenome;

    @NotBlank
    @Email
    @Length(max = 255)
    private String email;

    @NotBlank
    @Length(min = 11, max = 11)
    private String cpf;

    @NotBlank
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
    private String dataNascimento;

    @NotBlank
    @Length(min = 2, max = 2)
    private String estado;

    @NotBlank
    @Length(min = 1, max = 255)
    private String cidade;

    @NotBlank
    @Length(min = 1, max = 255)
    private String endereco;

}
