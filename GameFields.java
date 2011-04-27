package com.pokemon.mmo;

import java.util.Random;

import com.pokemon.mmo.Enums.Types;

public class GameFields {
	
	/**POKEMON TYPES*/
	public static final int NO_TYPE = 0;
	public static final int NORMAL = 1;
	public static final int FIRE = 2;
	public static final int WATER = 3;
	public static final int ELECTRIC = 4;
	public static final int GRASS = 5;
	public static final int ICE = 6;
	public static final int FIGHTING = 7;
	public static final int POISON = 8;
	public static final int GROUND = 9;
	public static final int FLYING = 10;
	public static final int PSYCHIC = 11;
	public static final int BUG = 12;
	public static final int ROCK = 13;
	public static final int GHOST = 14;
	public static final int DRAGON = 15;
	public static final int DARK = 16;
	public static final int STEEL = 17;
	
	/**MAJOR STATUS EFFECTS*/
	public static final int STATUS_NORMAL = 0;
	public static final int STATUS_POISONED = 1;
	public static final int STATUS_TOXIC_POISONED = 2;
	public static final int STATUS_SLEEPING = 3;
	public static final int STATUS_BURNED = 4;
	public static final int STATUS_PARALYZED = 5;
	public static final int STATUS_FROZEN = 6;
	
	/**NON MAJOR STATUS EFFECTS*/
	public static final int NOTHING_WRONG = 0;
	public static final int CONFUSED = 1;
	public static final int ATTRACTED = 2;
	public static final int CANT_ESCAPE = 3;
	public static final int PERISH_SONG = 4;
	public static final int YAWNED = 5;
	
	private Types m_attackerType1;
	private Types m_attackerType2;
	private Types m_defenderType1;
	private Types m_defenderType2;
	
	public void setAttackerType1(Types type) {
		this.m_attackerType1 = type;
	}
	
	public void setAttackerType2(Types type) {
		this.m_attackerType2 = type;
	}
	
	public void setDefenderType1(Types type) {
		this.m_defenderType1 = type;
	}
	
	public void setDefenderType2(Types type) {
		this.m_defenderType2 = type;
	}
		
