package com.pokemon.mmo;

import java.util.HashMap;
import java.util.Scanner;

import com.pokemon.mmo.Enums.Ability;

public class Main {

	public static PokemonSpecies[] mSpeciesArray;
	public static Move[] mMoveArray;
	
	public static GUI mGui = null;

	public static void main(String[] args) throws Exception {
		mSpeciesArray = PokemonSpeciesFactory.createSpeciesArray();
		mMoveArray = MoveFactory.createMoveArray();
		
		battleSim();
		//mGui = new GUI();
		//gui.start();
		
	}
	
	private static void battleSim() {
		RandomForPokemon gen = new RandomForPokemon();
		Trainer trainer1 = new Trainer();
//		trainer1.setLeadingPokemon(gen.randomPokemon(20));
		trainer1.setLeadingPokemon(PokemonFactory.getPokemonAtLevel(mSpeciesArray[512],20));
		Trainer trainer2 = new Trainer();
		trainer2.setLeadingPokemon(gen.randomPokemon(20));
		Battle battle = new Battle(trainer1, trainer2);
		battle.battleThread();
		retryQuestion();
	}
	
	public static void retryQuestion() {
		System.out.println("Test again? [Y]/N");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		if(input.equals("Y") || input.equals("y") || input.equals("")) {
			battleSim();
		}
		else {
			System.out.println("End.");
		}
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
		HashMap<Integer, Integer[]> map = species.getLevelMoves();
		for (int i = 1; i <= 100; i++) {
			Integer[] moveIds = map.get(i);
			for(Integer moveId : moveIds){
				if(moveId != null) {
					Move move = mMoveArray[moveId];
					System.out.println(move.getMoveName() + " at level " + i);
				}
			}
		}
	}
}
