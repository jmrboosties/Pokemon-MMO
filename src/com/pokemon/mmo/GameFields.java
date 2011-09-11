package com.pokemon.mmo;

import java.util.Random;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.ModdableBattleStats;
import com.pokemon.mmo.Enums.MoveFlag;
import com.pokemon.mmo.Enums.MoveKinds;
import com.pokemon.mmo.Enums.Moves;
import com.pokemon.mmo.Enums.NonVolatileStatusAilment;
import com.pokemon.mmo.Enums.Sport;
import com.pokemon.mmo.Enums.Stats;
import com.pokemon.mmo.Enums.Types;
import com.pokemon.mmo.Enums.Weather;

public class GameFields {

	/*	This 2D-Array represents the matrix for type effectiveness seen here:
	 *	http://bulbapedia.bulbagarden.net/wiki/Type_chart#Generations_II-V
	 */
	private static float[][] typeMatrix = {
	        /* v v v v        DEFENDING TYPE         v v v v*/
	/*>*/   {1,1,1,1,1,0.5f,1,0,0.5f,1,1,1,1,1,1,1,1},                // NORMAL
	/*A*/   {2,1,0.5f,0.5f,1,2,0.5f,0,2,1,1,1,1,0.5f,2,1,2},          // FIGHT
	/*T*/   {1,2,1,1,1,0.5f,2,1,0.5f,1,1,2,0.5f,1,1,1,1},             // FLYING
	/*T*/   {1,1,1,0.5f,0.5f,0.5f,1,0.5f,0,1,1,2,1,1,1,1,1},          // POISON
	/*A*/   {1,1,0,2,1,2,0.5f,1,2,2,1,0.5f,2,1,1,1,1},                // GROUND
	/*C*/   {1,0.5f,2,1,0.5f,1,2,1,0.5f,2,1,1,1,1,2,1,1},             // ROCK
	/*K*/   {1,0.5f,0.5f,0.5f,1,1,1,0.5f,0.5f,0.5f,1,2,1,2,1,1,2},    // BUG
	/*I*/   {0,1,1,1,1,1,1,2,0.5f,1,1,1,1,2,1,1,0.5f},                // GHOST
	/*N*/   {1,1,1,1,1,2,1,1,0.5f,0.5f,0.5f,1,0.5f,1,2,1,1},          // STEEL
	/*G*/   {1,1,1,1,1,0.5f,2,1,2,0.5f,0.5f,2,1,1,2,0.5f,1},          // FIRE
	/* */   {1,1,1,1,2,2,1,1,1,2,0.5f,0.5f,1,1,1,0.5f,1},             // WATER
	/* */   {1,1,0.5f,0.5f,2,2,0.5f,1,0.5f,0.5f,2,0.5f,1,1,1,0.5f,1}, // GRASS
	/*T*/   {1,1,2,1,0,1,1,1,1,1,2,0.5f,0.5f,1,1,0.5f,1},             // ELECTRO
	/*Y*/   {1,2,1,2,1,1,1,1,0.5f,1,1,1,1,0.5f,1,1,0},                // PSYCHIC
	/*P*/   {1,1,2,1,2,1,1,1,0.5f,0.5f,0.5f,2,1,1,0.5f,2,1},          // ICE
	/*E*/   {1,1,1,1,1,1,1,1,0.5f,1,1,1,1,1,1,2,1},                   // DRAGON
	/*>*/   {1,0.5f,1,1,1,1,1,2,0.5f,1,1,1,1,2,1,1,0.5f},             // DARK
	};

	public static double typeMath(Types moveType, Types defenderType1, Types defenderType2) {
		if(defenderType1 == Types.NONE && defenderType2 == Types.NONE){
			throw new IllegalArgumentException("Only one of the defender types may be Types.NONE.");
		}
		double retValue = 0.0;
		if(defenderType2 == Types.NONE){
			retValue = typeMatrix[moveType.getId()-1][defenderType1.getId()-1];
		}
		else if(defenderType1 == Types.NONE) {
			retValue = typeMatrix[moveType.getId()-1][defenderType2.getId()-1];
		}
		else{
			retValue = typeMatrix[moveType.getId()-1][defenderType1.getId()-1]*typeMatrix[moveType.getId()-1][defenderType2.getId()-1];
		}
		if(retValue > 1) {
			System.out.println("It's super effective!");
		}
		else if(retValue == 0) {
			System.out.println("It has no effect!");
		}
		else if(retValue < 1){
			System.out.println("It's not very effective...");
		}
		return retValue;
	}

