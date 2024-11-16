package br.com.projeto.cupCake.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private String nome;

    private String sobrenome;

    private String email;

    private String cpf;

    private String senha;

    private String dataNascimento;

    private String estado;

    private String cidade;

    private String endereco;

}
