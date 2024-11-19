package br.com.projeto.cupCake.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Carrinho {

    @Id
    private Long id;

    @OneToMany(mappedBy = "carrinho")
    private List<CupCake> cupCake;

}
