package com.pokemon.mmo;

import java.util.Random;

import com.pokemon.mmo.Enums.Abilities;
import com.pokemon.mmo.Enums.Genders;
import com.pokemon.mmo.Enums.Moves;
import com.pokemon.mmo.Enums.Stats;
import com.pokemon.mmo.Enums.Status;
import com.pokemon.mmo.Enums.Types;

public class Pokemon {

	private String mNickName;
	private PokemonSpecies mSpecies;
	private Types mType1;
	private Types mType2;
	
	private Genders mGender;
	private int mLevel;
	
	private int mHPIV;
	private int mAttackIV;
	private int mDefenseIV;
	private int mSpAttackIV;
	private int mSpDefenseIV;
	private int mSpeedIV;

	private int mHPEVs;
	private int mAttackEVs;
	private int mDefenseEVs;
	private int mSpAttackEVs;
	private int mSpDefenseEVs;
	private int mSpeedEVs;
	
	private int mMaxHP;
	private int mAttack;
	private int mDefense;
	private int mSpAttack;
	private int mSpDefense;
	private int mSpeed;
	
	private Move mSlot1;
	private Move mSlot2;
	private Move mSlot3;
	private Move mSlot4;
	
	private int mCurrentHP;
	private Status mStatus;
	private int mTurnsInBattle;
	private Abilities mAbility;
	
	public Pokemon(PokemonSpecies species) {
		this.mNickName = species.getSpeciesName();
		this.mSpecies = species;
		this.mGender = Genders.GENDERLESS; //CHANGE THIS TO JUST DEFAULT TO RANDOM BETWEEN MALE AND FEMALE FOLLOWING GENDER RATIO
		this.mType1 = species.getType1(); 
		this.mType2 = species.getType2();
		
		Random generator = new Random();
		
		this.mHPIV = generator.nextInt(32);
		this.mAttackIV = generator.nextInt(32);
		this.mDefenseIV = generator.nextInt(32);
		this.mSpAttackIV = generator.nextInt(32);
		this.mSpDefenseIV = generator.nextInt(32);
		this.mSpeedIV = generator.nextInt(32);
		
		this.mHPEVs = 0;
		this.mAttackEVs = 0;
		this.mDefenseEVs = 0;
		this.mSpAttackEVs = 0;
		this.mSpDefenseEVs = 0;
		this.mSpeedEVs = 0;
		
		this.setSlot1(null);
		this.setSlot2(null);
		this.setSlot3(null);
		this.setSlot4(null);
		
		this.mTurnsInBattle = 0;
	}
	
	public void setLevel(int level) {
		this.mLevel = level;
	}
	
	public int getLevel() {
		return mLevel;
	}

	public void setAbility(Abilities ability) {
		this.mAbility = ability;
	}
	
	public Abilities getAbility() {
		return mAbility;
	}

	public int getHeldItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	public PokemonSpecies getSpecies() {
		return mSpecies;
	}
	
	public void setSpecies(PokemonSpecies mSpecies) {
		this.mSpecies = mSpecies;
	}

	public Types getType1() {
		return mType1;
	}
	
	public void setType1(Types type) {
		this.mType1 = type;
	}

	public Types getType2() {
		return mType2;
	}
	
	public void setType2(Types type) {
		this.mType2 = type;
	}

	public int getStatChanges(int column) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setHPIV(int mHPIV) {
		this.mHPIV = mHPIV;
	}

	public int getHPIV() {
		return mHPIV;
	}

	public void setAttackIV(int mAttackIV) {
		this.mAttackIV = mAttackIV;
	}

	public int getAttackIV() {
		return mAttackIV;
	}

	public void setDefenseIV(int mDefenseIV) {
		this.mDefenseIV = mDefenseIV;
	}

	public int getDefenseIV() {
		return mDefenseIV;
	}

	public void setSpAttackIV(int mSpAttackIV) {
		this.mSpAttackIV = mSpAttackIV;
	}

	public int getSpAttackIV() {
		return mSpAttackIV;
	}

	public void setSpDefenseIV(int mSpDefenseIV) {
		this.mSpDefenseIV = mSpDefenseIV;
	}