	public static long typeMath(Types moveType, Types defenderType1, Types defenderType2) {
		long multiplier = 1;
		switch(moveType) {
		case NORMAL :
			switch(defenderType1) {
			case ROCK :
			case STEEL : 
				multiplier = (long) (multiplier*(.5));
				break;
			case GHOST :
				multiplier = (long) (multiplier*(0));
				break;
			}
			switch(defenderType2) {
			case ROCK :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			case GHOST :
				multiplier = (long) (multiplier*(0));
				break;
			}
			break;
		
		case FIRE :
			switch(defenderType1) {
			case GRASS : 
			case ICE :
			case BUG :
			case STEEL :
				multiplier = (long) (multiplier*(2));
				break;
			case ROCK :
			case DRAGON :
			case WATER :
			case FIRE :
				multiplier = (long) (multiplier*(.5));
				break;
			}
			switch(defenderType2) {
			case GRASS : 
			case ICE :
			case BUG :
			case STEEL :
				multiplier = (long) (multiplier*(2));
				break;
			case ROCK :
			case DRAGON :
			case WATER :
			case FIRE :
				multiplier = (long) (multiplier*(.5));
				break;
			}
			break;
			
		case WATER :
			switch(defenderType1) {
			case FIRE :
			case GROUND :
			case ROCK :
				multiplier = (long) (multiplier*(2));
				break;
			case WATER :
			case GRASS :
			case DRAGON :
				multiplier = (long) (multiplier*(.5));
				break;
			}
			switch(defenderType2) {
			case FIRE :
			case GROUND :
			case ROCK :
				multiplier = (long) (multiplier*(2));
				break;
			case WATER :
			case GRASS :
			case DRAGON :
				multiplier = (long) (multiplier*(.5));
				break;
			}
			break;
			
		case ELECTRIC :
			switch(defenderType1) {
			case WATER :
			case FLYING :
				multiplier = (long) (multiplier*(2));
				break;
			case ELECTRIC :
			case GRASS :
			case DRAGON :
				multiplier = (long) (multiplier*(.5));
				break;
			case GROUND :
				multiplier = (long) (multiplier*(0));
				break;
			}
			switch(defenderType2) {
			case WATER :
			case FLYING :
				multiplier = (long) (multiplier*(2));
				break;
			case ELECTRIC :
			case GRASS :
			case DRAGON :
				multiplier = (long) (multiplier*(.5));
				break;
			case GROUND :
				multiplier = (long) (multiplier*(0));
				break;
			}
			break;
			
		case GRASS :
			switch(defenderType1) {
			case FIRE :
			case GRASS :
			case POISON :
			case FLYING :
			case BUG :
			case DRAGON :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			case WATER :
			case GROUND :
			case ROCK :
				multiplier = (long) (multiplier*(2));
				break;
			}
			switch(defenderType2) {
			case FIRE :
			case GRASS :
			case POISON :
			case FLYING :
			case BUG :
			case DRAGON :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			case WATER :
			case GROUND :
			case ROCK :
				multiplier = (long) (multiplier*(2));
				break;
			}
			break;
			
		case ICE :
			switch(defenderType1) {
			case FIRE :
			case WATER :
			case ICE :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			case GRASS :
			case GROUND :
			case FLYING :
			case DRAGON :
				multiplier = (long) (multiplier*(2));
				break;
			}
			switch(defenderType2) {
			case FIRE :
			case WATER :
			case ICE :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			case GRASS :
			case GROUND :
			case FLYING :
			case DRAGON :
				multiplier = (long) (multiplier*(2));
				break;
			}
			break;
			
		case FIGHTING :
			switch(defenderType1) {
			case NORMAL :
			case ICE :
			case ROCK :
			case DARK :
			case STEEL :
				multiplier = (long) (multiplier*(2));
				break;
			case POISON :
			case FLYING :
			case PSYCHIC :
			case BUG :
				multiplier = (long) (multiplier*(.5));
				break;
			case GHOST :
				multiplier = (long) (multiplier*(0));
				break;
			}
			switch(defenderType2) {
			case NORMAL :
			case ICE :
			case ROCK :
			case DARK :
			case STEEL :
				multiplier = (long) (multiplier*(2));
				break;
			case POISON :
			case FLYING :
			case PSYCHIC :
			case BUG :
				multiplier = (long) (multiplier*(.5));
				break;
			case GHOST :
				multiplier = (long) (multiplier*(0));
				break;
			}
			break;
			
		case POISON :
			switch(defenderType1) {
			case GRASS :
				multiplier = (long) (multiplier*(2));
				break;
			case POISON :
			case GROUND :
			case ROCK :
			case GHOST :
				multiplier = (long) (multiplier*(.5));
				break;
			case STEEL :
				multiplier = (long) (multiplier*(0));
				break;
			}
			switch(defenderType2) {
			case GRASS :
				multiplier = (long) (multiplier*(2));
				break;
			case POISON :
			case GROUND :
			case ROCK :
			case GHOST :
				multiplier = (long) (multiplier*(.5));
				break;
			case STEEL :
				multiplier = (long) (multiplier*(0));
				break;
			}
			break;
			
		case GROUND :
			switch(defenderType1) {
			case FIRE :
			case ELECTRIC :
			case POISON :
			case ROCK :
			case STEEL :
				multiplier = (long) (multiplier*(2));
				break;
			case GRASS :
			case BUG :
				multiplier = (long) (multiplier*(.5));
				break;
			case FLYING :
				multiplier = (long) (multiplier*(0));
				break;
			}
			switch(defenderType2) {
			case FIRE :
			case ELECTRIC :
			case POISON :
			case ROCK :
			case STEEL :
				multiplier = (long) (multiplier*(2));
				break;
			case GRASS :
			case BUG :
				multiplier = (long) (multiplier*(.5));
				break;
			case FLYING :
				multiplier = (long) (multiplier*(0));
				break;
			}
			break;
			
		case FLYING :
			switch(defenderType1) {
			case ELECTRIC :
			case ROCK :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			case GRASS :
			case FIGHTING :
			case BUG :
				multiplier = (long) (multiplier*(2));
				break;
			}
			switch(defenderType2) {
			case ELECTRIC :
			case ROCK :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			case GRASS :
			case FIGHTING :
			case BUG :
				multiplier = (long) (multiplier*(2));
				break;
			}
			break;
			
		case PSYCHIC :
			switch(defenderType1) {
			case FIGHTING :
			case POISON :
				multiplier = (long) (multiplier*(2));
				break;
			case PSYCHIC :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			case DARK :
				multiplier = (long) (multiplier*(0));
				break;
			}
			switch(defenderType2) {
			case FIGHTING :
			case POISON :
				multiplier = (long) (multiplier*(2));
				break;
			case PSYCHIC :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			case DARK :
				multiplier = (long) (multiplier*(0));
				break;
			}
			break;
			
		case BUG :
			switch(defenderType1) {
			case FIRE :
			case FIGHTING :
			case FLYING :
			case POISON :
			case GHOST :
				multiplier = (long) (multiplier*(.5));
				break;
			case GRASS :
			case PSYCHIC :
			case DARK :
				multiplier = (long) (multiplier*(2));
				break;
			}
			switch(defenderType2) {
			case FIRE :
			case FIGHTING :
			case FLYING :
			case POISON :
			case GHOST :
				multiplier = (long) (multiplier*(.5));
				break;
			case GRASS :
			case PSYCHIC :
			case DARK :
				multiplier = (long) (multiplier*(2));
				break;
			}
			break;
			
		case ROCK :
			switch(defenderType1) {
			case FIRE :
			case ICE :
			case FLYING :
			case BUG :
				multiplier = (long) (multiplier*(2));
				break;
			case FIGHTING :
			case GROUND :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			}
			switch(defenderType2) {
			case FIRE :
			case ICE :
			case FLYING :
			case BUG :
				multiplier = (long) (multiplier*(2));
				break;
			case FIGHTING :
			case GROUND :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			}
			break;
			
		case GHOST :
			switch(defenderType1) {
			case NORMAL :
				multiplier = (long) (multiplier*(0));
				break;
			case PSYCHIC :
			case GHOST :
				multiplier = (long) (multiplier*(2));
				break;
			case DARK :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			}
			switch(defenderType2) {
			case NORMAL :
				multiplier = (long) (multiplier*(0));
				break;
			case PSYCHIC :
			case GHOST :
				multiplier = (long) (multiplier*(2));
				break;
			case DARK :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			}
			break;
		
		case DRAGON :
			switch(defenderType1) {
			case DRAGON :
				multiplier = (long) (multiplier*(2));
				break;
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			}
			switch(defenderType2) {
			case DRAGON :
				multiplier = (long) (multiplier*(2));
				break;
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			}
			break;
			
		case DARK :
			switch(defenderType1) {
			case FIGHTING :
			case DARK :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			case PSYCHIC :
			case GHOST :
				multiplier = (long) (multiplier*(2));
				break;
			}
			switch(defenderType2) {
			case FIGHTING :
			case DARK :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			case PSYCHIC :
			case GHOST :
				multiplier = (long) (multiplier*(2));
				break;
			}
			break;
			
		case STEEL :
			switch(defenderType1) {
			case FIRE :
			case WATER :
			case ELECTRIC :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			case ICE :
			case ROCK :
				multiplier = (long) (multiplier*(2));
				break;
			}
			switch(defenderType2) {
			case FIRE :
			case WATER :
			case ELECTRIC :
			case STEEL :
				multiplier = (long) (multiplier*(.5));
				break;
			case ICE :
			case ROCK :
				multiplier = (long) (multiplier*(2));
				break;
			}
			break;
		}
		return multiplier;
	}
	
