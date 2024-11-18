package br.com.projeto.cupCake.service;

import br.com.projeto.cupCake.dto.UsuarioDTO;
import br.com.projeto.cupCake.model.Usuario;
import br.com.projeto.cupCake.repository.UsuarioRepositoy;
import lombok.RequiredArgsConstructor;
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
}
