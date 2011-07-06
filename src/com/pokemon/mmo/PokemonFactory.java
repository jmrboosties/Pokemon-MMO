/**
 * Generates actual pokemon, taking base stats and applying level, IVs, and EVs to get result. 
 */

package com.pokemon.mmo;

import java.util.HashMap;
import java.util.Random;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.Gender;
import com.pokemon.mmo.Enums.Nature;
import com.pokemon.mmo.Enums.Stats;

public class PokemonFactory {

	public static Pokemon getPokemon(PokemonSpecies species) {

		Pokemon pokemon = new Pokemon(species);

		pokemon.setLevel(1);
		pokemon.setGender(determineGender(species));
		pokemon.setAbility(determineAbility(species));		
		
		pokemon.setStat(Stats.HP, setHPStat(pokemon, species));
		for (Stats stat : Stats.values()) {
			pokemon.setStat(stat, setStat(pokemon, species, stat));
		}
		pokemon.setCurrentHP(pokemon.getStat(Stats.HP));
		
		return pokemon;
	}
	
	public static Pokemon getPokemonAtLevel(PokemonSpecies species, int level) {
		
		Pokemon pokemon = new Pokemon(species);

		pokemon.setLevel(level);
		pokemon.setGender(determineGender(species));
		pokemon.setAbility(determineAbility(species));		
		
		pokemon.setStat(Stats.HP, setHPStat(pokemon, species));
		for (Stats stat : Stats.values()) {
			pokemon.setStat(stat, setStat(pokemon, species, stat));
		}
		pokemon.setCurrentHP(pokemon.getStat(Stats.HP));
		
		return pokemon;
	}
	
	public static Pokemon setPokemonStats(PokemonSpecies species, int level, Gender gender, Ability ability) {

		Pokemon pokemon = new Pokemon(species);

		pokemon.setLevel(level);
		pokemon.setGender(gender);
		pokemon.setAbility(ability);

		pokemon.setStat(Stats.HP, setHPStat(pokemon, species));
		
		for (Stats stat : Stats.values()) {
			pokemon.setStat(stat, setStat(pokemon, species, stat));
		}
		pokemon.setCurrentHP(pokemon.getStat(Stats.HP));
		determineMoves(pokemon);

		return pokemon;
	}

	private static int setHPStat(Pokemon pokemon, PokemonSpecies species) {
		int maxHP = 1;

		/**
		 * HP = (((IV + (2 * base) + (EV/4) + 100) * Level)/100) + 10
		 */
		
		maxHP = (((pokemon.getIV(Stats.HP) + (2 * species.getSpecificStat(Stats.HP)) + (pokemon.getEV(Stats.HP)/4) + 100) * pokemon.getLevel()) / 100) + 10;

		return maxHP;
	}

	private static int setStat(Pokemon pokemon, PokemonSpecies species, Stats stat) {
		int totalStat = 1;
		double iv = 0;
		double ev = 0;
		int base = 0;

		switch (stat) {
		case HP:
			return setHPStat(pokemon, species);
		default :
			iv = pokemon.getIV(stat);
			ev = pokemon.getEV(stat);
			base = species.getSpecificStat(stat);
			totalStat = (int) ((int) ((((iv + (2 * base) + (ev / 4)) * pokemon.getLevel()) / 100) + 5) * getNatureMod(pokemon.getNature(), stat));
			return totalStat;
		}
	}
	
	private static double getNatureMod(Nature nature, Stats stat) {
		double mod = 1;
		
		if(nature.getDecreased() == stat) {
			mod = mod - 0.1;
		}
		if(nature.getIncreased() == stat) {
			mod = mod + 0.1;
		}
		
		return mod;
	}
	
	public static void respecStats(Pokemon pokemon, PokemonSpecies species) {
		pokemon.setStat(Stats.HP, setHPStat(pokemon, species));
		for (Stats stat : Stats.values()) {
			pokemon.setStat(stat, setStat(pokemon, species, stat));
		}
		pokemon.setCurrentHP(pokemon.getStat(Stats.HP));
		determineMoves(pokemon);
	}
	
	private static Ability determineAbility(PokemonSpecies species) {
		Random generator = new Random();
		int numOfAbilities = 0;
		Ability ability = Ability.NONE;
		Ability[] abilities = species.getAbilityArray();
		for (int i = 0; i < abilities.length; i++) {
			if(abilities[i] != Ability.NONE) {
				numOfAbilities++;
			}
		}
		
		boolean hasDream = species.hasDreamAbility();
		if(!hasDream) {
			int odds = 100 / numOfAbilities;
			int r = generator.nextInt(100) + 1;
			if(r <= odds) {
				ability = abilities[0];
			}
			else {
				ability = abilities[1];
			}
		}
		else {
			int odds = 90 / numOfAbilities;
			int r = generator.nextInt(100) + 1;
			if(r <= 10) {
				ability = species.getDreamAbility();
			}
			else if(r <= odds + 10) {
				ability = abilities[0];
			}
			else {
				ability = abilities[1];
			}
		}
		System.out.println(species.getSpeciesName() + " has ability " + ability.getName());
		return ability;
	}
	
	private static Gender determineGender(PokemonSpecies species) {
		double ratio = species.getGenderRatio();
		if(ratio == -1) {
			return Gender.GENDERLESS;
		}
		else {
			Random generator = new Random();
			double r = generator.nextInt(100) + 1;
			if(r <= ratio) {
				return Gender.MALE;
			}
			else {
				return Gender.FEMALE;
			}
		}
	}
	
	private static void determineMoves(Pokemon pokemon) {
		HashMap<Integer, Integer[]> map = pokemon.getSpecies().getLevelMoves();
		int level = pokemon.getLevel();
		int j = 1;
		for (int i = level; i > 0; i--) {
			Integer[] moveIds = map.get(i);
			if(moveIds != null){
				for(Integer moveId : moveIds){
					pokemon.setMoveSlot(j, Main.mMoveArray[moveId]);
					j++;
					if(j > 4) {
						return;
					}
				}
			}
		}
	}

}
