package com.pokemon.mmo;

import java.util.Random;

import com.pokemon.mmo.Enums.NonVolatileStatusAilment;

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
	
	public void dealDamage(boolean bool) {
		if(!bool) {
			System.out.println(mAttacker.getPokemon().getNickName() + "'s attack missed!");
		}
		else {
			int targetHp = mTarget.getPokemon().getCurrentHP();
			int attackerHp = mAttacker.getPokemon().getCurrentHP();
			System.out.println(mAttacker.getPokemon().getNickName() + " uses " + mMove.getMoveName() + "!");
			int damage = GameFields.damageCalc(mAttacker, mTarget, mMove, mBattle); 
			targetHp = targetHp - damage;
			System.out.println(mTarget.getPokemon().getNickName() + " takes " + damage);
			applyAttackerStatChanges();
			applyAttackerStatusAilments();
			if(targetHp > 0) {
				mTarget.getPokemon().setCurrentHP(targetHp);
			}
			else {
				mTarget.getPokemon().setCurrentHP(0);
				mTarget.getPokemon().setStatus(NonVolatileStatusAilment.FAINTED);
				System.out.println(mTarget.getPokemon().getNickName() + " fainted!");
				return;
			}
			applyTargetStatChanges();
			applyTargetStatusAilments();
			
			int recoil = mMove.getRecoilPercentage();
			if(recoil != 0) {
				int recoilDamage = (damage * recoil) / 100;
				attackerHp = attackerHp + recoilDamage;
			}
		}
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
	
	private boolean accuracyCheck() {
		//Probability = MoveAccuracy * (AttackerAccuracy/TargetEvasion)
		int prob = 100;
		prob = mMove.getAccuracy() * (mAttacker.getPokemon().getAccuracy() / mTarget.getPokemon().getAccuracy());
		int gen = mGenerator.nextInt(101);
		if(gen <= prob) {
			return true;
		}
		else {
			return false;
		}
	}

	public void standardMove() {
		dealDamage(accuracyCheck());
	}
	
	public void multiHit() {
		int hits = mMove.getMinHits();
		int max = mMove.getMaxHits();
		int var = max - hits;
		
		hits += mGenerator.nextInt(var) + 1;
		
		for (int i = 0; i < hits; i++) {
			dealDamage(accuracyCheck());
		}
	}
	
}