	public static int damageCalc(Pokemon attacker, Pokemon defender, Move move, BattleStats battle) {
		int damageInt = 1;
		
		int levelVar = ((attacker.getLevel() * 2 / 5) + 2);
		int next1 = levelVar * calcBasePower(attacker, defender, move, battle);
		int next2 = next1 * calcAttackOrSpAttack(attacker, defender, move, battle) / 50;
		int next3 = next2 / calcAttackOrSpAttack(attacker, defender, move, battle);
		int next4 = (next3 * calcMod1(attacker, defender, move, battle)) + 2;
		int next5 = next4 * calcCritHit(attacker, defender) * calcMod2(attacker, move);
		int next6 = next5 * calcRandom() / 100;
		int next7 = (int) (next6 * stabDetermine(attacker.getType1(), attacker.getType2(), move.getType()));
		
		long effectiveness = typeMath(move.getType(), defender.getType1(), defender.getType2());
		
		int next8 = (int) (next7 * effectiveness);
		int next9 = (int) (next8 * calcMod3(attacker, defender, effectiveness));
		
		/**
		 * I am doing it step by step because the game cuts off any decimals at each step.
		 */
		
		damageInt = next9;
		return damageInt;
	}

	private static int calcBasePower(Pokemon attacker, Pokemon defender, Move move, BattleStats stats) {
		int basePower = 0;
		long hh = 1;
		long bp = move.getBasePower();
		long it = 1;
		long chg = 1;
		long ms = 1;
		long ws = 1;
		long ua = 1;
		long fa = 1;
		//BasePower = HH × BP × IT × CHG × MS × WS × UA × FA
		
		/**HH variable*/
		
		if(attacker.getBuffs() == 4 /*TODO HELPING HAND BUFF IS 4 FOR NOW*/) {
			hh = (long) 1.5;
		}
		
		/**IT variable*/
		
		switch(attacker.getHeldItem()) {
		case Items.MUSCLE_BAND :
			if(move.getKind() == Move.PHYSICAL) {
				it = (long) 1.1;
			}
			break;
		
		case Items.WISE_GLASSES :
			if(move.getKind() == Move.SPECIAL) {
				it = (long) 1.1;
			}
			break;
		
		//TODO ITEMS LIKE MAGNET, EARTH PLATE, ROSE INCENSE, ETC. BONUS IS 1.2
		
		case Items.ADAMANT_ORB :
			if(attacker.getSpecies() == 487 /*DIGALA, FIX THE INT*/) {
				if(move.getType() == STEEL || move.getType() == DRAGON) {
					it = (long) 1.2;
				}
			}
			break;
		
		case Items.LUSTROUS_ORB :
			if(attacker.getSpecies() == 470 /*PALKIA, FIX THE INT*/ && (move.getType() == WATER || move.getType() == DRAGON)) {
				it = (long) 1.2;
			}
			break;
			
		case Items.GRISEOUS_ORB :
			if(attacker.getSpecies() == 480 /*GIRITANIA, FIX THE INT*/ && (move.getType() == GHOST || move.getType() == DRAGON)) {
				it = (long) 1.2;
			}
			break;
		}
		
		/**CHG variable*/
		
		if(stats.previousMove(1) == Move.CHARGE && move.getType() == ELECTRIC) {
			chg = 2;
		}
		
		/**MS variable*/
		
		if(stats.getSport() == BattleStats.MUD_SPORT) {
			ms = (long) 0.5;
		}
		
		/**WS variable*/
		
		if(stats.getSport() == BattleStats.WATER_SPORT) {
			ws = (long) 0.5;
		}
		
		/**UA variable*/
		
		switch(attacker.getAbility()) {
		case 68 : /*TODO Rivalry Ability*/
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
		case 65 : //TODO RECKLESS ABILITY
			if(move.isRecoil()) {
				ua = (long) 1.2;
			}
			break;
		case 45 : //TODO IRON FIST ABILITY
			if(move.isPunching()) {
				ua = (long) 1.2;
			}
			break;
		case 12 : //TODO BLAZE ABILITY
			if(attacker.getCurrentHealthRatio() < 0.3 && move.getType() == FIRE) {
				ua = (long) 1.5;
			}
			break;
		case 13 : //TODO TORRENT ABILITY
			if(attacker.getCurrentHealthRatio() < 0.3 && move.getType() == WATER) {
				ua = (long) 1.5;
			}
			break;
		case 14 : //TODO OVERGROW ABILITY
			if(attacker.getCurrentHealthRatio() < 0.3 && move.getType() == GRASS) {
				ua = (long) 1.5;
			}
			break;
		case 15 : //TODO SWARM ABILITY
			if(attacker.getCurrentHealthRatio() < 0.3 && move.getType() == BUG) {
				ua = (long) 1.5;
			}
			break;
		case 90 : //TODO TECHNICIAN ABILITY
			if(move.getBasePower() <= 60) {
				ua = (long) 1.5;
			}
			break;
		}
		
		/**FA variable*/
		
		switch(defender.getAbility()) {
		case 76 : //TODO THICKFAT ABILITY
			if(move.getType() == FIRE || move.getType() == ICE) {
				fa = (long) 0.5;
			}
			break;
		case 44 : //TODO HEATPROOF ABILITY
			if(move.getType() == FIRE) {
				fa = (long) 0.5;
			}
			break;
		case 33 : //TODO DRY SKIN ABILITY
			if(move.getType() == FIRE) {
				fa = (long) 1.25;
			}
			break;
		}
		
		/**EQUATION*/
		Long equation = hh * bp * it * chg * ms * ws * ua * fa;
		basePower = equation.intValue();
		
		return basePower;
	}
	
