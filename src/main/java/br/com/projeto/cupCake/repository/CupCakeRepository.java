package br.com.projeto.cupCake.repository;

import br.com.projeto.cupCake.model.CupCake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CupCakeRepository extends JpaRepository<CupCake, Long> {

    @Query("SELECT cc FROM CupCake cc JOIN cc.usuario u WHERE  u.email = :email")
    List<CupCake> buscarPorUsuario(@Param("email") String email);

    @Query("SELECT cc FROM CupCake cc WHERE cc.aprovado = FALSE")
    List<CupCake> buscarReceitasNaoAprovadas();

    @Query("SELECT cc FROM CupCake cc WHERE cc.aprovado = TRUE")
    List<CupCake> buscarTodasAprovadas();
}
