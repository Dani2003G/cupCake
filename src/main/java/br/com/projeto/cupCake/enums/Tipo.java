package br.com.projeto.cupCake.enums;

import lombok.Getter;

@Getter
public enum Tipo {
    RECEITA("RECEITA", "Receita"),
    PRODUTO("RECEITA", "Produto");

    Tipo(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    private String codigo;
    private String descricao;
}
