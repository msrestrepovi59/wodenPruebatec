import { Component, OnInit } from '@angular/core';
import { PokemonService } from '../../Services/pokemon.service';

@Component({
  selector: 'app-pokemon',
  templateUrl: './pokemon.component.html',
  styleUrls: ['./pokemon.component.css'],
})
export class PokemonComponent implements OnInit {
  pokemons: any[] = [];
  filteredPokemons: any[] = [];
  selectedPokemon: any = null;
  selectedPokemonAbilities: any[] = [];
  isModalOpen = false;
  searchTerm = '';

  constructor(private pokemonService: PokemonService) {}

  ngOnInit(): void {
    this.fetchPokemons();
  }

  fetchPokemons(): void {
    this.pokemonService.getAllPokemons().subscribe((data) => {
      this.pokemons = data;
      this.filteredPokemons = data;
    });
  }

  filterPokemons(): void {
    this.filteredPokemons = this.pokemons.filter((pokemon) =>
      pokemon.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  openModal(pokemon: any): void {
    this.selectedPokemon = pokemon;

  
  this.pokemonService.getPokemonAbilities(pokemon.id).subscribe((abilities: any) => {
    this.selectedPokemonAbilities = abilities.map((ability: any) => ({
      name: ability.name, 
      image: ability.imagemove,
    }));
    this.isModalOpen = true; 
  }, error => {
    console.error('Error al obtener las habilidades del Pokémon:', error);
  });
  }

  closeModal(): void {
    this.isModalOpen = false;
  }
}
