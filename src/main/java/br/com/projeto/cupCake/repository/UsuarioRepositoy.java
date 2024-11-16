package br.com.projeto.cupCake.repository;

import br.com.projeto.cupCake.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositoy extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

}