	private static int calcAttackOrSpAttack(Pokemon attacker, Pokemon defender, Move move, BattleStats stats) {
		int attack = 1;
		int stat = 1;
		long sm = 1;
		long am = 1;
		long im = 1;
		//[Sp]Attack = stat * sm * am * im
		if(move.getKind() == Move.PHYSICAL) {
			stat = attacker.getAttackStat();
			
			if(attacker.getStatChanges(1) > 0) {
				sm = (attacker.getStatChanges(1) + 2) / 2;
			}
			else if(attacker.getStatChanges(1) < 0) {
				sm = 2 / (attacker.getStatChanges(1) + 2);
			}
			
			if(defender.getAbility() == 70 && attacker.getStatChanges(1) > 0/*TODO UNAWARE ABILITY*/) {
				sm = 1;
			}
			
			switch(attacker.getAbility()) {
			case 23 : //TODO PURE POWER ABILITY
			case 24 : //TODO HUGE POWER ABILITY
				am = 2;
				break;
			case 25 : //TODO FLOWER GIFT
				if(stats.getWeather = BattleStats.SUNNY_DAY) {
					am = (long) 1.5;
				}
				break;
			case 26 : //TODO GUTS
				if(attacker.getStatus() == 5 /* TODO THIS IS SUPPOSED TO BE BURN, SLEEP, PARALYZE, POISON*/) {
					am = (long) 1.5;
				}
				break;
			case 27 : //TODO HUSTLE
				am = (long) 1.5;
				break;
			case 28 : //TODO SLOW START
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
				if(attacker.getSpecies() == 25 /*TODO Pikachu*/) {
					im = 2;
				}
				break;
			case Items.THICK_CLUB :
				if(attacker.getSpecies() == 40 || attacker.getSpecies() == 41 /*TODO CUBONE OR MAROWAK*/) {
					im = 2;
				}
				break;
			}
			
		}
		else if(move.getKind() == Move.SPECIAL) {
			stat = attacker.getSpecialAttackStat();
			
			if(attacker.getStatChanges(3) > 0) {
				sm = (attacker.getStatChanges(3) + 2) / 2;
			}
			else if(attacker.getStatChanges(3) < 0) {
				sm = 2 / (attacker.getStatChanges(3) + 2);
			}
			
			if(defender.getAbility() == 70 && attacker.getStatChanges(3) > 0/*TODO UNAWARE ABILITY*/) {
				sm = 1;
			}
			
			switch(attacker.getAbility()) {
			case 99 : //TODO SOLAR POWER ABILITY
				if(stats.getWeather() == BattleStats.SUNNY_DAY) {
					am = (long) 1.5;
				}
			}
			
			switch(attacker.getHeldItem()) {
			case Items.CHOICE_SPECS :
				im = (long) 1.5;
				break;
			case Items.LIGHT_BALL :
				if(attacker.getSpecies() == 25 /*TODO PIKACHU*/) {
					im = 2;
				}
				break;
			case Items.SOUL_DEW :
				if(attacker.getSpecies() == 367 || attacker.getSpecies() == 368 /*TODO LATIOS AND LATIAS*/) {
					im = (long) 1.5;
				}
				break;
			case Items.DEEPSEATOOTH :
				if(attacker.getSpecies() == 300 /*TODO CLAMPERL*/) {
					im = 2;
				}
				break;
			}
		}
		
		attack = (int) (stat * sm * am * im);
		
		return attack;
	}
	
