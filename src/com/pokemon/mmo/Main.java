package com.pokemon.mmo;

import java.util.Random;

import com.pokemon.mmo.Enums.Ability;

public class Main {

	public static PokemonSpecies[] mSpeciesArray;
	public static Move[] mMoveArray;

	public static void main(String[] args) {
		mSpeciesArray = PokemonSpeciesFactory.createSpeciesArray();
		mMoveArray = MoveFactory.createMoveArray();

		Random generator = new Random();
		int val = generator.nextInt(669) + 1;
		PokemonSpecies species = mSpeciesArray[val];
		System.out.println(species.getSpeciesName() + " is a " + species.getType(1).getName() + " and " + species.getType(2).getName() + " pokemon.");
		String abilityMsg = species.getSpeciesName() + " has the following abilities: ";
		for (Ability ability : species.getAbilityArray()) {
			if(ability != Ability.NONE) {
				abilityMsg +=  ", " + ability.getName();
			}
		}
		System.out.println(abilityMsg);
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
