package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Abilities;
import com.pokemon.mmo.Enums.Genders;
import com.pokemon.mmo.Enums.Moves;

public class MyCharmander extends Pokemon {

	public MyCharmander(PokemonSpecies species) {
		super(species);
	}

	public static final int DEX_NUMBER = 4;

	public static final int LEVEL = 12;
	public static final Genders GENDER = Genders.MALE;
	public static final Abilities ABILITY = Abilities.BLAZE;

	public static final int HP_IV = 25;
	public static final int ATTACK_IV = 10;
	public static final int DEFENSE_IV = 27;
	public static final int SPECIAL_ATTACK_IV = 31;
	public static final int SPECIAL_DEFENSE_IV = 20;
	public static final int SPEED_IV = 17;

	public static final int HP_EVS = 0;
	public static final int ATTACK_EVS = 0;
	public static final int DEFENSE_EVS = 0;
	public static final int SPECIAL_ATTACK_EVS = 0;
	public static final int SPECIAL_DEFENSE_EVS = 0;
	public static final int SPEED_EVS = 0;

	public static final Moves SLOT_1 = Moves.SCRATCH;
	public static final Moves SLOT_2 = Moves.EMBER;

}
