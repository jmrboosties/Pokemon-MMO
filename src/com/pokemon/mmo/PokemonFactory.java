/**
 * Generates actual pokemon, taking base stats and applying level, IVs, and EVs to get result. 
 */

package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Abilities;
import com.pokemon.mmo.Enums.Gender;
import com.pokemon.mmo.Enums.Stats;

public class PokemonFactory {

	public static Pokemon getPokemon(PokemonSpecies species) { // the commented
																// param would
																// be the id of
																// the pokemon
																// on the table
		// Would normally query online db for various stats, I included them in
		// a class

		Pokemon pokemon = new Pokemon(species);

		pokemon.setLevel(8);
		pokemon.setGender(Gender.MALE); // TODO RANDOM TAKING RATIO INTO ACCOUNT
		pokemon.setAbility(Abilities.OVERGROW); // TODO RANDOM FOR SPECIES

		pokemon.setSlot1(Main.mMoveArray[1]);

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

		maxHP = (((pokemon.getHPIV() + (2 * species.getSpecificStat(0))
				+ pokemon.getHPEVs() / 4 + 100) * pokemon.getLevel()) / 100) + 10;

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

}
