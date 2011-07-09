package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.ModdableBattleStats;
import com.pokemon.mmo.Enums.NonVolatileStatusAilment;
import com.pokemon.mmo.Enums.Stats;
import com.pokemon.mmo.Enums.VolatileEffectBatonPass;

public class MoveExecutionThread {

	/******************************************/
	/********     Member Variables     ********/
	/******************************************/
	
	private BattlePlayer mAttacker;
	private BattlePlayer mTarget;
	private Pokemon mAttackerPokemon;
	private Pokemon mTargetPokemon;
	private Battle mBattle;
	private Move mMove;
	private int mDamageDealt;
	private RandomForPokemon mGenerator = new RandomForPokemon();
	
	/**************************************/
	/********     Constructors     ********/
	/**************************************/
	
	public MoveExecutionThread(BattlePlayer attacker, BattlePlayer target, Move move, Battle battle) {
		this.mAttacker = attacker;
		this.mTarget = target;
		this.mMove = move;
		this.mBattle = battle;
		
		this.mAttackerPokemon = mAttacker.getPokemon();
		this.mTargetPokemon = mTarget.getPokemon();
	}
	
	/***********************************/
	/********     Accessors     ********/
	/***********************************/
	
	public BattlePlayer getAttacker() {
		return mAttacker;
	}
	
	public BattlePlayer getDefender() {
		return mTarget;
	}
	
	/***********************************/
	/********   Public Methods   *******/
	/***********************************/
	

	public void standardMove() {
		dealDamage(accuracyCheck());
	}
	
	public void changeStatsOnly() {
		switch(mMove.getMoveTarget()) {
		case USER :
			applyAttackerStatChanges();
			break;
		case SELECTED_POKEMON :
		case ALL_OPPONENTS : //TODO same as selected for now, but in double battles this could change
			if(accuracyCheck()) {
				applyTargetStatChanges();
			}
			else {
				System.out.println(mAttackerPokemon.getNickName() + "'s attack missed!");
			}
			break;
		}
	}
	
	public void multiHit() {
		int hits = mMove.getMinHits();
		int max = mMove.getMaxHits();
		int var = max - hits;
		
		hits += mGenerator.nextInt(var) + 1;
		
		for (int i = 0; i < hits; i++) {
			if(mMove.getMoveId() == 78) {
				dealDamage(accuracyCheck());
				ailmentOnly();
			}
			else {
				dealDamage(accuracyCheck()); //TODO check if moves like fury swipes calc accuracy for each hit
			}
		}
	}
	
	public void ailmentOnly() {
		if(accuracyCheck()) {
			applyTargetNonVolatileStatusAilments();
			applyTargetVolatileStatusAilments();
		}
		else {
			System.out.println(mAttackerPokemon.getNickName() + "'s attack missed!");
		}
	}
	
	public void healUser() {
		switch(mMove.getMoveEffect()) {
		case 33 :
			healFormula(50);
			break;
		case 133 :
			switch(mMove.getMoveId()) {
			case 234 :
			case 235 :
				switch(mBattle.getWeather()) {
				case SUNNY_DAY :
					//TODO get the rest, not sure what makes it stronger or weaker
					break;
				}
			}
			break;
		case 215 :
			healFormula(50);
			//TODO flying pokemon sits down
			break;
		case 310 :
			healPulse();
			//TODO targeting works differently in double battle, but i think that is handled in battle not here
			break;
		case 163 :
			//TODO get amount swallowed and calc accordingly
			break;
		}
	}
	
	public void damageAndAbsorb() {
		dealDamage(accuracyCheck());
		absorb();
	}
	
	public void inflictDamageAndStatusAilment() {
		dealDamage(accuracyCheck());
		if(mTargetPokemon.getStatus() != NonVolatileStatusAilment.FAINTED) {
			secondaryEffect();
		}
	}
	
	public void inflictDamageAndChangeUserStats() {
		dealDamage(accuracyCheck());
		if(mTargetPokemon.getStatus() != NonVolatileStatusAilment.FAINTED) {
			applyAttackerStatChanges();
		}
	}
	
