package com.pokemon.pokemones.dto;

import java.util.List;

import com.pokemon.pokemones.service.PokemonService;

import lombok.Data;

@Data
public class PokemonDetail {
	
	 
	   
	        private List<AbilityWrapper> abilities;

	        @Data
	        public static class AbilityWrapper {
	            private Ability ability;

	            @Data
	            public static class Ability {
	                private String name;
	            }
	        }
	    }