	private static int calcDefenseOrSpecialDefense(Pokemon defender, Pokemon attacker, Move move, BattleStats stats) {
		//[Sp]Def = Stat × SM × Mod × SX
		int defense = 1;
		int stat = 1;
		long sm = 1;
		long mod = 1;
		long sx = 1;
		
		switch(move.getKind()) {
		case Move.PHYSICAL :
			stat = defender.getDefenseStat();
			
			if(defender.getStatChanges(2) > 0) {
				sm = (defender.getStatChanges(2) + 2) / 2;
			} 
			else if(defender.getStatChanges(2) < 0) {
				sm = 2 / (defender.getStatChanges(2) + 2);
			}
			
			if(attacker.getAbility() == 70 && defender.getStatChanges(2) > 0/*TODO UNAWARE ABILITY*/) {
				sm = 1;
			}
			
			if(move.getMove() == 310 || move.getMove() == 400 /*TODO Self destruct or explosion*/) {
				sx = (long) 0.5;
			}
			
			//mod = ability x item x sandstorm for rock
			
			if(defender.getAbility() == 43 && stats.getWeather() == BattleStats.SUNNY_DAY /*TODO FLOWER GIFT*/) {
				mod = (long) 1.5;
			}
			
			switch(defender.getHeldItem()) {
			case Items.METAL_POWDER :
				if(defender.getSpecies() == 126 /*TODO DITTO*/) {
					mod = (long) (mod * 1.5);
				}
				break;
			case Items.MARVEL_SCALE :
				if(defender.getStatus = 5 /*Asleep poison burned paralyzed*/) {
					mod = (long) (mod * 1.5);
				}
				break;
			}
			break;
			
		case Move.SPECIAL :
			stat = defender.getSpecialDefenseStat();
			
			if(defender.getStatChanges(4) > 0) {
				sm = (defender.getStatChanges(4) + 2) / 2; //TODO 4 IS THE SPECIAL DEFENSE COLUMN, FOR NOW
			}
			else if(defender.getStatChanges(4) < 0) {
				sm = 2 / (defender.getStatChanges(4) + 2);
			}
			
			if(attacker.getAbility() == 70 && defender.getStatChanges(4) > 0/*TODO UNAWARE ABILITY*/) {
				sm = 1;
			}
			if(defender.getAbility() == 100 && stats.getWeather() == BattleStats.SUNNY_DAY /*TODO flower gift and sunny day*/) {
				mod = (long) 1.5;
			}
			switch(defender.getHeldItem()) {
			case Items.METAL_POWDER :
				if(defender.getSpecies() == 120 /*TODO Ditto*/) {
					mod = (long) (mod * 1.5);
				}
				break;
			case Items.SOUL_DEW :
				if(defender.getSpecies() == 340 || defender.getSpecies() == 341 /*TODO latios and latias*/) {
					mod = (long) (mod * 1.5);
				}
				break;
			case Items.DEEPSEASCALE :
				if(defender.getSpecies() == 320 /*TODO CLAMPERL*/) {
					mod = (long) (mod * 1.5);
				}
				break;
			}
			if(stats.getWeather() == BattleStats.SANDSTORM && (defender.getType1() == ROCK || defender.getType1() == ROCK)) {
				mod = (long) (mod * 1.5);
			}			
		}
		defense = (int) (stat * sm * mod * sx);
		return defense;
	}

