package com.pokemon.mmo;

import java.util.ArrayList;

public class BattlePlayer {

	private Trainer mTrainer;
	private Pokemon mPokemon;
	private Move mCurrentChosenMove;
	
	private boolean mGastroAcid = false; //TODO add this to field? dont think so
	
	private boolean mTookACrit = false;
	
	private ArrayList<Move> mPreviousMoves;
	private boolean[] mOnFieldBuffs; //do length here with all false
	
	public BattlePlayer(Trainer trainer) {
		this.mTrainer = trainer;
		mPokemon = mTrainer.getLeadingPokemon();
		mPokemon.initBattleAbility();
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
	
	public boolean hasReflect() {
		//TODO THIS IS PLACEHOLDER WILL DELETE
		return false;
	}
	
	public boolean hasLightScreen() {
		//TODO SAME AS ABOVE
		return false;
	}
	
	public boolean hasFlashFire() {
		//TODO ANOTHER PLACEHOLDER
		return false;
	}

	public void setGastroAcid(boolean gastroAcid) {
		this.mGastroAcid = gastroAcid;
	}

	public boolean isGastroAcid() {
		return mGastroAcid;
	}

	public void setTookACrit(boolean tookACrit) {
		this.mTookACrit = tookACrit;
	}

	public boolean tookACrit() {
		return mTookACrit;
	}
	
}
