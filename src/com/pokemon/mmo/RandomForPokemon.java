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
	
	public Pokemon randomPokemon(int level) {
		int i = nextInt(649) + 1;
		return PokemonFactory.getPokemonAtLevel(Main.mSpeciesArray[i], level);
	}
	
	public Move getRandomMove(Pokemon pokemon) {
		int numOfMoves = 0;
		Move[] moves = pokemon.getMoveArray();
		for (int i = 0; i < moves.length; i++) {
			if(moves[i] != Main.mMoveArray[0]) {
				numOfMoves++;
			}
		}
		Move slot = moves[nextInt(numOfMoves)];
		return slot;
	}
}