	public static int damageCalc(BattlingPokemon attacker, BattlingPokemon defender, Move move,
			Battle battle) {
		int damageInt = 1;
		
		/**
		 * I am doing it step by step because the game cuts off any decimals at
		 * each step.
		 * 
		 * Damage Formula = (((((((Level * 2 / 5) + 2) * BasePower * [Sp]Atk /
		 * 50) / [Sp]Def) * Mod1) + 2) * CH * Mod2 * R / 100) * STAB * Type1 *
		 * Type2 * Mod3)
		 */

		int levelVar = ((attacker.getPokemon().getLevel() * 2 / 5) + 2);
		int next1 = levelVar * calcBasePower(attacker, defender, move, battle);
		int next2 = next1
				* calcAttackOrSpAttack(attacker, defender, move, battle) / 50;
		int next3 = next2
				/ calcDefenseOrSpecialDefense(defender, attacker, move, battle);
		int next4 = (next3 * calcMod1(attacker, defender, move, battle)) + 2;
		int next5 = (int) (next4 * calcCritHit(attacker, defender) * calcMod2(
				attacker, move));
		int next6 = (int) (next5 * .9);
		int next7 = (int) (next6 * stabDetermine(attacker.getPokemon().getType(1),
				attacker.getPokemon().getType(2), move.getType()));

		double effectiveness = typeMath(move.getType(), defender.getPokemon().getType(1),
				defender.getPokemon().getType(2));

		double next8 = (next7 * effectiveness);

		damageInt = (int) (next8 * calcMod3(attacker, defender, effectiveness));
		
		

		return damageInt;
	}

	private static int calcBasePower(BattlingPokemon attacker, BattlingPokemon defender,
			Move move, Battle stats) {
		
		double hh = 1;
		double bp = move.getBasePower();
		double it = 1;
		double chg = 1;
		double ms = 1;
		double ws = 1;
		double ua = 1;
		double fa = 1;
		// BasePower = HH * BP * IT * CHG * MS * WS * UA * FA

//		/** HH variable */
//TODO
//		if (attacker.isHelpedByHand()) {
//			hh = (long) 1.5;
//		}

		/** IT variable */

		switch (attacker.getItem()) {
		case Items.MUSCLE_BAND:
			if (move.getKind() == MoveKinds.PHYSICAL) {
				it = 1.1;
			}
			break;

		case Items.WISE_GLASSES:
			if (move.getKind() == MoveKinds.SPECIAL) {
				it = 1.1;
			}
			break;

		// TODO ITEMS LIKE MAGNET, EARTH PLATE, ROSE INCENSE, ETC. BONUS IS 1.2. Do this once items are in.
			//PERHAPS DO THIS WHOLE THING IN A SEPARATE CLASS OR METHOD, ITS KIND OF BIG.

		case Items.ADAMANT_ORB:
			if (attacker.getPokemon().getSpecies().getDexNumber() == 483) {
				if (move.getType() == Types.STEEL
						|| move.getType() == Types.DRAGON) {
					it = (long) 1.2;
				}
			}
			break;

		case Items.LUSTROUS_ORB:
			if (attacker.getPokemon().getSpecies().getDexNumber() == 484
					&& (move.getType() == Types.WATER || move.getType() == Types.DRAGON)) {
				it = (long) 1.2;
			}
			break;

		case Items.GRISEOUS_ORB:
			if (attacker.getPokemon().getSpecies().getDexNumber() == 480 /*
															 * GIRITANIA, FIX
															 * THE INT
															 */
					&& (move.getType() == Types.GHOST || move.getType() == Types.DRAGON)) {
				it = (long) 1.2;
			}
			break;
		}

		/** CHG variable */

		//TODO if (stats.getLastMove().getMoveEnum() == Moves.CHARGE
			//	&& move.getType() == Types.ELECTRIC) {
			//chg = 2;
		//}

		/** MS variable */

		if (stats.getSport() == Sport.MUD_SPORT
				&& move.getType() == Types.ELECTRIC) {
			ms = (long) 0.5;
		}

		/** WS variable */

		if (stats.getSport() == Sport.WATER_SPORT
				&& move.getType() == Types.FIRE) {
			ws = (long) 0.5;
		}

		/** UA variable */

		switch (attacker.getAbility()) {
		case RIVALRY :
			if (attacker.getPokemon().getGender() == defender.getPokemon().getGender()) {
				ua = (long) 1.25;
			} else if (attacker.getPokemon().getGender() != defender.getPokemon().getGender()) {
				ua = (long) 0.75;
			} else {
				ua = 1;
			}
			break;
		case RECKLESS:
			if (move.isRecoil()) {
				ua = (long) 1.2;
			}
			break;
		case IRON_FIST: 
			if (move.hasFlag(MoveFlag.PUNCHING)) { //TODO change this to flags or something
				ua = (long) 1.2;
			}
			break;
		case BLAZE:
			if (attacker.getPokemon().getCurrentHealthRatio() < 0.3
					&& move.getType() == Types.FIRE) {
				ua = (long) 1.5;
			}
			break;
		case TORRENT:
			if (attacker.getPokemon().getCurrentHealthRatio() < 0.3
					&& move.getType() == Types.WATER) {
				ua = (long) 1.5;
			}
			break;
		case OVERGROW:
			if (attacker.getPokemon().getCurrentHealthRatio() < 0.3
					&& move.getType() == Types.GRASS) {
				ua = (long) 1.5;
			}
			break;
		case SWARM: 
			if (attacker.getPokemon().getCurrentHealthRatio() < 0.3
					&& move.getType() == Types.BUG) {
				ua = (long) 1.5;
			}
			break;
		case TECHNICIAN: 
			if (move.getBasePower() <= 60) {
				ua = (long) 1.5;
			}
			break;
		}

		/** FA variable */

		switch (defender.getAbility()) {
		case THICK_FAT: 
			if (move.getType() == Types.FIRE || move.getType() == Types.ICE) {
				fa = (long) 0.5;
			}
			break;
		case HEATPROOF: 
			if (move.getType() == Types.FIRE) {
				fa = (long) 0.5;
			}
			break;
		case DRY_SKIN: 
			if (move.getType() == Types.FIRE) {
				fa = (long) 1.25;
			}
			break;
		}

		/** EQUATION */
		int basePower = (int) (hh * bp * it * chg * ms * ws * ua * fa);

		return basePower;
	}

