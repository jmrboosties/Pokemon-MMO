/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pokemon.mmo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author robosllim
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	PokemonSpecies mander = PokemonSpeciesFactory.getPokemonSpecies(4);
    	PokemonSpecies squirtle = PokemonSpeciesFactory.getPokemonSpecies(7);
    	System.out.println(mander.getSpeciesName() + " vs. " + squirtle.getSpeciesName() + "!");
    	Pokemon myPokemon = PokemonFactory.getPokemon(mander);
    	System.out.println(mander.getSpeciesName() + "'s" + " nickname is " + myPokemon.getNickName() + "!");
		System.out.println(myPokemon.getNickName() + " is level " + String.valueOf(myPokemon.getLevel()) + ".");
    	
    	String input = "";
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Happy?");
    	
    	try {
    		input = reader.readLine();
    		System.out.println(input);
    	}
    	catch(IOException ioe) {
    		System.out.println("Exception.");
    	}
    	
    	
    	
    }

}
