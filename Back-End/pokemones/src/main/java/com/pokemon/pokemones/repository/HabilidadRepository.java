package com.pokemon.pokemones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemon.pokemones.models.Habilidad;

public interface HabilidadRepository extends JpaRepository<Habilidad, Long> {
}
