package br.com.projeto.cupCake.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class CadastroUsuarioDTO {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String cpf;

    @NotBlank
    private String senha;

    @NotBlank
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
    private String dataNascimento;

    @NotBlank
    private String estado;

    @NotBlank
    private String cidade;

    @NotBlank
    private String endereco;

    private String dataCadastro;

    @NotBlank
    private String confirmarSenha;

}
