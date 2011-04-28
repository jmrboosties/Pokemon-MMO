/**
 * Generates a species of Pokemon, only base stats and other information relevant to all instances of a species.
 */

package com.pokemon.mmo;

public class PokemonSpeciesFactory {

	public static PokemonSpecies getPokemonSpecies(int dexNum) {
        if (dexNum < 0 || dexNum > 649){
            return new PokemonSpecies();
        }
        switch (dexNum){
            case 1:
                return new Bulbasaur();
            case 4:
                return new Charmander();
            case 7:
                return new Squirtle();
            default:
                break;
        }
        return new PokemonSpecies();
    }
	
}
