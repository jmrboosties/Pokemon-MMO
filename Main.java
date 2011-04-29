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
    	PokemonSpecies mander = PokemonSpeciesFactory.getPokemonSpecies(4);
    	PokemonSpecies squirtle = PokemonSpeciesFactory.getPokemonSpecies(7);
    	System.out.println(mander.getSpeciesName() + " vs. " + squirtle.getSpeciesName() + "!");
    	Pokemon myPokemon = PokemonFactory.getPokemon(mander);
    	System.out.println(mander.getSpeciesName() + "'s" + " nickname is " + myPokemon.getNickName() + "!");
		System.out.println(myPokemon.getNickName() + " is level " + String.valueOf(myPokemon.getLevel()) + ".");
		System.out.println(myPokemon.getNickName() + " uses Ember!");
		System.out.println("Charmander's special attack is " + myPokemon.getSpAttack());
		
		
		BattleStats battle = new BattleStats();
		GameFields battleMath = new GameFields();
		Pokemon wildPokemon = PokemonFactory.getPokemon(squirtle);
		battleMath.setAttackerType1(myPokemon.getType1());
		battleMath.setAttackerType2(myPokemon.getType2());
		battleMath.setDefenderType1(wildPokemon.getType1());
		battleMath.setDefenderType2(wildPokemon.getType2());
		
		System.out.println("Squirtle's special defense is " + wildPokemon.getSpDefense());
		
		int damage = battleMath.damageCalc(myPokemon, wildPokemon, MoveFactory.getMove(Moves.EMBER), battle);
		
		System.out.println("Squirtle takes " + String.valueOf(damage) + "!");
		
		int currentHP = (int) (wildPokemon.getCurrentHP() - damage);
		
		System.out.println("Squirtle's HP goes from " + String.valueOf(wildPokemon.getCurrentHP()) + " to " + currentHP);
    	
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