	private static int calcAttackOrSpAttack(BattlingPokemon attacker, BattlingPokemon defender,
			Move move, Battle stats) {
		
		int attack = 1;
		int stat = 1;
		long sm = 1;
		long am = 1;
		long im = 1;
		// [Sp]Attack = stat * sm * am * im
		if (move.getKind() == MoveKinds.PHYSICAL) {
			stat = attacker.getPokemon().getStat(Stats.ATTACK);

			if (attacker.getStatStageChange(ModdableBattleStats.ATTACK) > 0) {
				sm = (attacker.getStatStageChange(ModdableBattleStats.ATTACK) + 2) / 2;
			} else if (attacker.getStatStageChange(ModdableBattleStats.ATTACK) < 0) {
				sm = 2 / (attacker.getStatStageChange(ModdableBattleStats.ATTACK) + 2);
			}

			if (defender.getAbility() == Ability.UNAWARE
					&& attacker.getStatStageChange(ModdableBattleStats.ATTACK) > 0) {
				sm = 1;
			}

			switch (attacker.getAbility()) {
			case PURE_POWER:
			case HUGE_POWER:
				am = 2;
				break;
			case FLOWER_GIFT:
				if (stats.getWeather() == Weather.SUNNY_DAY) {
					am = (long) 1.5;
				}
				break;
			case GUTS:
				if (attacker.isAffectedByStatusAilment() /*
														 * TODO THIS IS SUPPOSED
														 * TO BE BURN, SLEEP,
														 * PARALYZE, POISON
														 */) {
					am = (long) 1.5;
				}
				break;
			case HUSTLE:
				am = (long) 1.5;
				break;
			case SLOW_START:
				if (attacker.getTurnsInBattle() < 5) { //TODO
					am = (long) 0.5;
				}
				break;
			}

			switch (attacker.getItem()) {
			case Items.CHOICE_BAND:
				im = (long) 1.5;
				break;
			case Items.LIGHT_BALL:
				if (attacker.getPokemon().getSpecies().getDexNumber() == 25) {
					im = 2;
				}
				break;
			case Items.THICK_CLUB:
				if (attacker.getPokemon().getSpecies().getDexNumber() == 104
						|| attacker.getPokemon().getSpecies().getDexNumber() == 105) {
					im = 2;
				}
				break;
			}

		} else if (move.getKind() == MoveKinds.SPECIAL) {
			stat = attacker.getPokemon().getStat(Stats.SPECIAL_ATTACK);

			if (attacker.getStatStageChange(ModdableBattleStats.SPECIAL_ATTACK) > 0) {
				sm = (attacker.getStatStageChange(ModdableBattleStats.SPECIAL_ATTACK) + 2) / 2;
			} else if (attacker.getStatStageChange(ModdableBattleStats.SPECIAL_ATTACK) < 0) {
				sm = 2 / (attacker.getStatStageChange(ModdableBattleStats.SPECIAL_ATTACK) + 2);
			}

			if (defender.hasAbility(Ability.UNAWARE)
					&& attacker.getStatStageChange(ModdableBattleStats.SPECIAL_ATTACK) > 0) {
				sm = 1;
			}

			switch (attacker.getAbility()) {
			case SOLAR_POWER:
				if (stats.getWeather() == Weather.SUNNY_DAY) {
					am = (long) 1.5;
				}
			}

			switch (attacker.getItem()) {
			case Items.CHOICE_SPECS:
				im = (long) 1.5;
				break;
			case Items.LIGHT_BALL:
				if (attacker.getPokemon().getSpecies().getDexNumber() == 25) {
					im = 2;
				}
				break;
			case Items.SOUL_DEW:
				if (attacker.getPokemon().getSpecies().getDexNumber() == 380
						|| attacker.getPokemon().getSpecies().getDexNumber() == 381) {
					im = (long) 1.5;
				}
				break;
			case Items.DEEPSEATOOTH:
				if (attacker.getPokemon().getSpecies().getDexNumber() == 366) {
					im = 2;
				}
				break;
			}
		}

		attack = (int) (stat * sm * am * im);

		return attack;
	}

