package br.com.projeto.cupCake.repository;

import br.com.projeto.cupCake.model.HistoricoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoPedidoRepository extends JpaRepository<HistoricoPedido, Long> {

    @Query("SELECT h FROM HistoricoPedido h JOIN h.usuario u WHERE  u.email = :email")
    List<HistoricoPedido> buscarPorUsuario(@Param("email") String email);
}
