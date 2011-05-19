package com.pokemon.mmo;

public class Enums {

	public static enum Types {
		NONE, NORMAL, FIRE, WATER, ELECTRIC, GRASS, ICE,
		FIGHTING, POISON, GROUND, FLYING, PSYCHIC, BUG,
		ROCK, GHOST, DRAGON, DARK, STEEL;
	}
	
	public static enum MoveKinds {
		PHYSICAL, SPECIAL, STATUS
	}
	
	public static enum Genders {
		GENDERLESS, MALE, FEMALE
	}
	
	public static enum Moves {
		SCRATCH, EMBER, TACKLE, CHARGE, ME_FIRST, EMPTY
	}
	
	public static enum Abilities {
		SOLID_ROCK, FILTER, UNAWARE, RIVALRY, RECKLESS, IRON_FIST, TORRENT, BLAZE, OVERGROW, SWARM, TECHNICIAN, 
		THICK_FAT, HEATPROOF, DRY_SKIN, PURE_POWER, HUGE_POWER, FLOWER_GIFT, GUTS, HUSTLE, SLOW_START, SOLAR_POWER, 
		BATTLE_ARMOR, SNIPER
	}
	
	public static enum Stats {
		HP, ATTACK, DEFENSE, SPECIAL_ATTACK, SPECIAL_DEFENSE, SPEED
	}
	
	public static enum Weather {
		NORMAL, SUNNY_DAY, RAIN_DANCE, SANDSTORM, HAIL
	}
	
	public static enum Status {
		NONE, POISON, TOXIC, BURN, PARALYZE, FREEZE, SLEEP, FAINTED
	}
	
}