	public int getSpDefenseIV() {
		return mSpDefenseIV;
	}

	public void setSpeedIV(int mSpeedIV) {
		this.mSpeedIV = mSpeedIV;
	}

	public int getSpeedIV() {
		return mSpeedIV;
	}

	public int getHPEVs() {
		return mHPEVs;
	}

	public int getAttackEVs() {
		return mAttackEVs;
	}

	public int getDefenseEVs() {
		return mDefenseEVs;
	}

	public int getSpAttackEVs() {
		return mSpAttackEVs;
	}

	public int getSpDefenseEVs() {
		return mSpDefenseEVs;
	}

	public int getSpeedEVs() {
		return mSpeedEVs;
	}

	public void setHPStat(int setHPStat) {
		this.mMaxHP = setHPStat;
	}
	
	public int getHPStat() {
		return mMaxHP;
	}

	public void setStats(int value, Stats stat) {
		switch(stat) {
		case ATTACK :
			this.mAttack = value;
			break;
		case DEFENSE :
			this.mDefense = value;
			break;
		case SPECIAL_ATTACK :
			this.mSpAttack = value;
			break;
		case SPECIAL_DEFENSE :
			this.mSpDefense = value;
			break;
		case SPEED :
			this.mSpeed = value;
		}
	}
	
	public int getAttack() {
		return mAttack;
	}
	
	public int getDefense() {
		return mDefense;
	}
	
	public int getSpAttack() {
		return mSpAttack;
	}
	
	public int getSpDefense() {
		return mSpDefense;
	}
	
	public int getSpeed() {
		return mSpeed;
	}

	public void setNickName(String mNickName) {
		this.mNickName = mNickName;
	}

	public String getNickName() {
		return mNickName;
	}

	public void setHPEVs(int HPEVs) {
		mHPEVs = HPEVs;
	}

	public void setAttackEVs(int AttackEVs) {
		mAttackEVs = AttackEVs;
	}

	public void setDefenseEVs(int defenseEVs) {
		mDefenseEVs = defenseEVs;
	}

	public void setSpAttackEVs(int spAttackEVs) {
		mSpAttackEVs = spAttackEVs;
	}

	public void setSpDefenseEVs(int spDefenseEVs) {
		mSpDefenseEVs = spDefenseEVs;
	}

	public void setSpeedEVs(int speedEVs) {
		mSpeedEVs = speedEVs;
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

	public Genders getGender() {
		return mGender;
	}
	
	public void setGender(Genders gender) {
		this.mGender = gender;
	}

	public double getCurrentHealthRatio() {
		double ratio = mCurrentHP / mMaxHP;
		return ratio;
	}

	public Status getStatus() {
		return mStatus;
	}
	
	public void setStatus(Status status) {
		this.mStatus = status;
	}
	
	public boolean isAffectedByStatusAilment() {
		if(mStatus != Status.NONE) {
			return true;
		}
		else {
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

	public void setSlot1(Move move) {
		this.mSlot1 = move;
	}

	public Move getSlot1() {
		return mSlot1;
	}

	public void setSlot2(Move move) {
		this.mSlot2 = move;
	}

	public Move getSlot2() {
		return mSlot2;
	}

	public void setSlot3(Move move) {
		this.mSlot3 = move;
	}

	public Move getSlot3() {
		return mSlot3;
	}

	public void setSlot4(Move move) {
		this.mSlot4 = move;
	}

	public Move getSlot4() {
		return mSlot4;
	}
	
	public Move getRandomMove() {
		int moves = 0;
		if(getSlot1() != null) {
			moves++;
		}
		if(getSlot2() != null) {
			moves++;
		}
		if(getSlot3() != null) {
			moves++;
		}
		if(getSlot4() != null) {
			moves++;
		}
		Random generator = new Random();
		int gen = generator.nextInt(moves) + 1;
		if(gen == 1) {
			return mSlot1;
		}
		else if(gen == 2) {
			return mSlot2;
		}
		else if(gen == 3) {
			return mSlot3;
		}
		else if(gen == 4) {
			return mSlot4;
		}
		return null;
	}
	
}
