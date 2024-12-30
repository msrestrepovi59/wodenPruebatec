package com.pokemon.pokemones.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;
    private String imageUrl; 
    
    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Habilidad> habilidades;
}

