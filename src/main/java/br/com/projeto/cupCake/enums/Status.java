package br.com.projeto.cupCake.enums;

import lombok.Getter;

@Getter
public enum Status {
    PEDIDO_REALIZADO(1, "Pedido Realizado"),
    PAGAMENTO_REALIZADO(2, "Pagamento Realizado"),
    PEDIDO_EM_PRODUCAO(3, "Pedido em Produção"),
    SAIU_PARA_ENTREGA(4, "Saiu para Entrega"),
    FINALIZADO(5, "Finalizado");

    Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    private Integer codigo;
    private String descricao;

}
