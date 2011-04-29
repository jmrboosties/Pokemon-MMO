/**
 * Generates actual pokemon, taking base stats and applying level, IVs, and EVs to get result. 
 */

package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Stats;

public class PokemonFactory {
	
	public static Pokemon getPokemon(PokemonSpecies species /*, int id*/) { //the commented param would be the id of the pokemon on the table*/
		//Would normally query online db for various stats, I included them in a class		
		
		if(species.getDexNumber() == 4) {
			Pokemon charmander = new Pokemon(species);
			
			charmander.setNickName(MyCharmander.NICKNAME);
			charmander.setLevel(MyCharmander.LEVEL);
			charmander.setGender(MyCharmander.GENDER);
			charmander.setAbility(MyCharmander.ABILITY);
			
			charmander.setHPIV(MyCharmander.HP_IV);
			charmander.setAttackIV(MyCharmander.ATTACK_IV);
			charmander.setDefenseIV(MyCharmander.DEFENSE_IV);
			charmander.setSpAttackIV(MyCharmander.SPECIAL_ATTACK_IV);
			charmander.setSpDefenseIV(MyCharmander.SPECIAL_DEFENSE_IV);
			charmander.setSpeedIV(MyCharmander.SPEED_IV);
			
			charmander.setHPEVs(MyCharmander.HP_EVS);
			charmander.setAttackEVs(MyCharmander.ATTACK_EVS);
			charmander.setDefenseEVs(MyCharmander.DEFENSE_EVS);
			charmander.setSpAttackEVs(MyCharmander.SPECIAL_ATTACK_EVS);
			charmander.setSpDefenseEVs(MyCharmander.SPECIAL_DEFENSE_EVS);
			charmander.setSpeedEVs(MyCharmander.SPEED_EVS);
			
			charmander.setHPStat(setHPStat(charmander, species));
			for (Stats stat : Stats.values()) {
				charmander.setStats(setStat(charmander, species, stat), stat);
			}
			charmander.setCurrentHP(charmander.getHPStat()); //TODO GET THIS FROM DB
			
			return charmander;
		}
		else if(species.getDexNumber() == 7) {
			Pokemon squirtle = new Pokemon(species);
			
			squirtle.setNickName(MySquirtle.NICKNAME);
			squirtle.setLevel(MySquirtle.LEVEL);
			squirtle.setGender(MySquirtle.GENDER);
			squirtle.setAbility(MySquirtle.ABILITY);
			
			squirtle.setHPIV(MySquirtle.HP_IV);
			squirtle.setAttackIV(MySquirtle.ATTACK_IV);
			squirtle.setDefenseIV(MySquirtle.DEFENSE_IV);
			squirtle.setSpAttackIV(MySquirtle.SPECIAL_ATTACK_IV);
			squirtle.setSpDefenseIV(MySquirtle.SPECIAL_DEFENSE_IV);
			squirtle.setSpeedIV(MySquirtle.SPEED_IV);
			
			squirtle.setHPEVs(MySquirtle.HP_EVS);
			squirtle.setAttackEVs(MySquirtle.ATTACK_EVS);
			squirtle.setDefenseEVs(MySquirtle.DEFENSE_EVS);
			squirtle.setSpAttackEVs(MySquirtle.SPECIAL_ATTACK_EVS);
			squirtle.setSpDefenseEVs(MySquirtle.SPECIAL_DEFENSE_EVS);
			squirtle.setSpeedEVs(MySquirtle.SPEED_EVS);
			
			squirtle.setHPStat(setHPStat(squirtle, species));
			for (Stats stat : Stats.values()) {
				squirtle.setStats(setStat(squirtle, species, stat), stat);
			}
			squirtle.setCurrentHP(squirtle.getHPStat()); //TODO ALSO GET THIS FROM DB
			
			return squirtle;
		}
		
		return null;
		
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
		
		totalStat = ((((iv + (2 + base) + (ev/4) + 100) * pokemon.getLevel()) / 100) + 5) /* * nature */;
		
		return totalStat;
	}
	
}
