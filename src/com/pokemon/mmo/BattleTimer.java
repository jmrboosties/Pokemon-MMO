package com.pokemon.mmo;

import com.pokemon.mmo.Enums.EffectType;

public class BattleTimer {

	private EffectType mEffectType;
	
	public BattleTimer(EffectType type) {
		this.mEffectType = type;
		//TODO check if timer already exists
		initiateTimer(mEffectType);
	}
	
	public void initiateTimer(EffectType type) {
		
	}
	
}
