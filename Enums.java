package com.pokemon.mmo;

public class Enums {

	public static enum Types {
		NONE(-1), NORMAL(0), FIRE(1), WATER(2), ELECTRIC(3), GRASS(4), ICE(5),
		FIGHTING(6), POISON(7), GROUND(8), FLYING(9), PSYCHIC(10), BUG(11),
		ROCK(12), GHOST(13), DRAGON(14), DARK(15), STEEL(16);

		public final int id;

		private Type(int id) {
			this.id = id;
		}
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
