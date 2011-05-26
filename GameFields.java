package com.pokemon.mmo;

import java.util.Random;

import com.pokemon.mmo.BattleStats.Sport;
import com.pokemon.mmo.Enums.Abilities;
import com.pokemon.mmo.Enums.MoveKinds;
import com.pokemon.mmo.Enums.Moves;
import com.pokemon.mmo.Enums.Status;
import com.pokemon.mmo.Enums.Types;
import com.pokemon.mmo.Enums.Weather;

public class GameFields {

private static float[][] typeMatrix =
	{//represents the matrix that can be seen at pokemondb.net/type
														//Attacking Type:
	{1,1,1,1,1,1,1,1,1,1,1,1,0.5f,0,1,1,0.5f}, 			//NORMAL
	{1,0.5f,0.5f,1,2,2,1,1,1,1,1,2,0.5f,1,0.5f,1,2},			//FIRE
	{1,2,0.5f,1,0.5f,1,1,1,2,1,1,1,2,1,0.5f,1,1},				//WATER
	{1,1,2,0.5f,0.5f,1,1,1,0,2,1,1,1,1,0.5f,1,1},			//ELECTRIC
	{1,0.5f,2,1,0.5f,1,1,0.5f,2,0.5f,1,0.5f,2,1,0.5f,1,0.5f},	//GRASS
	{1,0.5f,0.5f,1,2,0.5f,1,1,2,2,1,1,1,1,2,1,0.5f},			//ICE
	{2,1,1,1,1,2,1,0.5f,1,0.5f,0.5f,0.5f,2,0,1,2,2},			//FIGHTING
	{1,1,1,1,2,1,1,0.5f,0.5f,1,1,1,0.5f,0.5f,1,1,0},			//POISON
	{1,2,1,2,0.5f,1,1,2,1,0,1,0.5f,2,1,1,1,2},				//GROUND
	{1,1,1,0.5f,2,1,2,1,1,1,1,2,0.5f,1,1,1,0.5f},			//FLYING
	{1,1,1,1,1,1,2,2,1,1,0.5f,1,1,1,1,0,0.5f},				//PSYCHIC
	{1,0.5f,1,1,2,1,0.5f,0.5f,1,0.5f,2,1,1,0.5f,1,2,0.5f},		//BUG
	{1,2,1,1,1,2,0.5f,1,0.5f,2,1,2,1,1,1,1,0.5f},			//ROCK
	{0,1,1,1,1,1,1,1,1,1,2,1,1,2,1,0.5f,0.5f},				//GHOST
	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,0.5f},				//DRAGON
	{1,1,1,1,1,1,0.5f,1,1,1,2,1,1,2,1,0.5f,0.5f},			//DARK
	{1,0.5f,0.5f,0.5f,1,2,1,1,1,1,1,1,2,1,1,1,0.5f},			//STEEL
	};
		
	public static double typeMath(Types moveType, Types defenderType1, Types defenderType2) {
		if(defenderType1 == Types.NONE && defenderType2 == Types.NONE){
			throw new IllegalArgumentException("Only one of the defender types may be Types.NONE.");
		}
		double retValue = 0.0;
		if(defenderType2 == Types.NONE){
			retValue = typeMatrix[moveType.id][defenderType1];
		}
		else if(defenderType1 == Types.NONE) {
			retValue = typeMatrix[moveType.id][defenderType2];
		}
		else{
			retValue = typeMatrix[moveType.id][defenderType1]*typeMatrix[moveType][defenderType2];
		}
		return retValue;
	}
	
	public static int damageCalc(Pokemon attacker, Pokemon defender, Move move, BattleStats battle) {
		int damageInt = 1;
		
		/**
		 * I am doing it step by step because the game cuts off any decimals at each step.
		 * 
		 * Damage Formula = (((((((Level × 2 ÷ 5) + 2) × BasePower × [Sp]Atk ÷ 50) ÷ [Sp]Def) × Mod1) + 2) × 
         *       CH × Mod2 × R ÷ 100) × STAB × Type1 × Type2 × Mod3)
		 */
		
		int levelVar = ((attacker.getLevel() * 2 / 5) + 2);
		int next1 = levelVar * calcBasePower(attacker, defender, move, battle);
		int next2 = next1 * calcAttackOrSpAttack(attacker, defender, move, battle) / 50;
		int next3 = next2 / calcDefenseOrSpecialDefense(defender, attacker, move, battle);
		int next4 = (next3 * calcMod1(attacker, defender, move, battle)) + 2;
		int next5 = (int) (next4 * /*calcCritHit(attacker, defender)*/ calcMod2(attacker, move));
		int next6 = (int) (next5 * .9);
		int next7 = (int) (next6 * stabDetermine(attacker.getType1(), attacker.getType2(), move.getType()));
		
		double effectiveness = typeMath(move.getType(), defender.getType1(), defender.getType2());
		
		double next8 = (next7 * effectiveness);
		
		damageInt = (int) (next8 * calcMod3(attacker, defender, effectiveness));
		
		return damageInt;
	}

	private static int calcBasePower(Pokemon attacker, Pokemon defender, Move move, BattleStats stats) {
		double hh = 1;
		double bp = move.getBasePower();
		double it = 1;
		double chg = 1;
		double ms = 1;
		double ws = 1;
		double ua = 1;
		double fa = 1;
		//BasePower = HH × BP × IT × CHG × MS × WS × UA × FA
		
		/**HH variable*/
		
		if(attacker.isHelpedByHand()) {
			hh = (long) 1.5;
		}
		
		/**IT variable*/
		
		switch(attacker.getHeldItem()) {
		case Items.MUSCLE_BAND :
			if(move.getKind() == MoveKinds.PHYSICAL) {
				it = 1.1;
			}
			break;
		
		case Items.WISE_GLASSES :
			if(move.getKind() == MoveKinds.SPECIAL) {
				it = 1.1;
			}
			break;
		
		//TODO ITEMS LIKE MAGNET, EARTH PLATE, ROSE INCENSE, ETC. BONUS IS 1.2
		
		case Items.ADAMANT_ORB :
			if(attacker.getSpecies().getDexNumber() == 487 /*DIGALA, FIX THE INT*/) {
				if(move.getType() == Types.STEEL || move.getType() == Types.DRAGON) {
					it = (long) 1.2;
				}
			}
			break;
		
		case Items.LUSTROUS_ORB :
			if(attacker.getSpecies().getDexNumber() == 470 /*PALKIA, FIX THE INT*/ && (move.getType() == Types.WATER || move.getType() == Types.DRAGON)) {
				it = (long) 1.2;
			}
			break;
			
		case Items.GRISEOUS_ORB :
			if(attacker.getSpecies().getDexNumber() == 480 /*GIRITANIA, FIX THE INT*/ && (move.getType() == Types.GHOST || move.getType() == Types.DRAGON)) {
				it = (long) 1.2;
			}
			break;
		}
		
		/**CHG variable*/
		
		if(stats.getLastMove().getMoveEnum() == Moves.CHARGE && move.getType() == Types.ELECTRIC) {
			chg = 2;
		}
		
		/**MS variable*/
		
		if(stats.getSport() == Sport.MUD_SPORT && move.getType() == Types.ELECTRIC) {
			ms = (long) 0.5;
		}
		
		/**WS variable*/
		
		if(stats.getSport() == Sport.WATER_SPORT && move.getType() == Types.FIRE) {
			ws = (long) 0.5;
		}
		
		/**UA variable*/
		
		switch(attacker.getAbility()) {
		case RIVALRY : /*TODO Rivalry Ability*/
			if(attacker.getGender() == defender.getGender()) {
				ua = (long) 1.25;
			}
			else if(attacker.getGender() != defender.getGender()) {
				ua = (long) 0.75;
			}
			else {
				ua = 1;
			}
			break;
		case RECKLESS : //TODO RECKLESS ABILITY
			if(move.isRecoil()) {
				ua = (long) 1.2;
			}
			break;
		case IRON_FIST : //TODO IRON FIST ABILITY
			if(move.isPunching()) {
				ua = (long) 1.2;
			}
			break;
		case BLAZE : //TODO BLAZE ABILITY
			if(attacker.getCurrentHealthRatio() < 0.3 && move.getType() == Types.FIRE) {
				ua = (long) 1.5;
			}
			break;
		case TORRENT : //TODO TORRENT ABILITY
			if(attacker.getCurrentHealthRatio() < 0.3 && move.getType() == Types.WATER) {
				ua = (long) 1.5;
			}
			break;
		case OVERGROW : //TODO OVERGROW ABILITY
			if(attacker.getCurrentHealthRatio() < 0.3 && move.getType() == Types.GRASS) {
				ua = (long) 1.5;
			}
			break;
		case SWARM : //TODO SWARM ABILITY
			if(attacker.getCurrentHealthRatio() < 0.3 && move.getType() == Types.BUG) {
				ua = (long) 1.5;
			}
			break;
		case TECHNICIAN : //TODO TECHNICIAN ABILITY
			if(move.getBasePower() <= 60) {
				ua = (long) 1.5;
			}
			break;
		}
		
		/**FA variable*/
		
		switch(defender.getAbility()) {
		case THICK_FAT : //TODO THICKFAT ABILITY
			if(move.getType() == Types.FIRE || move.getType() == Types.ICE) {
				fa = (long) 0.5;
			}
			break;
		case HEATPROOF : //TODO HEATPROOF ABILITY
			if(move.getType() == Types.FIRE) {
				fa = (long) 0.5;
			}
			break;
		case DRY_SKIN : //TODO DRY SKIN ABILITY
			if(move.getType() == Types.FIRE) {
				fa = (long) 1.25;
			}
			break;
		}
		
		/**EQUATION*/
		int basePower= (int) (hh * bp * it * chg * ms * ws * ua * fa);
		
		return basePower;
	}
	
	private static int calcAttackOrSpAttack(Pokemon attacker, Pokemon defender, Move move, BattleStats stats) {
		int attack = 1;
		int stat = 1;
		long sm = 1;
		long am = 1;
		long im = 1;
		//[Sp]Attack = stat * sm * am * im
		if(move.getKind() == MoveKinds.PHYSICAL) {
			stat = attacker.getAttack();
			
			if(attacker.getStatChanges(1) > 0) {
				sm = (attacker.getStatChanges(1) + 2) / 2;
			}
			else if(attacker.getStatChanges(1) < 0) {
				sm = 2 / (attacker.getStatChanges(1) + 2);
			}
			
			if(defender.getAbility() == Abilities.UNAWARE && attacker.getStatChanges(1) > 0/*TODO UNAWARE ABILITY*/) {
				sm = 1;
			}
			
			switch(attacker.getAbility()) {
			case PURE_POWER : //TODO PURE POWER ABILITY
			case HUGE_POWER : //TODO HUGE POWER ABILITY
				am = 2;
				break;
			case FLOWER_GIFT : //TODO FLOWER GIFT
				if(stats.getWeather() == Weather.SUNNY_DAY) {
					am = (long) 1.5;
				}
				break;
			case GUTS : //TODO GUTS
				if(attacker.isAffectedByStatusAilment() /* TODO THIS IS SUPPOSED TO BE BURN, SLEEP, PARALYZE, POISON*/) {
					am = (long) 1.5;
				}
				break;
			case HUSTLE : //TODO HUSTLE
				am = (long) 1.5;
				break;
			case SLOW_START : //TODO SLOW START
				if(attacker.getTurnsInBattle() < 5) {
					am = (long) 0.5;
				}
				break;
			}
			
			switch(attacker.getHeldItem()) {
			case Items.CHOICE_BAND :
				im = (long) 1.5;
				break;
			case Items.LIGHT_BALL :
				if(attacker.getSpecies().getDexNumber() == 25 /*TODO Pikachu*/) {
					im = 2;
				}
				break;
			case Items.THICK_CLUB :
				if(attacker.getSpecies().getDexNumber() == 40 || attacker.getSpecies().getDexNumber() == 41 /*TODO CUBONE OR MAROWAK*/) {
					im = 2;
				}
				break;
			}
			
		}
		else if(move.getKind() == MoveKinds.SPECIAL) {
			stat = attacker.getSpAttack();
			
			if(attacker.getStatChanges(3) > 0) {
				sm = (attacker.getStatChanges(3) + 2) / 2;
			}
			else if(attacker.getStatChanges(3) < 0) {
				sm = 2 / (attacker.getStatChanges(3) + 2);
			}
			
			if(defender.getAbility() == Abilities.UNAWARE && attacker.getStatChanges(3) > 0/*TODO UNAWARE ABILITY*/) {
				sm = 1;
			}
			
			switch(attacker.getAbility()) {
			case SOLAR_POWER : //TODO SOLAR POWER ABILITY
				if(stats.getWeather() == Weather.SUNNY_DAY) {
					am = (long) 1.5;
				}
			}
			
			switch(attacker.getHeldItem()) {
			case Items.CHOICE_SPECS :
				im = (long) 1.5;
				break;
			case Items.LIGHT_BALL :
				if(attacker.getSpecies().getDexNumber() == 25 /*TODO PIKACHU*/) {
					im = 2;
				}
				break;
			case Items.SOUL_DEW :
				if(attacker.getSpecies().getDexNumber() == 367 || attacker.getSpecies().getDexNumber() == 368 /*TODO LATIOS AND LATIAS*/) {
					im = (long) 1.5;
				}
				break;
			case Items.DEEPSEATOOTH :
				if(attacker.getSpecies().getDexNumber() == 300 /*TODO CLAMPERL*/) {
					im = 2;
				}
				break;
			}
		}
		
		attack = (int) (stat * sm * am * im);
		
		return attack;
	}
	
	private static int calcDefenseOrSpecialDefense(Pokemon defender, Pokemon attacker, Move move, BattleStats stats) {
		//[Sp]Def = Stat × SM × Mod
		int defense = 1;
		int stat = 1;
		double sm = 1;
		double mod = 1;
		
		switch(move.getKind()) {
		case PHYSICAL :
			stat = defender.getDefense();
			
			if(defender.getStatChanges(2) > 0) {
				sm = (defender.getStatChanges(2) + 2) / 2;
			} 
			else if(defender.getStatChanges(2) < 0) {
				sm = 2 / (defender.getStatChanges(2) + 2);
			}
			
			if(attacker.getAbility() == Abilities.UNAWARE && defender.getStatChanges(2) > 0/*TODO UNAWARE ABILITY*/) {
				sm = 1;
			}
			//mod = ability x item x sandstorm for rock
			
			if(defender.getAbility() == Abilities.FLOWER_GIFT && stats.getWeather() == Weather.SUNNY_DAY /*TODO FLOWER GIFT*/) {
				mod = 1.5;
			}
			
			switch(defender.getHeldItem()) {
			case Items.METAL_POWDER :
				if(defender.getSpecies().getDexNumber() == 126 /*TODO DITTO*/) {
					mod = (mod * 1.5);
				}
				break;
			case Items.MARVEL_SCALE :
				if(defender.isAffectedByStatusAilment()) {
					mod = (mod * 1.5);
				}
				break;
			}
			break;
			
		case SPECIAL :
			stat = defender.getSpDefense();
			
			if(defender.getStatChanges(4) > 0) {
				sm = (defender.getStatChanges(4) + 2) / 2; //TODO 4 IS THE SPECIAL DEFENSE COLUMN, FOR NOW
			}
			else if(defender.getStatChanges(4) < 0) {
				sm = 2 / (defender.getStatChanges(4) + 2);
			}
			
			if(attacker.getAbility() == Abilities.UNAWARE && defender.getStatChanges(4) > 0/*TODO UNAWARE ABILITY*/) {
				sm = 1;
			}
			if(defender.getAbility() == Abilities.FLOWER_GIFT && stats.getWeather() == Weather.SUNNY_DAY /*TODO flower gift and sunny day*/) {
				mod = 1.5;
			}
			switch(defender.getHeldItem()) {
			case Items.METAL_POWDER :
				if(defender.getSpecies().getDexNumber() == 120 /*TODO Ditto*/) {
					mod = (mod * 1.5);
				}
				break;
			case Items.SOUL_DEW :
				if(defender.getSpecies().getDexNumber() == 340 || defender.getSpecies().getDexNumber() == 341 /*TODO latios and latias*/) {
					mod = (mod * 1.5);
				}
				break;
			case Items.DEEPSEASCALE :
				if(defender.getSpecies().getDexNumber() == 320 /*TODO CLAMPERL*/) {
					mod = (mod * 1.5);
				}
				break;
			}
			if(stats.getWeather() == Weather.SANDSTORM && (defender.getType1() == Types.ROCK || defender.getType1() == Types.ROCK)) {
				mod = (mod * 1.5);
			}			
		}
		defense = (int) (stat * sm * mod);
		return defense;
	}

	private static int calcRandom() {
		//TODO NOT UNIFORM READ UP ON THIS
		Random generator = new Random();
		return generator.nextInt(16) + 85;
	}
	
	private static double stabDetermine(Types attackerType1, Types attackerType2, Types moveType) {
		double stab = 1;
		if(moveType == attackerType1 || moveType == attackerType2) {
			stab = 1.5;
		}
		return stab;
	}

	private static int calcMod1(Pokemon attacker, Pokemon defender, Move move, BattleStats battle) {
		/*Mod1 = BRN × RL × TVT × SR × FF*/
		double calcMod = 1;
		double brn = 1;
		double rl = 1;
		double sr = 1;
		double ff = 1;
		
		if(attacker.getStatus() ==  Status.BURN) {
			brn = 0.5;
		}
		if((defender.hasReflect() && move.getKind() == MoveKinds.PHYSICAL )|| (defender.hasLightScreen() && move.getKind() == MoveKinds.SPECIAL)) {
			rl = 0.5;
		}
		if(battle.getWeather() != Weather.NORMAL) {
			switch(battle.getWeather()) {
			case SUNNY_DAY :
				if(move.getType() == Types.FIRE) {
					sr = 1.5;
				}
				if(move.getType() == Types.WATER) {
					sr = 0.5;
				}
				break;
			case RAIN_DANCE :
				if(move.getType() == Types.WATER) {
					sr = 1.5;
				}
				if(move.getType() == Types.FIRE) {
					sr = 0.5;
				}
				break;
			}
		}
		if(attacker.hasFlashFire()/* TODO Flash fire*/) {
			ff = 1.5;
		}
		
		calcMod = brn * rl * sr * ff;
		return (int) calcMod;
	}
	
	private static double calcMod2(Pokemon attacker, Move move) {
		double mod2 = 1;
		if(attacker.getHeldItem() == Items.LIFE_ORB) {
			mod2 = mod2 * 1.5;
		}
		if(attacker.getHeldItem() == Items.METRONOME) {
			//TODO fuck if i know
		}
		if(move.getMoveEnum() == Moves.ME_FIRST) {
			mod2 = mod2 * 1.5;
		}
		return (int) mod2;
	}
	
	private static int calcMod3(Pokemon attacker, Pokemon defender, double effectiveness) {
		double mod3 = 1;
		double srf = 1;
		double eb = 1;
		double tl = 1;
		double trb = 1;
		//Mod3 = SRF × EB × TL × TRB
		if(defender.getAbility() == Abilities.SOLID_ROCK || defender.getAbility() == Abilities.FILTER) {
			srf = 0.75;
		}
		if(attacker.getHeldItem() == Items.EXPERT_BELT && (effectiveness >= 2)) {
			eb = 1.2;
		}
		/*if(effectiveness < 1) { THIS IS A MESS FIGURE OUT TINTED LENS
			tl = 2;
		}*/
		if(defender.getHeldItem() <= 200 && defender.getHeldItem() >= 217) {
			//TODO method to determine super effectiveness from type & berry held, if matches...
			trb = 0.5;
		}
		mod3 = srf * eb * tl * trb;
		return (int) mod3;
	}

	private static int calcCritHit(Pokemon attacker, Pokemon defender) {
		int critMult = 1;
		if(defender.getAbility() == Abilities.BATTLE_ARMOR /*50 is arbitrary, ability int for Battle Armor*/) {
			return critMult;
		}
		Random generator = new Random();
		long ran = (long) generator.nextFloat() * 100;
		if(ran < 6.25) {
			critMult = 2;
			if(attacker.getAbility() == Abilities.SNIPER /*100 is arbitrary, int for ability Sniper*/) {
				critMult = 3;
			}
		}
		return critMult;
	}
	
}
