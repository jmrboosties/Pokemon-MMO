/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pokemon.mmo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.pokemon.mmo.Enums.Moves;

/**
 *
 * @author robosllim
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	System.out.println("IT'S ELF'S WORLD!!");
    	System.out.println("Trainer, what is your name?");
    	
    	Trainer trainer = new Trainer();
    	
    	String input = "";
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	
    	try {
    		input = reader.readLine();
    		trainer.setName(input);
    	}
    	catch(IOException ioe) {
    		System.out.println("Exception.");
    	}
    	
    	System.out.println(trainer.getName() + ", here is a Charmander. Give your Charmander a nickname?");
    	PokemonSpecies mander = PokemonSpeciesFactory.getPokemonSpecies(4);
    	Pokemon myPokemon = PokemonFactory.getPokemon(mander);
    	
    	try {
    		input = reader.readLine();
    		myPokemon.setNickName(input);    		
    	} catch(IOException e) {
    		System.out.println("Failure.");
    	}
    	
    	System.out.println("From now on this little guy is named " + myPokemon.getNickName() + "!");
    	
    	trainer.setPokemon1(myPokemon);
    	
    	System.out.println("Oh no! Here comes a wild one! This is a demo so just let your pokemon do the work.");
    	
    	BattleStats battle = new BattleStats(trainer);
    	
    }

}
