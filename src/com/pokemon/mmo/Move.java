package com.pokemon.mmo;

import com.pokemon.mmo.Enums.MetaStatusAilment;
import com.pokemon.mmo.Enums.ModdableBattleStats;
import com.pokemon.mmo.Enums.MoveKinds;
import com.pokemon.mmo.Enums.MoveMetaCategory;
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
	private int[] mMoveStatChanges = new int[7];
	private MetaStatusAilment mAilment;
	private int mSecondaryChance;
	private MoveKinds mKind;
	private MoveTargetId mTarget;
	private int mPP;
	private int mMoveId;	
	private int mMoveCode;
	private MoveMetaCategory mMetaCategory;
	private int mMoveEffect;
	private int mMinHits;
	private int mMaxHits;
	private int mMinTurns;
	private int mMaxTurns;
	private int mRecoilPercentage;

	public Move() {
		mName = "-----";
		mMoveId = 0;
		mMoveEnum = Moves.EMPTY;
		mType = Types.NONE;
		mPower = 0;
		mAccuracy = 0;
		mPriority = 0;
		mMetaCategory = MoveMetaCategory.UNIQUE_EFFECT;
		mAilment = MetaStatusAilment.NONE;
		mMoveEffect = 0;
		mMinHits = 0;
		mMaxHits = 0;
		mMaxTurns = 0;
		mMinTurns = 0;
		setRecoilPercentage(0);
		
		for (int i = 0; i < mMoveStatChanges.length; i++) {
			mMoveStatChanges[i] = 0;
		}
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

	public int getPriority() {
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

	public void setMoveStatChangesArray(int[] moveStatChanges) throws IllegalArgumentException {
		if(moveStatChanges.length != 7) {
			throw new IllegalArgumentException();
		}
		this.mMoveStatChanges = moveStatChanges;
	}
	
	public void setMoveStatChanges(ModdableBattleStats stat, int i) {
		this.mMoveStatChanges[stat.ordinal()] = i;
	}

	public int[] getMoveStatChanges() {
		return mMoveStatChanges;
	}
	
	public void setMoveMetaCategory(MoveMetaCategory category) {
		this.mMetaCategory = category;
	}
	
	public MoveMetaCategory getMoveMetaCategory() {
		return mMetaCategory;
	}
	
	public void setStatusAilment(MetaStatusAilment ailment) {
		this.mAilment = ailment;
	}
	
	public MetaStatusAilment getStatusAilment() {
		return mAilment;
	}

	public void setMoveEffect(int moveEffect) {
		this.mMoveEffect = moveEffect;
	}

	public int getMoveEffect() {
		return mMoveEffect;
	}

	public void setMinHits(int minHits) {
		this.mMinHits = minHits;
	}

	public int getMinHits() {
		return mMinHits;
	}

	public void setMaxHits(int maxHIts) {
		this.mMaxHits = maxHIts;
	}

	public int getMaxHits() {
		return mMaxHits;
	}

	public void setMinTurns(int minTurns) {
		this.mMinTurns = minTurns;
	}

	public int getMinTurns() {
		return mMinTurns;
	}

	public void setMaxTurns(int maxTurns) {
		this.mMaxTurns = maxTurns;
	}

	public int getMaxTurns() {
		return mMaxTurns;
	}

	public void setRecoilPercentage(int recoilPercentage) {
		this.mRecoilPercentage = recoilPercentage;
	}

	public int getRecoilPercentage() {
		return mRecoilPercentage;
	}
	
	public boolean isRecoil() {
		if(mRecoilPercentage < 0) {
			return true;
		}
		else {
			return false;
		}
	}

}
