package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Status;

public class MoveExecutionThread {

	private BattlePlayer mAttacker;
	private BattlePlayer mTarget;
	
	private Battle mBattle;
	
	private Move mMove;
	
	public MoveExecutionThread(BattlePlayer attacker, BattlePlayer target, Move move, Battle battle) {
		this.mAttacker = attacker;
		this.mTarget = target;
		this.mMove = move;
		this.mBattle = battle;
	}
	
	public void dealDamage() {
		int targetHp = mTarget.getPokemon().getCurrentHP();
		int damage = GameFields.damageCalc(mAttacker.getPokemon(), mTarget.getPokemon(), mMove, mBattle); //TODO consider 
		//putting battleplayer as param here... all refs go in via battleplayer.get______ to keep consistent
		targetHp = targetHp - damage;
		if(targetHp > 0) {
			mTarget.getPokemon().setCurrentHP(targetHp);
		}
		else {
			mTarget.getPokemon().setCurrentHP(0);
			mTarget.getPokemon().setStatus(Status.FAINTED);
			applyAttackerStatChanges();
			applyAttackerStatusAilments();
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
	
}
