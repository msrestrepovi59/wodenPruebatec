package com.pokemon.pokemones.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imagemove;

    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    @JsonBackReference
    private Pokemon pokemon;
}
