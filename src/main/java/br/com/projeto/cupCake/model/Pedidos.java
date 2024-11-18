package br.com.projeto.cupCake.model;

import br.com.projeto.cupCake.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany()
    @JoinTable(
            name = "pedido_cupcake",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "cupcake_id")
    )
    private List<CupCake> cupCakes;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private BigDecimal valorTotal;

}
