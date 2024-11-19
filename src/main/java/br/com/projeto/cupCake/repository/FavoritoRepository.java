package br.com.projeto.cupCake.repository;

import br.com.projeto.cupCake.model.Favorito;
import br.com.projeto.cupCake.model.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {
    @Query("SELECT f FROM Favorito f where f.id.idUsuario = :idUsuario")
    List<Favorito> buscarPorUsuario(@Param("idUsuario") Long id);
}
