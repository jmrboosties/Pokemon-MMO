package com.pokemon.mmo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

import com.pokemon.mmo.Enums.Ability;

public class Main {

	public static PokemonSpecies[] mSpeciesArray;
	public static Move[] mMoveArray;
	
	public static GUI gui = null;

	public static void main(String[] args) throws Exception {
		mSpeciesArray = PokemonSpeciesFactory.createSpeciesArray();
		mMoveArray = MoveFactory.createMoveArray();
		/*for (int i = 1; i < 10; i++) {
			mSpeciesArray[i].printPokemon();
			System.out.println(mSpeciesArray[i].getSpeciesName() + " learns:");
			HashMap map = mSpeciesArray[i].getHashMap(0);
			for (int j = 1; j < 101; j++) {
				Integer moveId = (Integer) map.get(j);
				if(moveId != null) {
					Move move = mMoveArray[moveId];
					System.out.println(move.getMoveName() + " at level " + j);
				}
			}
		}*/
		battleSim();
		//gui = new GUI();
		//gui.start();

//		while(true) {
//			System.out.println("Welcome to the Pokedex. Enter the dex number of who you want to check.");
//			String input = "";
//			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//			try {
//				input = reader.readLine();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			int dexNum = Integer.parseInt(input);
//			if(!(0<dexNum) || !(dexNum<650)) {
//				System.out.println("Try again, don't fuck around. Pokedex goes to 649.");
//			}
//			else {
//				dexCheck(dexNum);
//			}
//		}
	}
	
	private static void battleSim() {
		Trainer trainer1 = new Trainer();
		trainer1.setLeadingPokemon(PokemonFactory.getPokemonAtLevel(mSpeciesArray[4], 15));
		Trainer trainer2 = new Trainer();
		trainer2.setLeadingPokemon(PokemonFactory.getPokemonAtLevel(mSpeciesArray[10], 15));
		Battle battle = new Battle(trainer1, trainer2);
		battle.battleThread();
	}
	
	private static void dexCheck(int dexNum) {
		PokemonSpecies species = mSpeciesArray[dexNum];
		if(!species.isDualType()) {
			System.out.println(species.getSpeciesName() + " is a " + species.getType(1).getName() + " pokemon.");
		}
		else {
			System.out.println(species.getSpeciesName() + " is a " + species.getType(1).getName() + " and " + species.getType(2).getName() + " pokemon.");
		}
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
		
		if(species.getGenderRatio() == -1) {
			System.out.println(species.getSpeciesName() + " is Genderless.");
		}
		else {
			System.out.println(species.getSpeciesName() + " is male " + 100*species.getGenderRatio() + "% of the time.");
		}
		
		System.out.println(species.getSpeciesName() + " learns the following moves:");
		HashMap map = species.getHashMap(0);
		for (int i = 1; i < 101; i++) {
			Integer moveId = (Integer) map.get(i);
			if(moveId != null) {
				Move move = mMoveArray[moveId];
				System.out.println(move.getMoveName() + " at level " + i);
			}
		}

//		Pokemon pokemon = PokemonFactory.getPokemon(species);
//		System.out.println("A wild " + species.getSpeciesName() + " appeared!");
//		System.out.println(pokemon.getNickName() + " has ability " + pokemon.getAbility().getName());
//		System.out.println(pokemon.getNickName() + " is " + pokemon.getGender().getName());
//		Random generator = new Random();
//		int level = generator.nextInt(100) + 1;
//		pokemon.setLevel(30);
//		System.out.println(pokemon.getNickName() + " is level " + pokemon.getLevel());
//		System.out.println(pokemon.getNickName() + " has the following moves:");
//		Move[] moves = pokemon.getMoveArray();
//		for (int i = 0; i < moves.length; i++) {
//			if(moves[i] != mMoveArray[0]) {
//				System.out.println(moves[i].getMoveName());
//			}
//		}
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
