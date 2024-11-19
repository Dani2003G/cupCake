package br.com.projeto.cupCake.repository;

import br.com.projeto.cupCake.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    @Query("SELECT car FROM Carrinho car WHERE car.idUsuario = :idUsuario AND car.idCupCake = :idCupCake")
    List<Carrinho> buscarPorCupCakeEUsuario(@Param("idUsuario") Long idUsuario, @Param("idCupCake") Long idCupCake);
}
