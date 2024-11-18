package br.com.projeto.cupCake.model;

import br.com.projeto.cupCake.enums.Tipo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cupCakes")
    private List<Pedidos> pedidos;
}