	private static int calcRandom() {
		//TODO NOT UNIFORM READ UP ON THIS
		Random generator = new Random();
		return generator.nextInt(16) + 85;
	}
	
	private static long stabDetermine(int type1, int type2, int move) {
		long stab = 1;
		if(move == type1 || move == type2) {
			stab = (long) 1.5;
		}
		return stab;
	}

	private static int calcMod1(Pokemon attacker, Pokemon defender, Move move, BattleStats battle) {
		/*Mod1 = BRN × RL × TVT × SR × FF*/
		long calcMod = 1;
		long brn = 1;
		long rl = 1;
		long sr = 1;
		long ff = 1;
		
		if(attacker.getMajorStatus() ==  STATUS_BURNED) {
			brn = (long) 0.5;
		}
		if(defender.getShield1() == move.getKind() || defender.getShield2() == move.getKind()) {
			rl = (long) 0.5;
		}
		if(battle.getWeather() != BattleStats.NO_WEATHER) {
			switch(battle.getWeather()) {
			case BattleStats.SUNNY_DAY :
				if(move.getType() == FIRE) {
					sr = (long) 1.5;
				}
				if(move.getType() == WATER) {
					sr = (long) 0.5;
				}
				break;
			case BattleStats.RAIN_DANCE :
				if(move.getType() == WATER) {
					sr = (long) 1.5;
				}
				if(move.getType() == FIRE) {
					sr = (long) 0.5;
				}
				break;
			}
		}
		if(attacker.getBuffs() == 10 /* TODO Flash fire*/) {
			ff = (long) 1.5;
		}
		
		calcMod = brn * rl * sr * ff;
		return (int) calcMod;
	}
	
