package com.pokemon.mmo;

import java.util.Random;

public class RandomForPokemon extends Random {

	private static final long serialVersionUID = 172116441717210904L;

	public boolean randomBoolean(int odds) {
		int base = nextInt(100) + 1;
		if (odds < base) {
			return true;
		} else {
			return false;
		}
	}
}
