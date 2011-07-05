package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Ability;

public class AbilityClass {

	private final Ability mAbility;
	private boolean mAbilityIsActive = true;
	private Ability mAlteredAbility = Ability.NONE;
	
	public AbilityClass(Pokemon pokemon) {
		mAbility = pokemon.getAbility();
	}
	
	public void setAbilityActiveStatus(boolean b) {
		mAbilityIsActive = b;
	}
	
	public boolean isAbilityActive() {
		return mAbilityIsActive;
	}
	
	public void setAlteredAbility(Ability ability) {
		mAlteredAbility = ability;
	}
	
	public Ability getAlteredAbility() {
		return mAlteredAbility;
	}
	
	public Ability getBattleAbility() {
		if(mAlteredAbility != Ability.NONE) {
			return mAlteredAbility;
		}
		else {
			return mAbility;
		}
	}
	
	
}
