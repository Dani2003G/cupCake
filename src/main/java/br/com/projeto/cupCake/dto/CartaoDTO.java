package br.com.projeto.cupCake.dto;

import br.com.projeto.cupCake.model.Cartao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaoDTO {

    private Long numeroCartao;

    private String nomeCartao;

    private Integer mes;

    private Integer ano;

    private Integer codigoSeguranca;

    public Cartao toEntity() {
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao(this.numeroCartao);
        cartao.setNomeCartao(this.nomeCartao);
        cartao.setMes(this.mes);
        cartao.setAno(this.ano);
        return cartao;
    }
}
