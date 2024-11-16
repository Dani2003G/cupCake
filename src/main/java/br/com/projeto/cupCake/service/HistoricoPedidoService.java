package br.com.projeto.cupCake.service;

import br.com.projeto.cupCake.dto.HistoricoPedidoDTO;
import br.com.projeto.cupCake.model.HistoricoPedido;
import br.com.projeto.cupCake.repository.HistoricoPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoricoPedidoService {

    private final HistoricoPedidoRepository historicoPedidoRepository;

    public List<HistoricoPedidoDTO> buscarTudoPorUsuario(String email) {
        List<HistoricoPedido> historicoPedidos = historicoPedidoRepository.buscarPorUsuario(email);
        return historicoPedidos.stream().map(new HistoricoPedidoDTO()::toDTO).toList();
    }
}