	private static int calcDefenseOrSpecialDefense(BattlingPokemon defender,
			BattlingPokemon attacker, Move move, Battle stats) {
		
		// [Sp]Def = Stat * SM * Mod
		int defense = 1;
		int stat = 1;
		double sm = 1;
		double mod = 1;

		switch (move.getKind()) {
		case PHYSICAL:
			stat = defender.getPokemon().getStat(Stats.DEFENSE);

			if (defender.getStatStageChange(ModdableBattleStats.DEFENSE) > 0) {
				sm = (defender.getStatStageChange(ModdableBattleStats.DEFENSE) + 2) / 2;
			} else if (defender.getStatStageChange(ModdableBattleStats.DEFENSE) < 0) {
				sm = 2 / (defender.getStatStageChange(ModdableBattleStats.DEFENSE) + 2);
			}

			if (attacker.hasAbility(Ability.UNAWARE)
					&& defender.getStatStageChange(ModdableBattleStats.DEFENSE) > 0) {
				sm = 1;
			}
			// mod = ability x item x sandstorm for rock

			if (defender.hasAbility(Ability.FLOWER_GIFT)
					&& stats.getWeather() == Weather.SUNNY_DAY) {
				mod = 1.5;
			}

			switch (defender.getItem()) {
			case Items.METAL_POWDER:
				if (defender.getPokemon().getSpecies().getDexNumber() == 132) {
					mod = (mod * 1.5);
				}
				break;
			case Items.MARVEL_SCALE:
				if (defender.isAffectedByStatusAilment()) {
					mod = (mod * 1.5);
				}
				break;
			}
			break;

		case SPECIAL:
			stat = defender.getPokemon().getStat(Stats.SPECIAL_DEFENSE);

			if (defender.getStatStageChange(ModdableBattleStats.SPECIAL_DEFENSE) > 0) {
				sm = (defender.getStatStageChange(ModdableBattleStats.SPECIAL_DEFENSE) + 2) / 2;
			} else if (defender.getStatStageChange(ModdableBattleStats.SPECIAL_DEFENSE) < 0) {
				sm = 2 / (defender.getStatStageChange(ModdableBattleStats.SPECIAL_DEFENSE) + 2);
			}

			if (attacker.hasAbility(Ability.UNAWARE)
					&& defender.getStatStageChange(ModdableBattleStats.SPECIAL_DEFENSE) > 0) {
				sm = 1;
			}
			if (defender.hasAbility(Ability.FLOWER_GIFT)
					&& stats.getWeather() == Weather.SUNNY_DAY) {
				mod = 1.5;
			}
			switch (defender.getItem()) {
			case Items.METAL_POWDER:
				if (defender.getPokemon().getSpecies().getDexNumber() == 132) {
					mod = (mod * 1.5);
				}
				break;
			case Items.SOUL_DEW:
				if (defender.getPokemon().getSpecies().getDexNumber() == 380
						|| defender.getPokemon().getSpecies().getDexNumber() == 381) {
					mod = (mod * 1.5);
				}
				break;
			case Items.DEEPSEASCALE:
				if (defender.getPokemon().getSpecies().getDexNumber() == 366) {
					mod = (mod * 1.5);
				}
				break;
			}
			if (stats.getWeather() == Weather.SANDSTORM
					&& (defender.getPokemon().getType(1) == Types.ROCK || defender.getPokemon()
							.getType(2) == Types.ROCK)) {
				mod = (mod * 1.5);
			}
		}
		defense = (int) (stat * sm * mod);
		return defense;
	}

