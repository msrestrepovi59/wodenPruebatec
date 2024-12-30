package com.pokemon.pokemones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemon.pokemones.models.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
