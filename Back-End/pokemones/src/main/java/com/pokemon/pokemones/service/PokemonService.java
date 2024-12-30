package com.pokemon.pokemones.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pokemon.pokemones.dto.PokemonApiResponse;
import com.pokemon.pokemones.dto.PokemonDTO;
import com.pokemon.pokemones.dto.PokemonDetail;
import com.pokemon.pokemones.models.Habilidad;
import com.pokemon.pokemones.models.Pokemon;
import com.pokemon.pokemones.repository.PokemonRepository;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class PokemonService {
    private final RestTemplate restTemplate;
    private final PokemonRepository pokemonRepository;

    public PokemonService(RestTemplate restTemplate, PokemonRepository pokemonRepository) {
        this.restTemplate = restTemplate;
        this.pokemonRepository = pokemonRepository;
    }

    public void SavePokemon() {
    	 String apiUrl = "https://pokeapi.co/api/v2/pokemon?limit=20";
    	    PokemonApiResponse response = restTemplate.getForObject(apiUrl, PokemonApiResponse.class);

    	    if (response != null && response.getResults() != null) {
    	    	 AtomicInteger index = new AtomicInteger(1); // Para contar los Pokémon
    	        response.getResults().forEach(dto -> {
    	            Pokemon pokemon = new Pokemon();
    	            pokemon.setName(dto.getName());
    	            pokemon.setUrl(dto.getUrl());
    	            
    	            String detailUrl = "https://pokeapi.co/api/v2/pokemon/" + index;
    	            PokemonDetail detail = restTemplate.getForObject(detailUrl, PokemonDetail.class);

    	            if (detail != null && detail.getAbilities() != null) {
    	                List<Habilidad> habilidades = detail.getAbilities().stream()
    	                        .map(abilityWrapper -> {
    	                        	
    	                            Habilidad habilidad = new Habilidad();
    	                            habilidad.setName(abilityWrapper.getAbility().getName());
    	                            habilidad.setPokemon(pokemon);
    	                            String imagemoveUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/"+ index+ ".gif";
    	                            habilidad.setImagemove(imagemoveUrl);
    	                            
    	                            return habilidad;
    	                        }).collect(Collectors.toList());

    	                pokemon.setHabilidades(habilidades);
    	                
    	            }
    	            
    	            String formattedIndex = String.format("%03d", index.getAndIncrement());
    	            String imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/" + formattedIndex + ".png";
    	            pokemon.setImageUrl(imageUrl);
    	            

    	           

    	            pokemonRepository.save(pokemon);
    	        });
    	    }
    	}

    

    public List<Pokemon> getAllPokemon() {
        return pokemonRepository.findAll();
    }
   }



