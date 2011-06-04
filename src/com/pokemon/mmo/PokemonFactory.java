/**
 * Generates actual pokemon, taking base stats and applying level, IVs, and EVs to get result. 
 */

package com.pokemon.mmo;

import java.util.HashMap;
import java.util.Random;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.Gender;
import com.pokemon.mmo.Enums.Stats;

public class PokemonFactory {

	public static Pokemon getPokemon(PokemonSpecies species) {

		Pokemon pokemon = new Pokemon(species);

		pokemon.setLevel(1);
		pokemon.setGender(determineGender(species));
		pokemon.setAbility(determineAbility(species));		
		
		pokemon.setHPStat(setHPStat(pokemon, species));
		for (Stats stat : Stats.values()) {
			pokemon.setStats(setStat(pokemon, species, stat), stat);
		}
		pokemon.setCurrentHP(pokemon.getHPStat());
		
		return pokemon;
	}
	
	public static Pokemon setPokemonStats(PokemonSpecies species, int level, Gender gender, Ability ability) {

		Pokemon pokemon = new Pokemon(species);

		pokemon.setLevel(level);
		pokemon.setGender(gender);
		pokemon.setAbility(ability);

		pokemon.setHPStat(setHPStat(pokemon, species));
		
		for (Stats stat : Stats.values()) {
			pokemon.setStats(setStat(pokemon, species, stat), stat);
		}
		pokemon.setCurrentHP(pokemon.getHPStat());

		return pokemon;
	}

	private static int setHPStat(Pokemon pokemon, PokemonSpecies species) {
		int maxHP = 1;

		/**
		 * HP = (((IV + (2 * base) + (EV/4) + 100) * Level)/100) + 10
		 */
		
		maxHP = (((pokemon.getHPIV() + (2 * species.getSpecificStat(0)) + (pokemon.getHPEVs()/4) + 100) * pokemon.getLevel()) / 100) + 10;

		return maxHP;
	}

	private static int setStat(Pokemon pokemon, PokemonSpecies species,
			Stats stat) {
		int totalStat = 1;
		double iv = 0;
		double ev = 0;
		int base = 0;

		switch (stat) {
		case HP:
			// TODO NOTHING, OR COMBINE THIS WITH THE OTHER METHOD
			break;
		case ATTACK:
			iv = pokemon.getAttackIV();
			ev = pokemon.getAttackEVs();
			base = species.getSpecificStat(1);
			break;
		case DEFENSE:
			iv = pokemon.getDefenseIV();
			ev = pokemon.getDefenseEVs();
			base = species.getSpecificStat(2);
			break;
		case SPECIAL_ATTACK:
			iv = pokemon.getSpAttackIV();
			ev = pokemon.getSpAttackEVs();
			base = species.getSpecificStat(3);
			break;
		case SPECIAL_DEFENSE:
			iv = pokemon.getSpDefenseIV();
			ev = pokemon.getSpDefenseEVs();
			base = species.getSpecificStat(4);
			break;
		case SPEED:
			iv = pokemon.getSpeedIV();
			ev = pokemon.getSpeedEVs();
			base = species.getSpecificStat(5);
			;
			break;
		}

		/**
		 * Stat = ((((IV + (2 + base) + (EV/4) * Level) / 100) + 5) * Nature
		 */

		totalStat = (int) ((((iv + (2 * base) + (ev / 4)) * pokemon.getLevel()) / 100) + 5) /*
																							 *  *
																							 * nature
																							 */;

		return totalStat;
	}
	
	public static void respecStats(Pokemon pokemon, PokemonSpecies species) {
		pokemon.setHPStat(setHPStat(pokemon, species));
		for (Stats stat : Stats.values()) {
			pokemon.setStats(setStat(pokemon, species, stat), stat);
		}
		pokemon.setCurrentHP(pokemon.getHPStat());
		determineMoves(pokemon);
	}
	
	private static Ability determineAbility(PokemonSpecies species) {
		Random generator = new Random();
		int numOfAbilities = 0;
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
				return abilities[0];
			}
			else {
				return abilities[1];
			}
		}
		else {
			int odds = 90 / numOfAbilities;
			int r = generator.nextInt(100) + 1;
			if(r <= 10) {
				return species.getDreamAbility();
			}
			else if(r <= odds + 10) {
				return abilities[0];
			}
			else {
				return abilities[1];
			}
		}
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
		HashMap map = pokemon.getSpecies().getHashMap(0);
		int level = pokemon.getLevel();
		int j = 1;
		for (int i = level; i > 0 || j == 4; i--) {
			Integer moveId = (Integer) map.get(i);
			if(moveId != null) {
				pokemon.setMoveSlot(j, Main.mMoveArray[moveId]);
			}
		}
	}

}
