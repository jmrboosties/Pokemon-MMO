package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Moves;

public class MoveFactory {
	
	public static Move getMove(Moves moveEnum) {
		Move move = new Move(moveEnum);
		return move;
	}

}
