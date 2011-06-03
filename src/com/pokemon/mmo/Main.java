package com.pokemon.mmo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.pokemon.mmo.Enums.Ability;

public class Main {

	public static PokemonSpecies[] mSpeciesArray;
	public static Move[] mMoveArray;

	public static void main(String[] args) {
		mSpeciesArray = PokemonSpeciesFactory.createSpeciesArray();
		mMoveArray = MoveFactory.createMoveArray();

		while(true) {
			System.out.println("Welcome to the Pokedex. Enter the dex number of who you want to check.");
			String input = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				input = reader.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int dexNum = Integer.parseInt(input);
			if(!(0<dexNum) || !(dexNum<650)) {
				System.out.println("Try again, don't fuck around. Pokedex goes to 650.");
			}
			else {
				dexCheck(dexNum);
			}
		}
	}
	
	private static void dexCheck(int dexNum) {
		PokemonSpecies species = mSpeciesArray[dexNum];
		System.out.println(species.getSpeciesName() + " is a " + species.getType(1).getName() + " and " + species.getType(2).getName() + " pokemon.");
		String abilityMsg = species.getSpeciesName() + " has the following abilities: ";
		for (Ability ability : species.getAbilityArray()) {
			if(ability != Ability.NONE) {
				abilityMsg +=  ability.getName() + ", ";
			}
		}
		if(species.getDreamAbility() != Ability.NONE) {
			abilityMsg += species.getDreamAbility().getName();
		}
		else {
			abilityMsg += "with no dream ability.";
		}
		System.out.println(abilityMsg);
		System.out.println(species.getSpeciesName() + " is male " + species.getGenderRatio() + "% of the time.");
		
		Pokemon pokemon = PokemonFactory.getPokemon(species);
		System.out.println("A wild " + species.getSpeciesName() + " appeared!");
		System.out.println(pokemon.getNickName() + " has ability " + pokemon.getAbility().getName());
		System.out.println(pokemon.getNickName() + " is " + pokemon.getGender().getName());
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * System.out.println("IT'S ELF'S WORLD!!");
	 * System.out.println("Trainer, what is your name?");
	 * 
	 * Trainer trainer = new Trainer();
	 * 
	 * String input = ""; BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(System.in));
	 * 
	 * try { input = reader.readLine(); trainer.setName(input); }
	 * catch(IOException ioe) { System.out.println("Exception."); }
	 * 
	 * System.out.println(trainer.getName() +
	 * ", here is a Charmander. Give your Charmander a nickname?");
	 * 
	 * PokemonSpecies mander = PokemonSpeciesFactory.getPokemonSpecies(4);
	 * Pokemon myPokemon = PokemonFactory.getCharmander(mander);
	 * 
	 * try { input = reader.readLine(); myPokemon.setNickName(input); }
	 * catch(IOException e) { System.out.println("Failure."); }
	 * 
	 * System.out.println("From now on this little guy is named " +
	 * myPokemon.getNickName() + "!");
	 * 
	 * trainer.setPokemon1(myPokemon);
	 * 
	 * System.out.println(
	 * "Oh no! Here comes a wild one! This is a demo so just let your pokemon do the work."
	 * );
	 * 
	 * WildBattle battle = new WildBattle(trainer); boolean result =
	 * battle.wildBattleThread(); if(result) {
	 * System.out.println("Congrats on the win!"); } else {
	 * System.out.println("You suck."); }
	 * 
	 * }
	 */

}
