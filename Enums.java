package com.pokemon.mmo;

public class Enums {

	public static enum Types {
		NONE(-1), NORMAL(0), FIRE(1), WATER(2), ELECTRIC(3), GRASS(4), ICE(5),
		FIGHTING(6), POISON(7), GROUND(8), FLYING(9), PSYCHIC(10), BUG(11),
		ROCK(12), GHOST(13), DRAGON(14), DARK(15), STEEL(16);

		public final int id;

		private Types(int id) {
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
	
	public static enum MoveEffectId {
		DAMAGE, AILMENT, NET_GOOD_STATS, HEAL, DAMAGE_WITH_AILMENT, SWAGGER, DAMAGE_AND_RAISE, DAMAGE_AND_LOWER,
		DAMAGE_AND_HEAL, OHKO, WHOLE_FIELD_EFFECT, FIELD_EFFECT, FORCE_SWITCH, UNIQUE
	}
	
	public static enum MoveTargetId {
		SPECIFIC_TO_MOVE(1), SELECTED(2), ALLY(3), USERS_FIELD(4), USER_OR_ALLY(5), OPPONENTS_FIELD(6), USER(7), RANDOM_OPPONENT(8),
		ALL_OTHER_POKEMON(9), SELECTED_POKEMON(10), ALL_OPPONENTS(11), ENTIRE_FIELD(12);
		
		public final int id;
		
		private MoveTargetId(int i) {
			this.id = i;
		}
	}
	
}
