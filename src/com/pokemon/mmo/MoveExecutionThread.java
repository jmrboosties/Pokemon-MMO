package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.ModdableBattleStats;
import com.pokemon.mmo.Enums.NonVolatileStatusAilment;

public class MoveExecutionThread {

	private BattlePlayer mAttacker;
	private BattlePlayer mTarget;
	
	private Pokemon mAttackerPokemon;
	private Pokemon mTargetPokemon;
	
	private Battle mBattle;
	
	private Move mMove;
	
	private RandomForPokemon mGenerator = new RandomForPokemon();
	
	public MoveExecutionThread(BattlePlayer attacker, BattlePlayer target, Move move, Battle battle) {
		this.mAttacker = attacker;
		this.mTarget = target;
		this.mMove = move;
		this.mBattle = battle;
		
		this.mAttackerPokemon = mAttacker.getPokemon();
		this.mTargetPokemon = mTarget.getPokemon();
	}
	
	public BattlePlayer getAttacker() {
		return mAttacker;
	}
	
	public BattlePlayer getDefender() {
		return mTarget;
	}
	
	public void dealDamage(boolean bool) {
		if(!bool) {
			System.out.println(mAttackerPokemon.getNickName() + "'s attack missed!");
		}
		else {
			int targetHp = mTargetPokemon.getCurrentHP();
			int attackerHp = mAttackerPokemon.getCurrentHP();
			System.out.println(mAttackerPokemon.getNickName() + " uses " + mMove.getMoveName() + "!");
			int damage = GameFields.damageCalc(mAttacker, mTarget, mMove, mBattle); 
			targetHp = targetHp - damage;
			System.out.println(mTargetPokemon.getNickName() + " takes " + damage + " damage!");
//			applyAttackerStatChanges(); TODO this may move to a diff method
//			applyAttackerStatusAilments();
			if(targetHp > 0) {
				mTargetPokemon.setCurrentHP(targetHp);
				angerPointCheck();
			}
			else {
				mTargetPokemon.setCurrentHP(0);
				mTargetPokemon.setStatus(NonVolatileStatusAilment.FAINTED);
				System.out.println(mTargetPokemon.getNickName() + " fainted!");
				return;
			}
//			applyTargetStatChanges(); TODO this may move to a diff method
//			applyTargetStatusAilments();
			
			int recoil = mMove.getRecoilPercentage();
			if(recoil != 0) { //TODO change this to below 0, absorb handled differently... or do a switch or something which handles items like bigroot
				int recoilDamage = (damage * recoil) / 100;
				attackerHp = attackerHp + recoilDamage;
				mAttackerPokemon.setCurrentHP(attackerHp);
			}
		}
	}
	
	private void angerPointCheck() {
		if(mTarget.tookACrit() && mTargetPokemon.hasAbility(Ability.ANGER_POINT)) {
			mTargetPokemon.setStatStageChange(ModdableBattleStats.ATTACK, 6);
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
