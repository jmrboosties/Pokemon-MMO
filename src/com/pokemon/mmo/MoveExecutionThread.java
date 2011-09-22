package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.Gender;
import com.pokemon.mmo.Enums.ModdableBattleStats;
import com.pokemon.mmo.Enums.MoveFlag;
import com.pokemon.mmo.Enums.NonVolatileStatusAilment;
import com.pokemon.mmo.Enums.OneSideFieldEffect;
import com.pokemon.mmo.Enums.Stats;
import com.pokemon.mmo.Enums.VolatileEffectBatonPass;
import com.pokemon.mmo.Enums.VolatileEffectNoBatonPass;

public class MoveExecutionThread {

	/******************************************/
	/********     Member Variables     ********/
	/******************************************/
	
	private BattlingPokemon mAttacker;
	private BattlingPokemon mTarget;
	private Pokemon mAttackerPokemon;
	private Pokemon mTargetPokemon;
	private Battle mBattle;
	private Move mMove;
	private int mDamageDealt;
	private RandomForPokemon mGenerator = new RandomForPokemon();
	
	/**************************************/
	/********     Constructors     ********/
	/**************************************/
	
	public MoveExecutionThread(BattlingPokemon attacker, BattlingPokemon target, Move move, Battle battle) {
		this.mAttacker = attacker;
		this.mTarget = target;
		this.mMove = move;
		this.mBattle = battle;
		
		this.mAttackerPokemon = mAttacker.getPokemon();
		this.mTargetPokemon = mTarget.getPokemon();
	}
	
	/***********************************/
	/********   Public Methods   *******/
	/***********************************/
	
	//TODO PROTECT AND DETECT PRIVATE METHOD INSERTED INTO PUBLIC

	public void standardMove() {
		if(targetIsProtected()) {
			return;
		}
		dealDamage(accuracyCheck());
	}
	
	public void changeStatsOnly() {
		switch(mMove.getMoveTarget()) {
		case USER :
			applyAttackerStatChanges();
			break;
		case SELECTED_POKEMON :
		case ALL_OPPONENTS : //TODO same as selected for now, but in double battles this could change
			if(targetIsProtected()) {
				return;
			}
			if(accuracyCheck()) {
				applyTargetStatChanges();
			}
			else {
				System.out.println(mAttacker.getNickname() + "'s attack missed!");
			}
			break;
		}
	}
	
	public void multiHit() {
		if(targetIsProtected()) {
			return;
		}
		int hits = mMove.getMinHits();
		int max = mMove.getMaxHits();
		int var = max - hits;
		
		if(var != 0) {
			hits += mGenerator.nextInt(var) + 1;
		}
		
		boolean bool = accuracyCheck();
		if(!bool) {
			System.out.println(mAttacker.getNickname() + "'s attack missed!");
			return;
		}
		for (int i = 0; i < hits; i++) {
			if(mMove.getMoveId() == 78) {
				dealDamage(bool);
				ailmentOnly();
			}
			else {
				dealDamage(bool);
			}
		}
	}
	
