package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Stats;

public class Pokemon {

	private String mNickName;
	private PokemonSpecies mSpecies;
	
	private int mLevel;
	
	private int mHPIV;
	private int mAttackIV;
	private int mDefenseIV;
	private int mSpAttackIV;
	private int mSpDefenseIV;
	private int mSpeedIV;

	private int mMaxHP;
	private int mCurrentHP;
	private int mAttack;
	private int mDefense;
	private int mSpAttack;
	private int mSpDefense;
	private int mSpeed;
	
	public void setLevel(int level) {
		this.mLevel = level;
	}
	
	public int getLevel() {
		return mLevel;
	}

	public int getAbility() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getHeldItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getSpecies() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setSpecies(PokemonSpecies mSpecies) {
		this.mSpecies = mSpecies;
	}

	public int getType1() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getType2() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

	public int getAttackEVs() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getDefenseEVs() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getSpAttackEVs() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getSpDefenseEVs() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getSpeedEVs() {
		// TODO Auto-generated method stub
		return 0;
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
	
}