	private static int calcRandom() {
		// TODO NOT UNIFORM READ UP ON THIS. There is a chart showing the spread of the R varaible in the formula.
		// IT RANGES FROM .85 - 1 BUT MORE LIKELY TO OCCUR IN CENTER...
		Random generator = new Random();
		return generator.nextInt(16) + 85;
	}

	private static double stabDetermine(Types attackerType1,
			Types attackerType2, Types moveType) {
		double stab = 1;
		if (moveType == attackerType1 || moveType == attackerType2) {
			stab = 1.5;
		}
		return stab;
	}

	private static int calcMod1(BattlingPokemon attacker, BattlingPokemon defender, Move move,
			Battle battle) {
		
		/* Mod1 = BRN * RL * TVT * SR * FF */
		double calcMod = 1;
		double brn = 1;
		double rl = 1;
		double sr = 1;
		double ff = 1;

		if (attacker.getStatus() == NonVolatileStatusAilment.BURN) {
			brn = 0.5;
		}
		if ((defender.hasReflect() && move.getKind() == MoveKinds.PHYSICAL) //TODO PLACEHOLDER REWORK WITH
				|| (defender.hasLightScreen() && move.getKind() == MoveKinds.SPECIAL)) { //BATTLEPLAYER IN MIND
			rl = 0.5;
		}
		if (battle.getWeather() != Weather.NORMAL) {
			switch (battle.getWeather()) {
			case SUNNY_DAY:
				if (move.getType() == Types.FIRE) {
					sr = 1.5;
				}
				if (move.getType() == Types.WATER) {
					sr = 0.5;
				}
				break;
			case RAIN_DANCE:
				if (move.getType() == Types.WATER) {
					sr = 1.5;
				}
				if (move.getType() == Types.FIRE) {
					sr = 0.5;
				}
				break;
			}
		}
		if (attacker.hasFlashFire() /*TODO create a buff system in the pokemon class which returns 
		 								an array of buffs (we could do the buffs as enums) This is useful
		 								all over our code.*/) {
			ff = 1.5;
		}

		calcMod = brn * rl * sr * ff;
		return (int) calcMod;
	}

	private static double calcMod2(BattlingPokemon attacker, Move move) {
		double mod2 = 1;
		if (attacker.getItem() == Items.LIFE_ORB) {
			mod2 = mod2 * 1.5;
		}
		if (attacker.getItem() == Items.METRONOME) {
			// TODO fuck if i know, will have to keep track of move history I guess. save this for later lol
		}
		if (move.getMoveEnum() == Moves.ME_FIRST) {
			mod2 = mod2 * 1.5;
		}
		return (int) mod2;
	}

	private static int calcMod3(BattlingPokemon attacker, BattlingPokemon defender,
			double effectiveness) {
		
		double mod3 = 1;
		double srf = 1;
		double eb = 1;
		double tl = 1;
		double trb = 1;
		// Mod3 = SRF * EB * TL * TRB
		if (defender.hasAbility(Ability.SOLID_ROCK) 
				|| defender.hasAbility(Ability.FILTER)) {
			srf = 0.75;
		}
		if (attacker.getItem() == Items.EXPERT_BELT && (effectiveness >= 2)) {
			eb = 1.2;
		}
		if(effectiveness > 1 && attacker.hasAbility(Ability.TINTED_LENS)) {
			tl = 2;
		}
		if (defender.getItem() <= 200 && defender.getItem() >= 217) {
			// TODO method to determine super effectiveness from type & berry
			// held, if matches...
			trb = 0.5;
		}
		mod3 = srf * eb * tl * trb;
		return (int) mod3;
	}

	private static int calcCritHit(BattlingPokemon attacker, BattlingPokemon defender) {
		
		int critMult = 1;
		if (defender.hasAbility(Ability.BATTLE_ARMOR)) {
			return critMult;
		}
		Random generator = new Random();
		double ran = (double) generator.nextDouble() * 100;
		double chance = 6.25;
		
		if(attacker.hasAbility(Ability.SUPER_LUCK)) {
			chance = chance * 2;
		}
		if(defender.hasAbility(Ability.BATTLE_ARMOR) || defender.hasAbility(Ability.SHELL_ARMOR)) {
			ran = 100;
		}
		
		if (ran <= chance) {
			System.out.println("Critical Hit!");
			critMult = 2;
			if (attacker.hasAbility(Ability.SNIPER)) {
				critMult = 3;
			}
			//TODO set the actual thing for this: defender.setTookACrit(true);
		}
		return critMult;
	}

}