	public void ailmentOnly() {
		if(targetIsProtected()) {
			return;
		}
		if(accuracyCheck()) {
			applyTargetNonVolatileStatusAilments();
			applyTargetVolatileStatusAilments();
			System.out.println(mTarget.getNickname() + " has " + mMove.getStatusAilment().toString());
		}
		else {
			System.out.println(mAttacker.getNickname() + "'s attack missed!");
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
		if(targetIsProtected()) {
			return;
		}
		dealDamage(accuracyCheck());
		absorb();
	}
	
	public void inflictDamageAndStatusAilment() {
		if(targetIsProtected()) {
			return;
		}
		dealDamage(accuracyCheck());
		if(mTarget.getStatus() != NonVolatileStatusAilment.FAINTED) {
			secondaryEffect();
		}
	}
	
	public void inflictDamageAndChangeUserStats() {
		if(targetIsProtected()) {
			return;
		}
		dealDamage(accuracyCheck());
		if(mTarget.getStatus() != NonVolatileStatusAilment.FAINTED) {
			applyAttackerStatChanges();
		}
	}
	
	public void inflictDamageAndChangeTargetStats() {
		if(targetIsProtected()) {
			return;
		}
		boolean b = accuracyCheck();
		if(b) {
			dealDamage(b);
			if(mTarget.getStatus() != NonVolatileStatusAilment.FAINTED) {
				applyTargetStatChanges();
			}
		}
	}
	
	public void swaggerAndFlatter() {
		if(targetIsProtected()) {
			return;
		}
		applyTargetVolatileStatusAilments();
		applyTargetStatChanges();
	}
	
	public void onehitKO() {
		if(targetIsProtected()) {
			return;
		}
		ohko();
	}
	
	public void forceSwitchNoDamage() {
		forceSwitch();
	}
	
	public void damageAndForceSwitch() {
		dealDamage(accuracyCheck());
		forceSwitch();
	}
	
	public void getFullFieldEffect() {
		fieldEffect();
	}
	
	public void getOneSideFieldEffect() {
		determineOneSideEffect();
	}
	
	public void dealConfusion() {
		doConfusion();
	}
	
	/***********************************/
	/*******   Private Methods   *******/
	/***********************************/	
	
	private void dealDamage(boolean bool) {
		if(!bool) {
			System.out.println(mAttacker.getNickname() + "'s attack missed!");
			if(mMove.getMoveEffectId() == 46) {
				System.out.println(mAttacker.getNickname() + " kept going and crashed!");
				int attackerHp = mAttacker.getCurrentHP();
				int total = attackerHp / 2;
				System.out.println(mAttacker.getNickname() + " takes " + total + " damage!");
				if(total > 0) {
					mAttacker.setCurrentHP(total);
				}
				else {
					mAttacker.setCurrentHP(0);
					mAttacker.setStatus(NonVolatileStatusAilment.FAINTED);
				}
			}
		}
		else {
			int targetHp = mTarget.getCurrentHP();
			int attackerHp = mAttacker.getCurrentHP();
			mDamageDealt = GameFields.damageCalc(mAttacker, mTarget, mMove, mBattle);
			if(mDamageDealt == 0) {
				return;
			}
			targetHp = targetHp - mDamageDealt;
			System.out.println(mTarget.getNickname() + " takes " + mDamageDealt + " damage!");
			
			if(targetHp > 0) {
				mTarget.setCurrentHP(targetHp);
				angerPointCheck();
			}
			else {
				mTarget.setCurrentHP(0);
				mTarget.setStatus(NonVolatileStatusAilment.FAINTED);
				System.out.println(mTarget.getNickname() + " fainted!");
			}
			
			int recoil = mMove.getRecoilPercentage();
			if(recoil < 0) { //TODO change this to below 0, absorb handled differently... or do a switch or something which handles items like bigroot
				int recoilDamage = (mDamageDealt * recoil) / 100;
				attackerHp = attackerHp + recoilDamage;
				if(attackerHp < 0) {
					attackerHp = 0;
				}
				mAttacker.setCurrentHP(attackerHp);
				System.out.println(mAttacker.getNickname() + " takes " + (recoilDamage * -1) + " damage in recoil!");
				if(mAttacker.getCurrentHP() == 0) {
					mAttacker.setStatus(NonVolatileStatusAilment.FAINTED);
					System.out.println(mTarget.getNickname() + " fainted!");
				}
			}
			applyAttackerStatusAilments();
		}
	}
	
	private void angerPointCheck() {
		if(mTarget.tookACrit() && mTarget.hasAbility(Ability.ANGER_POINT)) {
			mTarget.setStatStageChange(ModdableBattleStats.ATTACK, 6);
		}
	}

	private void applyAttackerStatChanges() {
		int chance = mMove.getSecondaryStatChangeChance();
		int gen = mGenerator.nextInt(100) + 1;
		if(gen <= chance) {
			int[] statChanges = mMove.getMoveStatChanges();
			mAttacker.addStatChangeArray(statChanges);
		}
		//TODO output saying what has changed
	}
	
	private void applyTargetStatChanges() {
		int chance = mMove.getSecondaryStatChangeChance();
		int gen = mGenerator.nextInt(100) + 1;
		if(gen <= chance) {
			int[] statChanges = mMove.getMoveStatChanges();
			mTarget.addStatChangeArray(statChanges);
		}
		//TODO output saying what has changed
	}
	
	private void applyAttackerStatusAilments() {
		//TODO outrage, thrash... i think
	}
	
	private void applyTargetNonVolatileStatusAilments() {
		if(mTarget.isAffectedByStatusAilment()) {
			return;
		}
		int chance = mMove.getSecondaryAilmentChance();
		//TODO enhance chance of secondary effect here with abilities/items etc
		int i = mGenerator.nextInt(100) + 1;
		if(i <= chance) {
			switch(mMove.getStatusAilment()) {
			case POISON :
				mTarget.setStatus(NonVolatileStatusAilment.POISON);
				break;
			case TOXIC :
				mTarget.setStatus(NonVolatileStatusAilment.TOXIC);
				break;
			case PARALYSIS :
				mTarget.setStatus(NonVolatileStatusAilment.PARALYZE);
				break;
			case FREEZE :
				mTarget.setStatus(NonVolatileStatusAilment.FREEZE);
				break;
			case BURN :
				mTarget.setStatus(NonVolatileStatusAilment.BURN);
				break;
			case SLEEP :
				mTarget.setStatus(NonVolatileStatusAilment.SLEEP);
				sleepCounter(mTarget);
				break;
			default :
				//TODO blank?
			}
		}
	}
	
	private void applyTargetVolatileStatusAilments() {
		int chance = mMove.getSecondaryAilmentChance();
		//TODO enhance chance of secondary effect here with abilities/items etc
		int i = mGenerator.nextInt(100) + 1;
		if(i <= chance) {
			switch(mMove.getStatusAilment()) {
			case CONFUSION :
				mTarget.setBatonVolatileAilment(VolatileEffectBatonPass.CONFUSION, true);
				confuseCounter(mTarget);
				break;
			case LEECH_SEED :
				mTarget.setBatonVolatileAilment(VolatileEffectBatonPass.LEECH_SEED, true);
				break;
			case PERISH_SONG :
				mTarget.setBatonVolatileAilment(VolatileEffectBatonPass.PERISH_SONG, true);
				break;
			case EMBARGO :
				mTarget.setBatonVolatileAilment(VolatileEffectBatonPass.EMBARGO, true);
				break;
			case HEAL_BLOCK :
				mTarget.setBatonVolatileAilment(VolatileEffectBatonPass.HEAL_BLOCK, true);
				break;
			case RISE :
				mTarget.setBatonVolatileAilment(VolatileEffectBatonPass.RISE, true);
				break;
			case NIGHTMARE :
				if(mTarget.getStatus() == NonVolatileStatusAilment.SLEEP) {
					mTarget.setNoBatonVolatileAilment(VolatileEffectNoBatonPass.NIGHTMARE, true);
				}
				break;
			case INFATUATION :
				if(attractionCheck()) {
					mTarget.setNoBatonVolatileAilment(VolatileEffectNoBatonPass.INFATUATION, true);
				}
				break;
			case YAWN :
				if(mTarget.getStatus() == NonVolatileStatusAilment.NONE) {
					mTarget.setNoBatonVolatileAilment(VolatileEffectNoBatonPass.YAWN, true);
				}
				break;
			case TORMENT :
				mTarget.setBatonVolatileAilment(VolatileEffectBatonPass.TORMENT, true);
				break;
			case NO_TYPE_IMMUNITY :
				mTarget.setNoBatonVolatileAilment(VolatileEffectNoBatonPass.IDENTIFY, true);
				break;
			case INGRAIN :
				mTarget.setNoBatonVolatileAilment(VolatileEffectNoBatonPass.INGRAIN, true);
				break;
			case TRAPPED :
				mTarget.setNoBatonVolatileAilment(VolatileEffectNoBatonPass.TRAP, true);
				break;
			default :
				//TODO nothing goes here, dunno if i need to do anything even
				break;
			}
		}
		//TODO SET TIMERS FOR ABOVE VOLATILE EFFECTS!
	}
	
	private boolean accuracyCheck() {
		//TODO CHECK FLY
		if(mMove.getAccuracy() == -1) {
			return true;
		}
		/**Probability = MoveAccuracy * (AttackerAccuracy/TargetEvasion)*/
		int prob = 100;
		prob = mMove.getAccuracy() * (mAttacker.getAccuracy() / mTarget.getEvasion());
		int gen = mGenerator.nextInt(101);
		if(gen <= prob) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean attractionCheck() {
		if(mAttackerPokemon.getGender() == Gender.GENDERLESS || 
				mTargetPokemon.getGender() == Gender.GENDERLESS) {
			return false;
		}
		else if(mAttackerPokemon.getGender() != mTargetPokemon.getGender()) {
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
		mAttacker.setCurrentHP(mAttacker.getCurrentHP() + healAmount);
		if(mAttacker.getCurrentHP() > totalHp){
			mAttacker.setCurrentHP(totalHp);
		}
		System.out.println(mAttacker.getNickname() + " recovers health!");
	}
	
	private void healPulse() {
		int totalHp = mTargetPokemon.getStat(Stats.HP);
		int healAmount = totalHp / 2;
		mTarget.setCurrentHP(mTarget.getCurrentHP() + healAmount);
		if(mTarget.getCurrentHP() > totalHp){
			mTarget.setCurrentHP(totalHp);
		}
		System.out.println(mAttacker.getNickname() + " restores " + mTarget.getNickname() + "'s health!");
	}
	
	private void absorb() {
		//TODO big root, etc
		int restore = mDamageDealt / 2;
		int newTotal = mAttacker.getCurrentHP() + restore;
		if(newTotal > mAttackerPokemon.getStat(Stats.HP)) {
			mAttacker.setCurrentHP(mAttackerPokemon.getStat(Stats.HP));
		}
		else {
			mAttacker.setCurrentHP(newTotal);
		}
		System.out.println(mAttacker.getNickname() + " drains" + restore + " HP from " + mTarget.getNickname());
	}
	
	private void ohko() {
		if(mAttackerPokemon.getLevel() >= mTargetPokemon.getLevel()) {
			//Accuracy = (level of user minus level of target) + 30%
			int acc = mAttackerPokemon.getLevel() - mTargetPokemon.getLevel() + 30;
			int gen = mGenerator.nextInt(101);
			if(gen <= acc) {
				mTarget.setCurrentHP(0);
				mTarget.setStatus(NonVolatileStatusAilment.FAINTED);
				System.out.println("One hit KO!");
			}
		}
		else {
			System.out.println(mAttacker.getNickname() + "'s attack missed!");
		}
	}
	
	private boolean targetIsProtected() {
		//TODO if target has protect or detect activated return true
		// but if mMove is Feint return false and deactivate protect/detect 
		//(this is for multibattles but might as well code it now)
		
		//TODO also read up on moves that arent affected like shadow force and some others on bulbapedia
		return false; //temp
	}
	
	private void forceSwitch() {
		//TODO force a switch
	}
	
	private void fieldEffect() {
		if(mMove.getMoveId() == 356) {
			mBattle.setGravity(true);
		}
		else if(mMove.hasFlag(MoveFlag.WEATHER)) {
			mBattle.setWeather(mMove.getConjuredWeather());
		}
		else if(mMove.hasFlag(MoveFlag.SPORT)) {
			mBattle.setSport(mMove.getMoveSport());
		}
		else if(mMove.hasFlag(MoveFlag.ROOM)) {
			mBattle.setRoom(mMove.getRoom());
		}
		else {
			throw new IllegalAccessError("Shouldn't get here.");
		}
	}
	
	//TODO consider a move's "extra dialog" string which contains the "covered in mist!" text attached 
	//to move rather than writing them all here
	
	private void determineOneSideEffect() {
		switch(mMove.getMoveId()) {
		case 54 :
			System.out.println(mAttacker.getNickname() + "'s team is covered in mist!");
			mAttacker.setSideFieldEffect(OneSideFieldEffect.MIST, true);
			break;
		case 113 :
			System.out.println(mAttacker.getNickname() + "'s team is protected by light screen!");
			mAttacker.setSideFieldEffect(OneSideFieldEffect.LIGHT_SCREEN, true);
			break;
		case 115 :
			System.out.println(mAttacker.getNickname() + "'s team is protected by reflect");
			mAttacker.setSideFieldEffect(OneSideFieldEffect.LIGHT_SCREEN, true);
			break;
		case 191 :
			System.out.println(mTarget.getNickname() + "'s team is surrounded by spikes!");
			mTarget.setSideFieldEffect(OneSideFieldEffect.SPIKES, true);
			break;
		case 219 :
			System.out.println(mAttacker.getNickname() + "'s team is protected by safeguard!");
			mAttacker.setSideFieldEffect(OneSideFieldEffect.SAFEGUARD, true);
			break;
		case 366 :
			System.out.println(mAttacker.getNickname() + "'s team receives a tailwind!");
			mAttacker.setSideFieldEffect(OneSideFieldEffect.TAILWIND, true);
			break;
		case 381 :
			//TODO what to say?
			mAttacker.setSideFieldEffect(OneSideFieldEffect.LUCKY_CHANT, true);
			break;
		case 390 :
			System.out.println(mTarget.getNickname() + "'s team is surrounded by toxic spikes!");
			mTarget.setSideFieldEffect(OneSideFieldEffect.TOXIC_SPIKES, true);
			break;
		case 446 :
			System.out.println(mTarget.getNickname() + "'s team is surrounded by floating rocks!");
			mTarget.setSideFieldEffect(OneSideFieldEffect.STEALTH_ROCK, true);
			break;
		case 469 :
			System.out.println(mAttacker.getNickname() + "'s team is protected by wide guard!");
			mAttacker.setSideFieldEffect(OneSideFieldEffect.WIDE_GUARD, true);
			break;
		case 501 :
			System.out.println(mAttacker.getNickname() + "'s team is protected by quick guard!");
			mAttacker.setSideFieldEffect(OneSideFieldEffect.QUICK_GUARD, true);
			break;
		default :
			throw new IllegalArgumentException();
		}
	}
	
	private void sleepCounter(BattlingPokemon pokemon) {
		int len = mGenerator.nextInt(4) + 1;
		pokemon.setSleepCounter(len);
	}
	
	private void confuseCounter(BattlingPokemon pokemon) {
		int len = mGenerator.nextInt(4) + 1;
		pokemon.setConfuseCounter(len);
	}
	
	private void doConfusion() {
		mAttacker.setCurrentHP(mAttacker.getCurrentHP() - GameFields.damageCalcConfusion(mAttacker, mMove, mBattle));
	}
}
