package br.com.projeto.cupCake.service;

import br.com.projeto.cupCake.dto.UsuarioDTO;
import br.com.projeto.cupCake.model.Usuario;
import br.com.projeto.cupCake.repository.UsuarioRepositoy;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService{

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final UsuarioRepositoy usuarioRepositoy;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositoy.findByEmail(email);

        if (Objects.nonNull(usuario)) {
            return User.withUsername(usuario.getEmail())
                    .password(usuario.getSenha())
                    .roles(usuario.getRole())
                    .build();
        }

        return null;
    }

    public void salvar(UsuarioDTO dto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setSobrenome(dto.getSobrenome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setCpf(dto.getCpf());
        usuario.setDataNascimento(LocalDate.parse(dto.getDataNascimento(), formatter));
        usuario.setEstado(dto.getEstado());
        usuario.setCidade(dto.getCidade());
        usuario.setEndereco(dto.getEndereco());
        usuario.setRole("USER");
        usuario.setDataCadastro(LocalDateTime.now());
        usuarioRepositoy.save(usuario);
    }

    public void deletarUsuario(String email) {
        Usuario usuario = usuarioRepositoy.findByEmail(email);
        usuarioRepositoy.delete(usuario);
    }

    public List<UsuarioDTO> buscarUsuariosComuns() {
        List<Usuario> usuarios = usuarioRepositoy.buscarUsuariosComuns();
        return usuarios.stream().map(new UsuarioDTO()::toDTO).toList();
    }

    public void validarDelecaoUsuario(Long id) {
        Usuario usuario = usuarioRepositoy.findById(id).get();
        if(usuario.getRole().equals("USER")) {
            usuarioRepositoy.delete(usuario);
        }
    }

    public List<UsuarioDTO> buscarUsuariosAdministradores() {
        List<Usuario> usuarios = usuarioRepositoy.buscarUsuariosAdministradores();
        return usuarios.stream().map(new UsuarioDTO()::toDTO).toList();
    }

    public void validarDelecaoAdm(Long id, String email) {
        List<Usuario> usuarios = usuarioRepositoy.buscarUsuariosAdministradores();
        if(usuarios.size() == 1) {
            throw new ServiceException("Tem que ter ao menos 1 administrador cadastrado");
        }
        Usuario usuario1 = usuarioRepositoy.findById(id).get();
        Usuario usuario2 = usuarioRepositoy.findByEmail(email);
        if(usuario1.getId().equals(usuario2.getId())) {
            throw new ServiceException("Administrador a ser deletado é o logado");
        }
        usuarioRepositoy.delete(usuario1);
    }

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepositoy.findById(id).get();
        return new UsuarioDTO().toDTO(usuario);
    }
}
