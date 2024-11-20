package br.com.projeto.cupCake.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class CupCake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private Integer tempoPreparo;

    private String igredientes;

    private String modoPreparo;

    private String urlImagem;

    private BigDecimal preco;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
}
