package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Weather;

public class BattleStats {

	private Weather mWeather;
	private Move mLastMove;
	private Sport mSport;
	
	public static enum Sport {
		MUD_SPORT, WATER_SPORT
	}
	
	public BattleStats() {
		mWeather = Weather.NORMAL;
	}
	
	public Weather getWeather() {
		return mWeather;
	}
	
	public void setWeather(Weather weather) {
		this.mWeather = weather;
	}

	public void setLastMove(Move lastMove) {
		this.mLastMove = lastMove;
	}

	public Move getLastMove() {
		return mLastMove;
	}

	public void setSport(Sport sport) {
		this.mSport = sport;
	}

	public Sport getSport() {
		return mSport;
	}
	
}
