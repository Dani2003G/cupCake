package br.com.projeto.cupCake.repository;

import br.com.projeto.cupCake.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositoy extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    @Query("SELECT us FROM Usuario us WHERE us.role = 'USER'")
    List<Usuario> buscarUsuariosComuns();

    @Query("SELECT us FROM Usuario us WHERE us.role = 'ADMIN'")
    List<Usuario> buscarUsuariosAdministradores();

    Usuario findByCpf(String cpf);
}
