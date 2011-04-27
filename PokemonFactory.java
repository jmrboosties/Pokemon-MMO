/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pokemon.mmo;

/**
 *
 * @author robosllim
 */
public class PokemonFactory {
    public PokemonSpecies getPoke(int dexNum){
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
