package com.pokemon.mmo;

import java.util.Random;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.Gender;
import com.pokemon.mmo.Enums.ModdableBattleStats;
import com.pokemon.mmo.Enums.Nature;
import com.pokemon.mmo.Enums.NonVolatileStatusAilment;
import com.pokemon.mmo.Enums.Stats;
import com.pokemon.mmo.Enums.Types;
import com.pokemon.mmo.Enums.VolatileEffectBatonPass;
import com.pokemon.mmo.Enums.VolatileEffectNoBatonPass;

public class Pokemon {

	private String mNickName;
	private PokemonSpecies mSpecies;
	private Types[] mTypes = new Types[2];

	private Gender mGender;
	private int mLevel;
	private Ability mAbility;
	private Nature mNature;
	
	private AbilityClass mBattleAbility; //TODO getter no setter
	
	private boolean[] mBatonPassVolatileStatus = new boolean[VolatileEffectBatonPass.values().length];
	private boolean[] mNoBatonPassVolatileStatus = new boolean[VolatileEffectNoBatonPass.values().length];
	private NonVolatileStatusAilment mAilment;
	private int[] mBattleStatBuffs = new int[7]; //Att, Def, SpAtt, SpDef, Speed, Acc, Evade
	
	/**
	 * The following arrays are structed like so: 
	 * 0 = HP, 1 = Attack, 2 = Defense, 3 = Sp. Attack, 4 = Sp. Defense, 5 = Speed
	 * Use Stats enumerator ordinal for use (stat.ordinal()).
	 */
	private int[] mRealStats =  new int[6];
	private int[] mIVs = new int[6];
	private int[] mEVs = new int[6];
	
	private Move[] mMoves = new Move[4];

	private int mCurrentHP;
	private int mAccuracy;
	private int mEvasion;
	private int mTurnsInBattle;

	public Pokemon(PokemonSpecies species) {
		this.mNickName = species.getSpeciesName();
		this.mSpecies = species;
		this.mGender = Gender.GENDERLESS; // CHANGE THIS TO JUST DEFAULT TO
											// RANDOM BETWEEN MALE AND FEMALE
											// FOLLOWING GENDER RATIO
		mTypes[0] = species.getType(1);
		mTypes[1] = species.getType(2);

		Random generator = new Random();
		int natureInt = generator.nextInt(25) + 1; 

		mAbility = Ability.NONE;
		mNature = Nature.getNature(natureInt);
		
		for (int i = 0; i < mIVs.length; i++) {
			mIVs[i] = generator.nextInt(32);
		}

		for (int i = 0; i < mEVs.length; i++) {
			mEVs[i] = 0;
		}
		
		for (int i = 0; i < mMoves.length; i++) {
			mMoves[i] = Main.mMoveArray[0];
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

		this.mTurnsInBattle = 0;
		this.mAccuracy = 100;
		this.mEvasion = 100;
	}
	
	public void initBattleAbility() {
		if(mAbility != Ability.NONE) {
			mBattleAbility = new AbilityClass(this);
		}
		else {
			throw new IllegalArgumentException("Ability for " + mNickName + " not set.");
		}
	}
	
	public Ability getBattleAbility() {
		return mBattleAbility.getBattleAbility();
	}

	public void setLevel(int level) {
		this.mLevel = level;
		PokemonFactory.respecStats(this, mSpecies);
	}

	public int getLevel() {
		return mLevel;
	}

	public void setAbility(Ability ability) {
		this.mAbility = ability;
		PokemonFactory.respecStats(this, mSpecies);
	}

	public Ability getTrueAbility() {
		return mAbility;
	}
	
	public void setNature(Nature nature) {
		this.mNature = nature;
		PokemonFactory.respecStats(this, mSpecies);
	}
	
	public Nature getNature() {
		return mNature;
	}

	public int getHeldItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	public PokemonSpecies getSpecies() {
		return mSpecies;
	}

	public void setSpecies(PokemonSpecies species) {
		this.mSpecies = species;
		PokemonFactory.respecStats(this, mSpecies);
	}

	public void setType(int slot, Types type) {
		mTypes[slot-1] = type;
	}
	
	public Types[] getTypeArray() {
		return mTypes;
	}
	
	public Types getType(int slot) {
		return mTypes[slot-1];
	}
	
	public void setStat(Stats stat, int val) {
		mRealStats[stat.ordinal()] = val;
	}
	
	public void setStatsArray(int[] statVals) {
		this.mRealStats = statVals;
	}
	
	public int getStat(Stats stat) {
		return mRealStats[stat.ordinal()];
	}
	
	public int[] getStatsArray() {
		return mRealStats;
	}
	
	public void setIV(Stats stat, int iv) {
		mIVs[stat.ordinal()] = iv;
	}
	
	public void setIVArray(int[] ivVals) {
		this.mIVs = ivVals;
	}
	
	public int getIV(Stats stat) {
		return mIVs[stat.ordinal()];
	}
	
	public int[] getIVArray() {
		return mIVs;
	}
	
	public void setEV(Stats stat, int ev) {
		mEVs[stat.ordinal()] = ev;
	}
	
	public void setEVArray(int[] evs) {
		this.mEVs = evs;
	}
	
	public int getEV(Stats stat) {
		return mEVs[stat.ordinal()];
	}
	
	public int[] getEVArray() {
		return mEVs;
	}

	public void setNickName(String mNickName) {
		this.mNickName = mNickName;
	}

	public String getNickName() {
		return mNickName;
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

	public Gender getGender() {
		return mGender;
	}

	public void setGender(Gender gender) {
		this.mGender = gender;
	}

	public double getCurrentHealthRatio() {
		double ratio = mCurrentHP / mRealStats[0];
		return ratio;
	}

	public NonVolatileStatusAilment getStatus() {
		return mAilment;
	}

	public void setStatus(NonVolatileStatusAilment status) {
		this.mAilment = status;
	}

	public boolean isAffectedByStatusAilment() {
		if (mAilment != NonVolatileStatusAilment.NONE) {
			return true;
		} else {
			return false;
		}
	}

	public int getTurnsInBattle() {
		return mTurnsInBattle;
	}

	public void nextTurnInBattle() {
		this.mTurnsInBattle = mTurnsInBattle + 1;
	}
	
	public void setMoveSlot(int i, Move move) {
		i = i - 1;
		mMoves[i] = move;
	}
	
	public Move getMove(int i) {
		return mMoves[i];
	}

	public Move[] getMoveArray() {
		return mMoves;
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
		if(mBattleAbility.getBattleAbility() == ability) {
			return true;
		}
		else {
			return false;
		}
	}
}
