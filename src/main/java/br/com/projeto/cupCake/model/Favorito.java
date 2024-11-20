package br.com.projeto.cupCake.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Favorito {

    @EmbeddedId
    private FavoritoId id;
}
