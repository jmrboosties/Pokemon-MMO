package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Moves;
import com.pokemon.mmo.Enums.Types;


public class Move {
	
	private String mName;
	private Types mType;
	private int mPower;
	private int mAccuracy;
	private boolean mSecondary = false;
	private int mSecondaryEffect;
	private int mSecondaryChance;
	private int mMoveKind;
	
	public static final int STATUS = 0;
	public static final int PHYSICAL = 1;
	public static final int SPECIAL = 2;
	
	public static final int NO_EFFECT = 0;
	public static final int POISON_EFFECT = 1;
	public static final int TOXIC_EFFECT = 2;
	public static final int BURN_EFFECT = 3;
	public static final int PARALYZE_EFFECT = 4;
	public static final int FREEZE_EFFECT = 5;
	public static final int SLEEP_EFFECT = 6;
	public static final int FLINCH_EFFECT = 7;
	
	public static final int SCRATCH = 0;
	public static final int FLAMETHROWER = 1;
	public static final int ME_FIRST = 100;
	public static final int CHARGE = 54;
	
	public Move(Moves move) {
		mName = "";
		mType = Types.NONE;
		mPower = 0;
		mAccuracy = 0;
		mSecondary = false;
		
		switch(move) {
		case EMBER :
			mName = "Ember";
			mType = Types.FIRE;
			mPower = 40;
			mAccuracy = 100;
			mMoveKind = SPECIAL;
			mSecondary = true;
			mSecondaryChance = 10;
			mSecondaryEffect = BURN_EFFECT;
			break;
		}
		
	}
	
	public String getMoveName() {
		return mName;
	}

	public Types getType() {
		return mType;
	}

	public long getBasePower() {
		//TODO
		return 0;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

}