	public void inflictDamageAndChangeTargetStats() {
		dealDamage(accuracyCheck());
		if(mTargetPokemon.getStatus() != NonVolatileStatusAilment.FAINTED) {
			applyTargetStatChanges();
		}
	}
	
	public void swaggerAndFlatter() {
		applyTargetVolatileStatusAilments();
		applyTargetStatChanges();
	}
	
	/***********************************/
	/*******   Private Methods   *******/
	/***********************************/	
	
	private void dealDamage(boolean bool) {
		if(!bool) {
			System.out.println(mAttackerPokemon.getNickName() + "'s attack missed!");
			if(mMove.getMoveId() == 46) {
				int attackerHp = mAttackerPokemon.getCurrentHP();
				int d = GameFields.damageCalc(mAttacker, mTarget, mMove, mBattle);
				int total = attackerHp - d;
				if(total > 0) {
					mAttackerPokemon.setCurrentHP(total);
				}
				else {
					mAttackerPokemon.setCurrentHP(0);
					mAttackerPokemon.setStatus(NonVolatileStatusAilment.FAINTED);
				}
			}
		}
		else {
			int targetHp = mTargetPokemon.getCurrentHP();
			int attackerHp = mAttackerPokemon.getCurrentHP();
			mDamageDealt = GameFields.damageCalc(mAttacker, mTarget, mMove, mBattle);
			if(mDamageDealt == 0) {
				return;
			}
			targetHp = targetHp - mDamageDealt;
			System.out.println(mTargetPokemon.getNickName() + " takes " + mDamageDealt + " damage!");
			
			if(targetHp > 0) {
				mTargetPokemon.setCurrentHP(targetHp);
				angerPointCheck();
			}
			else {
				mTargetPokemon.setCurrentHP(0);
				mTargetPokemon.setStatus(NonVolatileStatusAilment.FAINTED);
				System.out.println(mTargetPokemon.getNickName() + " fainted!");
			}
			
			int recoil = mMove.getRecoilPercentage();
			if(recoil < 0) { //TODO change this to below 0, absorb handled differently... or do a switch or something which handles items like bigroot
				int recoilDamage = (mDamageDealt * recoil) / 100;
				attackerHp = attackerHp + recoilDamage;
				mAttackerPokemon.setCurrentHP(attackerHp);
				System.out.println(mAttackerPokemon.getNickName() + " takes " + (recoilDamage * -1) + " in recoil.");
			}
			applyAttackerStatusAilments();
		}
	}
	
	private void angerPointCheck() {
		if(mTarget.tookACrit() && mTargetPokemon.hasAbility(Ability.ANGER_POINT)) {
			mTargetPokemon.setStatStageChange(ModdableBattleStats.ATTACK, 6);
		}
	}

	private void applyAttackerStatChanges() {
		int chance = mMove.getSecondaryStatChangeChance();
		int gen = mGenerator.nextInt(100) + 1;
		if(gen <= chance) {
			int[] statChanges = mMove.getMoveStatChanges();
			mAttackerPokemon.addStatChangeArray(statChanges);
		}
		//TODO output saying what has changed
	}
	
	private void applyTargetStatChanges() {
		int chance = mMove.getSecondaryStatChangeChance();
		int gen = mGenerator.nextInt(100) + 1;
		if(gen <= chance) {
			int[] statChanges = mMove.getMoveStatChanges();
			mTargetPokemon.addStatChangeArray(statChanges);
		}
		//TODO output saying what has changed
	}
	
	private void applyAttackerStatusAilments() {
		//TODO outrage, thrash... i think
	}
	
	private void applyTargetNonVolatileStatusAilments() {
		if(mTargetPokemon.isAffectedByStatusAilment()) {
			return;
		}
		int chance = mMove.getSecondaryAilmentChance();
		//TODO enhance chance of secondary effect here with abilities/items etc
		int i = mGenerator.nextInt(100) + 1;
		if(i <= chance) {
			switch(mMove.getStatusAilment()) {
			case POISON :
				mTargetPokemon.setStatus(NonVolatileStatusAilment.POISON);
				break;
			case TOXIC :
				mTargetPokemon.setStatus(NonVolatileStatusAilment.TOXIC);
				break;
			case PARALYSIS :
				mTargetPokemon.setStatus(NonVolatileStatusAilment.PARALYZE);
				break;
			case FREEZE :
				mTargetPokemon.setStatus(NonVolatileStatusAilment.FREEZE);
				break;
			case BURN :
				mTargetPokemon.setStatus(NonVolatileStatusAilment.BURN);
				break;
			default :
				//TODO blank?
			}
			System.out.println(mTargetPokemon.getNickName() + " is " + mMove.getStatusAilment());
		}
	}
	
