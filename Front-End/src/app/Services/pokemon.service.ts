import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PokemonService {
  private apiUrl = 'http://localhost:8080/pokemon'; 

  constructor(private http: HttpClient) {}

  getAllPokemons(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
  getPokemonAbilities(pokemonId: number): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8080/pokemon/${pokemonId}/abilities`);
  }
  
}