package br.com.projeto.cupCake.dto;

import br.com.projeto.cupCake.model.HistoricoPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class HistoricoPedidoDTO {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private Long id;

    private Long pedidoId;

    private String nome;

    private String data;

    private BigDecimal valor;

    private String urlImagem;

    public HistoricoPedidoDTO toDTO(HistoricoPedido entidade) {
        HistoricoPedidoDTO dto = new HistoricoPedidoDTO();
        dto.setId(entidade.getId());
        dto.setPedidoId(entidade.getPedidoId());
        dto.setNome(entidade.getNome());
        dto.setData(entidade.getData().format(formatter));
        dto.setValor(entidade.getValor());
        dto.setUrlImagem(entidade.getUrlImagem());
        return dto;
    }
}
