package com.pokemon.mmo;

import java.util.ArrayList;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.ModdableBattleStats;
import com.pokemon.mmo.Enums.NonVolatileStatusAilment;
import com.pokemon.mmo.Enums.OneSideFieldEffect;
import com.pokemon.mmo.Enums.VolatileEffectBatonPass;
import com.pokemon.mmo.Enums.VolatileEffectNoBatonPass;

public class BattlingPokemon {

	private final Pokemon mPokemon;
	private String mNickName;
	
	private Trainer mTrainer;
	private Pokemon mBattlePokemon;
	private Move mCurrentChosenMove;
	private Ability mAbility;
	
	private int mItem;
	
	private int mAccuracy;
	private int mEvasion;
	private int mTurnsInBattle;
	
	private boolean mGastroAcid = false; //TODO add this to field? dont think so
	
	private boolean mTookACrit = false;
	
	private ArrayList<Move> mPreviousMoves; //TODO
	private boolean[] mOnFieldBuffs = new boolean[OneSideFieldEffect.values().length];
	
	private boolean[] mBatonPassVolatileStatus = new boolean[VolatileEffectBatonPass.values().length];
	private boolean[] mNoBatonPassVolatileStatus = new boolean[VolatileEffectNoBatonPass.values().length];
	private NonVolatileStatusAilment mAilment;
	
	private int mSleepCounter;
	private int mConfuseCounter;
	private int mPerishCounter;
	
	private int mCurrentHP;
	
	private int[] mBattleStatBuffs = new int[7]; //Att, Def, SpAtt, SpDef, Speed, Acc, Evade
	
	private Move[] mMoves = new Move[4];
	
	public BattlingPokemon(Trainer trainer) {
		this.mTrainer = trainer;
		mPokemon = mTrainer.getLeadingPokemon();
		mBattlePokemon = mPokemon;
		mNickName = mPokemon.getNickName();
		
		for (int i = 0; i < mOnFieldBuffs.length; i++) {
			mOnFieldBuffs[i] = false;
		}
		
		for (int i = 0; i < mBatonPassVolatileStatus.length; i++) {
			mBatonPassVolatileStatus[i] = false;
		}
		
		for (int i = 0; i < mNoBatonPassVolatileStatus.length; i++) {
			mNoBatonPassVolatileStatus[i] = false;
		}
		
		for (int i = 0; i < mBattleStatBuffs.length; i++) {
			mBattleStatBuffs[i] = 0;
		}
		
		this.mCurrentHP = mBattlePokemon.getCurrentHP();
		this.mAilment = NonVolatileStatusAilment.NONE;
		this.mSleepCounter = -1;
		this.mSleepCounter = -1;
		this.mPerishCounter = -1;
		this.mTurnsInBattle = 0;
		this.mAccuracy = 100;
		this.mEvasion = 100;
		this.mAbility = mBattlePokemon.getAbility();
		this.mMoves = mBattlePokemon.getMoveArray();
	}
	
	public void setTrainer(Trainer trainer) {
		this.mTrainer = trainer;
	}
	
	public Trainer getTrainer() {
		return mTrainer;
	}
	
	public void setPokemon(Pokemon pokemon) {
		this.mBattlePokemon = pokemon;
	}
	
	public Pokemon getPokemon() {
		return mBattlePokemon;
	}
	
	public void setCurrentChosenMove(Move move) {
		this.mCurrentChosenMove = move;
	}
	
	public Move getCurrentChosenMove() {
		return mCurrentChosenMove;
	}
	
	public boolean hasReflect() {
		return mOnFieldBuffs[OneSideFieldEffect.REFLECT.ordinal()];
	}
	
