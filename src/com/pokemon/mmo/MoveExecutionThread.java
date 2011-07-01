package com.pokemon.mmo;

import java.util.Random;

import com.pokemon.mmo.Enums.Status;

public class MoveExecutionThread {

	private BattlePlayer mAttacker;
	private BattlePlayer mTarget;
	
	private Battle mBattle;
	
	private Move mMove;
	
	private Random mGenerator;
	
	public MoveExecutionThread(BattlePlayer attacker, BattlePlayer target, Move move, Battle battle) {
		this.mAttacker = attacker;
		this.mTarget = target;
		this.mMove = move;
		this.mBattle = battle;
	}
	
	public BattlePlayer getAttacker() {
		return mAttacker;
	}
	
	public BattlePlayer getDefender() {
		return mTarget;
	}
	
	public void dealDamage() {
		int targetHp = mTarget.getPokemon().getCurrentHP();
		int damage = GameFields.damageCalc(mAttacker.getPokemon(), mTarget.getPokemon(), mMove, mBattle); //TODO consider 
		//putting battleplayer as param here... all refs go in via battleplayer.get______ to keep consistent
		targetHp = targetHp - damage;
		applyAttackerStatChanges();
		applyAttackerStatusAilments();
		if(targetHp > 0) {
			mTarget.getPokemon().setCurrentHP(targetHp);
		}
		else {
			mTarget.getPokemon().setCurrentHP(0);
			mTarget.getPokemon().setStatus(Status.FAINTED);
			return;
		}
		applyTargetStatChanges();
		applyTargetStatusAilments();
	}
	
	private void applyAttackerStatChanges() {
		//TODO fill this in with the stats and stuff
	}
	
	private void applyTargetStatChanges() {
		//TODO fill
	}
	
	private void applyAttackerStatusAilments() {
		//TODO fill, this is for moves which confuse user after they do it
	}
	
	private void applyTargetStatusAilments() {
		//TODO fill
	}
	
	public void multiHit() {
		int hits = mMove.getMinHits();
		int max = mMove.getMaxHits();
		int var = max - hits;
		
		hits += mGenerator.nextInt(var) + 1;
		
		for (int i = 0; i < hits; i++) {
			dealDamage();
		}
	}
	
}
