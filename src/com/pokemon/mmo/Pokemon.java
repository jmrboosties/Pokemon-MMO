package com.pokemon.mmo;

import java.util.Random;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.Gender;
import com.pokemon.mmo.Enums.Nature;
import com.pokemon.mmo.Enums.Stats;
import com.pokemon.mmo.Enums.Status;
import com.pokemon.mmo.Enums.Types;

public class Pokemon {

	private String mNickName;
	private PokemonSpecies mSpecies;
	private Types[] mTypes = new Types[2];

	private Gender mGender;
	private int mLevel;
	private Ability mAbility;
	private Nature mNature;
	
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
	private Status mStatus;
	//private SecondaryStatus[] mSecondaryStatus;
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

		this.mTurnsInBattle = 0;
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

	public Ability getAbility() {
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
	}

	public int getCurrentHP() {
		return mCurrentHP;
	}

	public boolean isHelpedByHand() {
		// TODO Auto-generated method stub
		return false;
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

	public Status getStatus() {
		return mStatus;
	}

	public void setStatus(Status status) {
		this.mStatus = status;
	}

	public boolean isAffectedByStatusAilment() {
		if (mStatus != Status.NONE) {
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

	public boolean hasLightScreen() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasReflect() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasFlashFire() {
		// TODO Auto-generated method stub
		return false;
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
	
	public Move getRandomMove() {
		int moves = 0;
		for (int i = 0; i < mMoves.length; i++) {
			if(mMoves[i] != Main.mMoveArray[0]) {
				moves++;
			}
		}
		Random generator = new Random();
		int gen = generator.nextInt(moves) + 1;
		return mMoves[gen];
	}
}