	public boolean hasLightScreen() {
		return mOnFieldBuffs[OneSideFieldEffect.LIGHT_SCREEN.ordinal()];
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
	
	public void setSideFieldEffect(OneSideFieldEffect effect, boolean b) {
		mOnFieldBuffs[effect.ordinal()] = b;
	}
	
	public boolean getSideFieldEffect(OneSideFieldEffect effect) {
		return mOnFieldBuffs[effect.ordinal()];
	}
	
	public void setBatonVolatileAilment(VolatileEffectBatonPass ailment, boolean bool) {
		int i = ailment.ordinal();
		mBatonPassVolatileStatus[i] = bool;
	}
	
	public boolean getBatonVolatileAilment(VolatileEffectBatonPass ailment) {
		int i = ailment.ordinal();
		return mBatonPassVolatileStatus[i];
	}
	
	public void setNoBatonVolatileAilment(VolatileEffectNoBatonPass ailment, boolean bool) {
		int i = ailment.ordinal();
		mNoBatonPassVolatileStatus[i] = bool;
	}
	
	public boolean getNoBatonVolatileAilment(VolatileEffectNoBatonPass ailment) {
		int i = ailment.ordinal();
		return mNoBatonPassVolatileStatus[i];
	}
	
	public void setStatStageChange(ModdableBattleStats stat, int i) {
		int j = stat.ordinal();
		mBattleStatBuffs[j] = i;
	}
	
	public void setStatStageChangeArray(int[] array) {
		if(array.length != ModdableBattleStats.values().length) {
			throw new IllegalArgumentException();
		}
		else {
			this.mBattleStatBuffs = array;
		}
	}
	
	public void addStatChangeArray(int[] array) {
		if(array.length != ModdableBattleStats.values().length) {
			throw new IllegalArgumentException();
		}
		else {
			for (int i = 0; i < mBattleStatBuffs.length; i++) {
				if(array[i] == 0) {
					//Nothing
				}
				else {
					mBattleStatBuffs[i] = mBattleStatBuffs[i] + array[i];
					if(mBattleStatBuffs[i] > 6) {
						mBattleStatBuffs[i] = 6;
						System.out.println(mNickName + "'s " + ModdableBattleStats.getStat(i).getName() + " is maxed out!");
					}
					else if(mBattleStatBuffs[i] < -6) {
						mBattleStatBuffs[i] = -6;
						System.out.println(mNickName + "'s " + ModdableBattleStats.getStat(i).getName() + " can't go lower!");
					}
					else if(array[i] < 0){
						System.out.println(mNickName + "'s " + ModdableBattleStats.getStat(i).getName() + " decreases!");
					}
					else if(array[i] > 0) {
						System.out.println(mNickName + "'s " + ModdableBattleStats.getStat(i).getName() + " increases!");
					}
				}
			}
		}
	}
	
	public int getStatStageChange(ModdableBattleStats stat) {
		int i = stat.ordinal();
		return mBattleStatBuffs[i];
	}
	
	public int[] getStatStageChangeArray() {
		return mBattleStatBuffs;
	}

	public int getTurnsInBattle() {
		return mTurnsInBattle;
	}

	public void nextTurnInBattle() {
		this.mTurnsInBattle = mTurnsInBattle + 1;
	}
	
	public void setAccuracy(int accuracy) {
		this.mAccuracy = accuracy;
	}

	public int getAccuracy() {
		//TODO check for held items here too
		int acc = mAccuracy;
		if(mAbility == Ability.COMPOUNDEYES) {
			acc = (int) (acc * 1.3);
		}
		if(mBattleStatBuffs[5] > 0) {
			acc = (int) (acc * (1 + (mBattleStatBuffs[5] / 3)));
		}
		else if(mBattleStatBuffs[5] < 0) {
			acc = (int) (acc * (3 / (3 - mBattleStatBuffs[5])));
		}
		return acc;
	}

	public void setEvasion(int evasion) {
		this.mEvasion = evasion;
	}

	public int getEvasion() {
		//TODO check for held items AND abilities
		int evade = mEvasion;
		if(mBattleStatBuffs[6] > 0) {
			evade = (int) (evade * (1 + (mBattleStatBuffs[6] / 3)));
		}
		else if(mBattleStatBuffs[6] < 0) {
			evade = (int) (evade * (3 / (3 - mBattleStatBuffs[6])));
		}
		return evade;
	}
	
	public boolean hasAbility(Ability ability) {
		if(mAbility == ability) {
			return true;
		}
		else {
			return false;
		}
	}

	public NonVolatileStatusAilment getStatus() {
		return mAilment;
	}
	
	public void setStatus(NonVolatileStatusAilment ailment) {
		this.mAilment = ailment;
	}

	public Ability getAbility() {
		return mAbility;
	}
	
	public void setAbility(Ability ability) {
		this.mAbility = ability;
	}

	public Move[] getMoveArray() {
		return mMoves;
	}
	
	public int getItem() {
		return mItem;
	}
	
	public String getNickname() {
		return mNickName;
	}
	
	public boolean isAffectedByStatusAilment() {
		if (mAilment != NonVolatileStatusAilment.NONE) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setSleepCounter(int i) {
		this.mSleepCounter = i;
	}
	
	public int getSleepCounter() {
		return mSleepCounter;
	}
	
	public void setConfuseCounter(int i) {
		this.mConfuseCounter = i;
	}
	
	public int getConfuseCounter() {
		return mConfuseCounter;
	}
	
	public int getPerishCount() {
		return mPerishCounter;
	}
	
	public void setPerishCounter() {
		this.mPerishCounter = 3;
		mBatonPassVolatileStatus[VolatileEffectBatonPass.PERISH_SONG.ordinal()] = true;
	}
	
	public void cancelPerishSong() {
		this.mPerishCounter = -1;
		mBatonPassVolatileStatus[VolatileEffectBatonPass.PERISH_SONG.ordinal()] = false;
	}
	
	public void setCurrentHP(int hp) {
	this.mCurrentHP = hp;
		if(mCurrentHP <= 0) {
			mCurrentHP = 0;
			mAilment = NonVolatileStatusAilment.FAINTED;
		}
    }

	public int getCurrentHP() {
		return mCurrentHP;
	}
	
}
