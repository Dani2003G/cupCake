package br.com.projeto.cupCake.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    @Column(unique = true, nullable = false)
    private String email;

    private String cpf;

    private String senha;

    private LocalDate dataNascimento;

    private String estado;

    private String cidade;

    private String endereco;

    private String role;

    private LocalDateTime dataCadastro;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<CupCake> cupCakes;

}
