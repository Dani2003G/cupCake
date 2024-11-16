package br.com.projeto.cupCake.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class HistoricoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    private String nome;

    private LocalDateTime data;

    private BigDecimal valor;

    private String urlImagem;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

}
