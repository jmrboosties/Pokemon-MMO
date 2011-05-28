package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Abilities;
import com.pokemon.mmo.Enums.Genders;
import com.pokemon.mmo.Enums.Moves;

public class MySquirtle extends Pokemon {

	public MySquirtle(PokemonSpecies species) {
		super(species);
		// TODO Auto-generated constructor stub
	}
	
	public static final String NICKNAME = "Mr. Turtle";
	public static final int DEX_NUMBER = 7;
	
	public static final int LEVEL = 10;
	public static final Genders GENDER = Genders.MALE;
	public static final Abilities ABILITY = Abilities.TORRENT;
	
	public static final int HP_IV = 20;
	public static final int ATTACK_IV = 10;
	public static final int DEFENSE_IV = 27;
	public static final int SPECIAL_ATTACK_IV = 20;
	public static final int SPECIAL_DEFENSE_IV = 25;
	public static final int SPEED_IV = 11;
	
	public static final int HP_EVS = 0;
	public static final int ATTACK_EVS = 0;
	public static final int DEFENSE_EVS = 0;
	public static final int SPECIAL_ATTACK_EVS = 0;
	public static final int SPECIAL_DEFENSE_EVS = 0;
	public static final int SPEED_EVS = 0;
	
	public static final Moves SLOT_1 = Moves.TACKLE;

}
