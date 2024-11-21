package br.com.projeto.cupCake.service;

import br.com.projeto.cupCake.dto.AlterarSenhaDTO;
import br.com.projeto.cupCake.dto.CadastroUsuarioDTO;
import br.com.projeto.cupCake.dto.UsuarioDTO;
import br.com.projeto.cupCake.model.Usuario;
import br.com.projeto.cupCake.repository.UsuarioRepositoy;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

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

    public void salvar(CadastroUsuarioDTO dto) {
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
        if (usuario.getRole().equals("USER")) {
            usuarioRepositoy.delete(usuario);
        }
    }

    public List<UsuarioDTO> buscarUsuariosAdministradores() {
        List<Usuario> usuarios = usuarioRepositoy.buscarUsuariosAdministradores();
        return usuarios.stream().map(new UsuarioDTO()::toDTO).toList();
    }

    public void validarDelecaoAdm(Long id, String email) {
        List<Usuario> usuarios = usuarioRepositoy.buscarUsuariosAdministradores();
        if (usuarios.size() == 1) {
            throw new ServiceException("Tem que ter ao menos 1 administrador cadastrado");
        }
        Usuario usuario1 = usuarioRepositoy.findById(id).get();
        Usuario usuario2 = usuarioRepositoy.findByEmail(email);
        if (usuario1.getId().equals(usuario2.getId())) {
            throw new ServiceException("Administrador a ser deletado é o logado");
        }
        usuarioRepositoy.delete(usuario1);
    }

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepositoy.findById(id).get();
        return new UsuarioDTO().toDTO(usuario);
    }

    public UsuarioDTO buscarPorEmail(String email) {
        Usuario usuario = usuarioRepositoy.findByEmail(email);
        return new UsuarioDTO().toDTO(usuario);
    }

    public void alterarDados(UsuarioDTO dto, String email) {
        if (!dto.getEmail().equals(email)) {
            if (Objects.nonNull(usuarioRepositoy.findByEmail(dto.getEmail()))) {
                throw new ServiceException("E-mail já está sendo usado");
            }
        }
        Usuario usuarioLogado = usuarioRepositoy.findByEmail(email);
        if (!dto.getCpf().equals(usuarioLogado.getCpf())) {
            if (Objects.nonNull(usuarioRepositoy.findByCpf(dto.getCpf()))) {
                throw new ServiceException("CPF já está sendo usado");
            }
        }
        usuarioLogado.setNome(dto.getNome());
        usuarioLogado.setSobrenome(dto.getSobrenome());
        usuarioLogado.setEmail(dto.getEmail());
        usuarioLogado.setCpf(dto.getCpf());
        usuarioLogado.setDataNascimento(LocalDate.parse(dto.getDataNascimento(), formatter));
        usuarioLogado.setEstado(dto.getEstado());
        usuarioLogado.setCidade(dto.getCidade());
        usuarioLogado.setEndereco(dto.getEndereco());
        usuarioRepositoy.save(usuarioLogado);
        atualizarContextoDeSeguranca(usuarioLogado.getEmail());
    }

    private void atualizarContextoDeSeguranca(String email) {
        Authentication atual = SecurityContextHolder.getContext().getAuthentication();

        Authentication novaAutenticacao = new UsernamePasswordAuthenticationToken(
                email,
                atual.getCredentials(),
                atual.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(novaAutenticacao);
    }

    public void alterarSenha(@Valid AlterarSenhaDTO dto, String email, BindingResult result) {
        Usuario usuarioLogado = usuarioRepositoy.findByEmail(email);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(dto.getSenha(), usuarioLogado.getSenha()) && !result.hasFieldErrors("senha")) {
            result.addError(new FieldError("dto", "senha", "Senha atual incorreta"));
        }
        if (!dto.getNovaSenha().equals(dto.getConfirmarSenha()) && !result.hasFieldErrors("confirmarSenha")) {
            result.addError(new FieldError("dto", "confirmarSenha", "As novas senhas não conferem"));
        }
        if(result.hasErrors()) {
            return;
        }
        usuarioLogado.setSenha(encoder.encode(dto.getNovaSenha()));
        usuarioRepositoy.save(usuarioLogado);
    }

    public void validarCadastro(@Valid CadastroUsuarioDTO dto, BindingResult result) {
        if(dto.getSenha().length() < 6 && !result.hasFieldErrors("senha")) {
            result.addError(new FieldError("dto", "senha", "Senha tem que ter no minimo 6 caracteres"));
        }

        if (!dto.getSenha().equals(dto.getConfirmarSenha()) && !result.hasFieldErrors("confirmarSenha")) {
            result.addError(new FieldError("dto", "confirmarSenha", "Senha e confirmar senha não conferem"));
        }

        if (Objects.nonNull(usuarioRepositoy.findByEmail(dto.getEmail())) && !result.hasFieldErrors("email")) {
            result.addError(new FieldError("dto", "email", "E-mail já está sendo usado"));
        }

        if (Objects.nonNull(usuarioRepositoy.findByCpf(dto.getCpf())) && !result.hasFieldErrors("cpf")) {
            result.addError(new FieldError("dto", "cpf", "CPF já está sendo usado"));
        }
    }
}
