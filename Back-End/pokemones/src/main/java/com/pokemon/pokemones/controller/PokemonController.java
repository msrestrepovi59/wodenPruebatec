package com.pokemon.pokemones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.pokemones.models.Habilidad;
import com.pokemon.pokemones.models.Pokemon;
import com.pokemon.pokemones.repository.PokemonRepository;
import com.pokemon.pokemones.service.PokemonService;

import java.util.List;

@RestController
public class PokemonController {
    private final PokemonService pokemonService;

    @Autowired
    private PokemonRepository pokemonRepository; 
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/savepokemon")
    public String fetchPokemon() {
        pokemonService.SavePokemon();
        return "el listado de pokemones ha sido guardado en la base de datos";
    }
    
    @CrossOrigin
    @GetMapping("/pokemon/{id}/abilities")
    public List<Habilidad> getPokemonAbilities(@PathVariable Long id) throws RuntimeException {

        Pokemon pokemon = pokemonRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pokemon no encontrado"));

        
        return pokemon.getHabilidades();
    }
    
    @CrossOrigin
    @GetMapping("/pokemon")
    public List<Pokemon> getAllPokemon() {
        return pokemonService.getAllPokemon();
    }
}

