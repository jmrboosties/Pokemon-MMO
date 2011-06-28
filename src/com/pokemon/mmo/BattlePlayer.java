package com.pokemon.mmo;

import java.util.ArrayList;

public class BattlePlayer {

	private Trainer mTrainer;
	private Pokemon mPokemon;
	private Move mCurrentChosenMove;
	
	private ArrayList<Move> mPreviousMoves;
	
	private int[] mBattleStatBuffs = {0,0,0,0,0,0,0};
	private boolean[] mOnFieldBuffs; //do length here with all false
	
	public BattlePlayer() {
		
	}
	
	public void setTrainer(Trainer trainer) {
		this.mTrainer = trainer;
	}
	
	public Trainer getTrainer() {
		return mTrainer;
	}
	
	public void setPokemon(Pokemon pokemon) {
		this.mPokemon = pokemon;
	}
	
	public Pokemon getPokemon() {
		return mPokemon;
	}
	
	public void setCurrentChosenMove(Move move) {
		this.mCurrentChosenMove = move;
	}
	
	public Move getCurrentChosenMove() {
		return mCurrentChosenMove;
	}
	
}
