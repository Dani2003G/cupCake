package br.com.projeto.cupCake.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Favorito {

    @EmbeddedId
    private FavoritoId id;
}