	private void applyTargetVolatileStatusAilments() {
		int chance = mMove.getSecondaryAilmentChance();
		//TODO enhance chance of secondary effect here with abilities/items etc
		int i = mGenerator.nextInt(100) + 1;
		if(i <= chance) {
			switch(mMove.getStatusAilment()) {
			case CONFUSION :
				mTargetPokemon.setBatonVolatileAilment(VolatileEffectBatonPass.CONFUSION, true);
				break;
			case LEECH_SEED :
				mTargetPokemon.setBatonVolatileAilment(VolatileEffectBatonPass.LEECH_SEED, true);
				break;
			case PERISH_SONG :
				mTargetPokemon.setBatonVolatileAilment(VolatileEffectBatonPass.PERISH_SONG, true);
				break;
			case EMBARGO :
				mTargetPokemon.setBatonVolatileAilment(VolatileEffectBatonPass.EMBARGO, true);
				break;
			case HEAL_BLOCK :
				mTargetPokemon.setBatonVolatileAilment(VolatileEffectBatonPass.HEAL_BLOCK, true);
				break;
			case RISE :
				mTargetPokemon.setBatonVolatileAilment(VolatileEffectBatonPass.RISE, true);
				break;
			default :
				//TODO nothing goes here, dunno if i need to do anything even
				break;
			}
			System.out.println(mTargetPokemon.getNickName() + " has " + mMove.getStatusAilment());
		}
		//TODO SET TIMERS FOR ABOVE VOLATILE EFFECTS!
	}
	
	private boolean accuracyCheck() {
		/**Probability = MoveAccuracy * (AttackerAccuracy/TargetEvasion)*/
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
	
	private void secondaryEffect() {
		int prob = mMove.getSecondaryAilmentChance();
		int gen = mGenerator.nextInt(100) + 1;
		if(gen <= prob) {
			applyTargetNonVolatileStatusAilments();
			applyTargetVolatileStatusAilments();
		}
	}
	
	private void healFormula(int healPercentage) {
		int denominator = 100 / healPercentage;
		
		int totalHp = mAttackerPokemon.getStat(Stats.HP);
		int healAmount = totalHp / denominator;
		mAttackerPokemon.setCurrentHP(mAttackerPokemon.getCurrentHP() + healAmount);
		if(mAttackerPokemon.getCurrentHP() > totalHp){
			mAttackerPokemon.setCurrentHP(totalHp);
		}
		System.out.println(mAttackerPokemon.getNickName() + " recovers health!");
	}
	
	private void healPulse() {
		int totalHp = mTargetPokemon.getStat(Stats.HP);
		int healAmount = totalHp / 2;
		mTargetPokemon.setCurrentHP(mTargetPokemon.getCurrentHP() + healAmount);
		if(mTargetPokemon.getCurrentHP() > totalHp){
			mTargetPokemon.setCurrentHP(totalHp);
		}
		System.out.println(mAttackerPokemon.getNickName() + " restores " + mTargetPokemon.getNickName() + "'s health!");
	}
	
	private void absorb() {
		//TODO big root, etc
		int restore = mDamageDealt / 2;
		int newTotal = mAttackerPokemon.getCurrentHP() + restore;
		if(newTotal > mAttackerPokemon.getStat(Stats.HP)) {
			mAttackerPokemon.setCurrentHP(mAttackerPokemon.getStat(Stats.HP));
		}
		else {
			mAttackerPokemon.setCurrentHP(newTotal);
		}
		System.out.println(mAttackerPokemon.getNickName() + " drains" + restore + " HP from " + mTargetPokemon.getNickName());
	}
	
}
