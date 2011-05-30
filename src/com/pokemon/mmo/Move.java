package com.pokemon.mmo;

import com.pokemon.mmo.Enums.MoveEffectId;
import com.pokemon.mmo.Enums.MoveKinds;
import com.pokemon.mmo.Enums.MoveTargetId;
import com.pokemon.mmo.Enums.Moves;
import com.pokemon.mmo.Enums.Types;

public class Move {

	private String mName;
	private Moves mMoveEnum;
	private Types mType;
	private int mPower;
	private int mAccuracy;
	private int mPriority;
	private MoveEffectId mEffectId;
	private int mSecondaryChance;
	private MoveKinds mKind;
	private MoveTargetId mTarget;
	private int mPP;
	private int mMoveId;

	public static enum MoveList {
		CHARGE, ME_FIRST
	}

	public Move() {
		mName = "-----";
		mMoveId = 0;
		mMoveEnum = Moves.EMPTY;
		mType = Types.NONE;
		mPower = 0;
		setAccuracy(0);
		setPriority(0);
		mEffectId = MoveEffectId.DAMAGE;
	}

	public void setMoveId(int id) {
		this.mMoveId = id;
	}

	public int getMoveId() {
		return mMoveId;
	}

	public void setMoveName(String name) {
		this.mName = name;
	}

	public String getMoveName() {
		return mName;
	}

	public void setType(Types type) {
		this.mType = type;
	}

	public Types getType() {
		return mType;
	}

	public void setEffectId(MoveEffectId effect) {
		this.mEffectId = effect;
	}

	public MoveEffectId getEffectId() {
		return mEffectId;
	}

	public void setSecondaryEffectChance(int chance) {
		this.mSecondaryChance = chance;
	}

	public int getSecondaryEffectChance() {
		return mSecondaryChance;
	}

	public void setMoveTarget(MoveTargetId target) {
		this.mTarget = target;
	}

	public MoveTargetId getMoveTarget() {
		return mTarget;
	}

	public void setBasePower(int power) {
		this.mPower = power;
	}

	public long getBasePower() {
		return mPower;
	}

	public void setAccuracy(int accuracy) {
		this.mAccuracy = accuracy;
	}

	public int getAccuracy() {
		return mAccuracy;
	}

	public void setPP(int pp) {
		this.mPP = pp;
	}

	public int getPP() {
		return mPP;
	}

	public MoveKinds getKind() {
		return mKind;
	}

	public void setKind(MoveKinds kind) {
		this.mKind = kind;
	}

	public boolean isRecoil() {
		// TODO ALL OF THIS
		return false;
	}

	public boolean isPunching() {
		// TODO Auto-generated method stub
		return false;
	}

	public Moves getMoveEnum() {
		return mMoveEnum;
	}

	public void setPriority(int priority) {
		this.mPriority = priority;
	}

	public int getPriority(Move move) {
		return mPriority;
	}

}
