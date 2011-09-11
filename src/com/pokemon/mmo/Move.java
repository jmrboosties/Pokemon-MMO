package com.pokemon.mmo;

import com.pokemon.mmo.Enums.MetaStatusAilment;
import com.pokemon.mmo.Enums.ModdableBattleStats;
import com.pokemon.mmo.Enums.MoveFlag;
import com.pokemon.mmo.Enums.MoveKinds;
import com.pokemon.mmo.Enums.MoveMetaCategory;
import com.pokemon.mmo.Enums.MoveTargetId;
import com.pokemon.mmo.Enums.Moves;
import com.pokemon.mmo.Enums.Room;
import com.pokemon.mmo.Enums.Sport;
import com.pokemon.mmo.Enums.Types;
import com.pokemon.mmo.Enums.Weather;

public class Move {

	private String mName;
	private Moves mMoveEnum;
	private Types mType;
	private int mPower;
	private int mAccuracy;
	private int mPriority;
	private int[] mMoveStatChanges = new int[7];
	private MetaStatusAilment mAilment;
	private int mSecondaryAilmentChance;
	private int mSecondaryStatChangeChance;
	private MoveKinds mKind;
	private MoveTargetId mTarget;
	private int mPP;
	private int mMoveId;	
	private int mMoveEffectId;
	private MoveMetaCategory mMetaCategory;
	private int mMoveEffect;
	private int mMinHits;
	private int mMaxHits;
	private int mMinTurns;
	private int mMaxTurns;
	private int mRecoilPercentage;
	private boolean[] mFlags = new boolean[MoveFlag.values().length];
	private Weather mConjuredWeather;
	private Sport mSport;
	private Room mRoom;

	public Move() {
		mName = "-----";
		mMoveId = 0;
		setMoveEffectId(0);
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
		
		for (int i = 0; i < mFlags.length; i++) {
			mFlags[i] = false;
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

	public void setSecondaryAilmentChance(int chance) {
		this.mSecondaryAilmentChance = chance;
	}

	public int getSecondaryAilmentChance() {
		return mSecondaryAilmentChance;
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

	public Moves getMoveEnum() {
		return mMoveEnum;
	}

	public void setPriority(int priority) {
		this.mPriority = priority;
	}

	public int getPriority() {
		return mPriority;
	}

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

	public void setSecondaryStatChangeChance(int secondaryStatChangeChance) {
		this.mSecondaryStatChangeChance = secondaryStatChangeChance;
	}

	public int getSecondaryStatChangeChance() {
		return mSecondaryStatChangeChance;
	}
	
	public void setMoveFlag(MoveFlag flag) {
		this.mFlags[flag.ordinal()] = true;
	}
	
	public boolean hasFlag(MoveFlag flag) {
		return mFlags[flag.ordinal()];
	}
	
	public boolean[] getMoveFlagArray() {
		return mFlags;
	}

	public void setConjuredWeather(Weather conjuredWeather) {
		this.mConjuredWeather = conjuredWeather;
		setMoveFlag(MoveFlag.WEATHER);
	}

	public Weather getConjuredWeather() {
		return mConjuredWeather;
	}

	public void setMoveSport(Sport sport) {
		this.mSport = sport;
		setMoveFlag(MoveFlag.SPORT);
	}

	public Sport getMoveSport() {
		return mSport;
	}

	public void setRoom(Room room) {
		this.mRoom = room;
		setMoveFlag(MoveFlag.ROOM);
	}

	public Room getRoom() {
		return mRoom;
	}

	public void setMoveEffectId(int moveEffectId) {
		this.mMoveEffectId = moveEffectId;
	}

	public int getMoveEffectId() {
		return mMoveEffectId;
	}

}
