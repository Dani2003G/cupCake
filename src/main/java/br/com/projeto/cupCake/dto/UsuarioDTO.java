package br.com.projeto.cupCake.dto;

import br.com.projeto.cupCake.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class UsuarioDTO {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Long id;

    private String nome;

    private String sobrenome;

    private String email;

    private String cpf;

    private String senha;

    private String dataNascimento;

    private String estado;

    private String cidade;

    private String endereco;

    private String dataCadastro;

    public UsuarioDTO toDTO(Usuario entidade) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(entidade.getId());
        usuarioDTO.setNome(entidade.getNome());
        usuarioDTO.setSobrenome(entidade.getSobrenome());
        usuarioDTO.setEmail(entidade.getEmail());
        usuarioDTO.setCpf(entidade.getCpf());
        usuarioDTO.setDataNascimento(entidade.getDataNascimento().format(formatter));
        usuarioDTO.setEstado(entidade.getEstado());
        usuarioDTO.setCidade(entidade.getCidade());
        usuarioDTO.setEndereco(entidade.getEndereco());
        usuarioDTO.setDataCadastro(entidade.getDataCadastro().format(formatter));
        return usuarioDTO;
    }

}
