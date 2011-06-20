package com.pokemon.mmo;

import com.pokemon.mmo.Enums.ModdableBattleStats;
import com.pokemon.mmo.Enums.MoveEffectGroup;
import com.pokemon.mmo.Enums.MoveKinds;
import com.pokemon.mmo.Enums.MoveSecondaryNonVolatileEffect;
import com.pokemon.mmo.Enums.MoveSecondaryVolatileEffect;
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
	private MoveEffectGroup mEffectGroup;
	private MoveSecondaryVolatileEffect mVolatileEffect;
	private MoveSecondaryNonVolatileEffect mNonVolatileEffect;
	private int[] mMoveStatChanges = new int[7];
	private int mSecondaryChance;
	private MoveKinds mKind;
	private MoveTargetId mTarget;
	private int mPP;
	private int mMoveId;	
	private int mMoveCode;

	public Move() {
		mName = "-----";
		mMoveId = 0;
		mMoveEnum = Moves.EMPTY;
		mType = Types.NONE;
		mPower = 0;
		mAccuracy = 0;
		mPriority = 0;
		setVolatileEffect(MoveSecondaryVolatileEffect.NONE);
		setNonVolatileEffect(MoveSecondaryNonVolatileEffect.NONE);
		mEffectGroup = MoveEffectGroup.NULL;
		
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

	public void setEffectId(MoveEffectGroup effect) {
		this.mEffectGroup = effect;
	}

	public MoveEffectGroup getEffectId() {
		return mEffectGroup;
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

	public void setVolatileEffect(MoveSecondaryVolatileEffect volatileEffect) {
		this.mVolatileEffect = volatileEffect;
	}

	public MoveSecondaryVolatileEffect getVolatileEffect() {
		return mVolatileEffect;
	}

	public void setNonVolatileEffect(MoveSecondaryNonVolatileEffect nonVolatileEffect) {
		this.mNonVolatileEffect = nonVolatileEffect;
	}

	public MoveSecondaryNonVolatileEffect getNonVolatileEffect() {
		return mNonVolatileEffect;
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
	
	public void setMoveCode(int i) {
		this.mMoveCode = i;
	}
	
	public int getMoveCode() {
		return mMoveCode;
	}
	
	
//	public void executeMove(Battle battle, Pokemon attacker, Pokemon defender/*Consider list of moves to record history and then just pick the most recent?*/) {
//		
//	}

	public void setMoveStatChangesArray(int[] moveStatChanges) {
		this.mMoveStatChanges = moveStatChanges;
	}
	
	public void setMoveStatChanges(ModdableBattleStats stat, int i) {
		this.mMoveStatChanges[stat.ordinal()] = i;
	}

	public int[] getMoveStatChanges() {
		return mMoveStatChanges;
	}

}
