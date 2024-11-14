package br.com.projeto.cupCake.repository;

import br.com.projeto.cupCake.model.HistoricoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoPedidoRepository extends JpaRepository<HistoricoPedido, Long> {
}
