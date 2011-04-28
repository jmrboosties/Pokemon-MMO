/**
 * Generates actual pokemon, taking base stats and applying level, IVs, and EVs to get result. 
 */

package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Stats;

public class PokemonFactory {
	
	public static Pokemon getPokemon(PokemonSpecies species /*, int id*/) { //the commented param would be the id of the pokemon on the table*/
		//Would normally query online db for various stats, I included them in a class		
		Pokemon charmander = new Pokemon();
		
		charmander.setNickName(MyCharmander.NICKNAME);
		charmander.setLevel(MyCharmander.LEVEL);
		
		charmander.setHPIV(MyCharmander.HP_IV);
		charmander.setAttackIV(MyCharmander.ATTACK_IV);
		charmander.setDefenseIV(MyCharmander.DEFENSE_IV);
		charmander.setSpAttackIV(MyCharmander.SPECIAL_ATTACK_IV);
		charmander.setSpDefenseIV(MyCharmander.SPECIAL_DEFENSE_IV);
		charmander.setSpeedIV(MyCharmander.SPEED_IV);
		
		charmander.setHPStat(setHPStat(charmander, species));
		for (Stats stat : Stats.values()) {
			charmander.setStats(setStat(charmander, species, stat), stat);
		}
		
		return charmander;
	}
	
	private static int setHPStat(Pokemon pokemon, PokemonSpecies species) {
		int maxHP = 1;
		
		/**
		 * HP = (((IV + (2 * base) + (EV/4) + 100) * Level)/100) + 10
		 */
		
		maxHP = (((pokemon.getHPIV() + (2 * species.getBaseHP()) + 
				pokemon.getHPEVs()/4 + 100) * pokemon.getLevel()) / 100) + 10;		
		
		return maxHP;
	}
	
	private static int setStat(Pokemon pokemon, PokemonSpecies species, Stats stat) {
		int totalStat = 1;
		int iv = 0;
		int ev = 0;
		int base = 0;
		
		switch(stat) {
		case ATTACK :
			iv = pokemon.getAttackIV();
			ev = pokemon.getAttackEVs();
			base = species.getBaseAtk();
			break;
		case DEFENSE :
			iv = pokemon.getDefenseIV();
			ev = pokemon.getDefenseEVs();
			base = species.getBaseDef();
			break;
		case SPECIAL_ATTACK :
			iv = pokemon.getSpAttackIV();
			ev = pokemon.getSpAttackEVs();
			base = species.getBaseSpecA();
			break;
		case SPECIAL_DEFENSE :
			iv = pokemon.getSpDefenseIV();
			ev = pokemon.getSpDefenseEVs();
			base = species.getBaseSpecD();
			break;
		case SPEED :
			iv = pokemon.getSpeedIV();
			ev = pokemon.getSpeedEVs();
			base = species.getBaseSpeed();
			break;
		}
		
		/**
		 * Stat = ((((IV + (2 + base) + (EV/4) * Level) / 100) + 5) * Nature
		 */
		
		totalStat = ((((iv + (2 + base) + (ev/4) + 100) * pokemon.getLevel()) / 100) + 5);
		
		return totalStat;
	}
	
}
