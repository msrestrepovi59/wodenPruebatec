package com.pokemon.pokemones.dto;

import lombok.Data;

import java.util.List;

@Data
public class PokemonApiResponse {
    private List<PokemonDTO> results;
}

