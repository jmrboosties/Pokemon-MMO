package com.pokemon.mmo;

import com.pokemon.mmo.Enums.MoveKinds;
import com.pokemon.mmo.Enums.Moves;
import com.pokemon.mmo.Enums.Types;


public class Move {
	
	private String mName;
	private Moves mMove;
	private Types mType;
	private int mPower;
	private int mAccuracy;
	private int mPriority;
	private boolean mSecondary = false;
	private int mSecondaryEffect;
	private int mSecondaryChance;
	private MoveKinds mKind;
	private boolean mTargetsFoe = true;
	
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
	
	public static enum MoveList {
		CHARGE, ME_FIRST
	}
	
	public Move(Moves move) {
		mName = "";
		mMove = move;
		mType = Types.NONE;
		mPower = 0;
		mAccuracy = 0;
		setPriority(0);
		mSecondary = false;
		
		switch(move) {
		case EMBER :
			mName = "Ember";
			mType = Types.FIRE;
			mKind = MoveKinds.SPECIAL;
			mPower = 40;
			mAccuracy = 100;
			mSecondary = true;
			mSecondaryChance = 10;
			mSecondaryEffect = BURN_EFFECT;
			break;
			
		case TACKLE :
			mName = "Tackle";
			mType = Types.NORMAL;
			mKind = MoveKinds.PHYSICAL;
			mPower = 50;
			mAccuracy = 100;
			mSecondary = false;
			break;
			
		case SCRATCH :
			mName = "Scratch";
			mType = Types.NORMAL;
			mKind = MoveKinds.PHYSICAL;
			mPower = 40;
			mAccuracy = 100;
			mSecondary = false;
			break;
		
		case EMPTY :
			mName = "-----";
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
		return mPower;
	}

	public MoveKinds getKind() {
		return mKind;
	}

	public boolean isRecoil() {
		//TODO ALL OF THIS
		return false;
	}

	public boolean isPunching() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Moves getMove() {
		return mMove;
	}

	public void setPriority(int mPriority) {
		this.mPriority = mPriority;
	}

	public int getPriority(Move move) {
		return mPriority;
	}

}
