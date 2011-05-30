package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Moves;

public class Pursuit extends Move {

	public Pursuit(Moves move) {
		super(move);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getPriority(Move move) {
		switch (move.getMove()) {
		case SWITCH:
		case U - TURN:
		case VOLT_SWITCH:
		case BATON_PASS:
			return 7;
			break;
		default:
			super.getPriority(move);
			break;
		}
	}

}