	private static int calcMod2(Pokemon attacker, Move move) {
		long mod2 = 1;
		if(attacker.getHeldItem() == Items.LIFE_ORB) {
			mod2 = (long) ((long) mod2 * 1.5);
		}
		if(attacker.getHeldItem() == Items.METRONOME) {
			//TODO fuck if i know
		}
		if(move.getMoveId() == Move.ME_FIRST) {
			mod2 = (long) ((long) mod2 * 1.5);
		}
		return (int) mod2;
	}
	
	private static long calcMod3(Pokemon attacker, Pokemon defender, long effectiveness) {
		long mod3 = 1;
		long srf = 1;
		long eb = 1;
		long tl = 1;
		long trb = 1;
		//Mod3 = SRF × EB × TL × TRB
		if(defender.getAbility() == Abilities.SOLID_ROCK || defender.getAbility() == Abilities.FILTER) {
			srf = (long) 0.75;
		}
		if(attacker.getHeldItem() == Items.EXPERT_BELT && (effectiveness >= 2)) {
			eb = (long) 1.2;
		}
		if(effectiveness < 1) { 
			tl = 2;
		}
		if(defender.getHeldItem() <= 200 && defender.getHeldItem() >= 217) {
			//TODO method to determine super effectiveness from type & berry held, if matches...
			trb = (long) 0.5;
		}
		mod3 = srf * eb * tl * trb;
		return mod3;
	}

	private static int calcCritHit(Pokemon attacker, Pokemon defender) {
		int critMult = 1;
		if(defender.getAbility() == 50 /*50 is arbitrary, ability int for Battle Armor*/) {
			return critMult;
		}
		Random generator = new Random();
		long ran = (long) generator.nextFloat() * 100;
		if(ran < 6.25) {
			critMult = 2;
			if(attacker.getAbility() == 100 /*100 is arbitrary, int for ability Sniper*/) {
				critMult = 3;
			}
		}
		return critMult;
	}
	
}
