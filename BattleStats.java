package com.pokemon.mmo;

import java.util.Random;

import com.pokemon.mmo.Enums.Moves;
import com.pokemon.mmo.Enums.Weather;

public class BattleStats {

	private Weather mWeather;
	private Move mLastMove;
	private Sport mSport;
	
	private Pokemon mPokemon1;
	private Pokemon mPokemon2;
	
	public static enum Sport {
		MUD_SPORT, WATER_SPORT
	}
	
	public BattleStats(Trainer trainer1) {
		mWeather = Weather.NORMAL;
		onWildBattleStart(trainer1);
	}
	
	private void onWildBattleStart(Trainer trainer1) {
		System.out.println("THE SCREEN IS FLASHING! MUSIC IS PLAYING! WILD POKEMON ENCOUNTER!");
		Random generator = new Random();
		int dexNum = generator.nextInt(3) + 1;
	
		if(dexNum == 2) {
			dexNum = 4;
		}
		else if(dexNum == 3) {
			dexNum = 7;
		}
		PokemonSpecies species = PokemonSpeciesFactory.getPokemonSpecies(dexNum);
		Pokemon wildPokemon = PokemonFactory.getPokemon(species);
		
		Pokemon trainerPokemon = trainer1.getLeadingPokemon();
		
		GameFields math = new GameFields(trainerPokemon, wildPokemon);
		
		System.out.println("Wild " + wildPokemon.getSpecies().getSpeciesName() + " appeared!");
		
		while(wildPokemon.getCurrentHP() > 0) {
			System.out.println("Charmander uses Ember!");
			int damage = math.damageCalc(trainerPokemon, wildPokemon, MoveFactory.getMove(Moves.EMBER), this);
			int current = wildPokemon.getCurrentHP();
			wildPokemon.setCurrentHP(current - damage);
			if(wildPokemon.getCurrentHP() > 0) {
				System.out.println(wildPokemon.getSpecies().getSpeciesName() + " has " + wildPokemon.getCurrentHP() + " HP left!");
			}
		}
		System.out.print(wildPokemon.getSpecies().getSpeciesName() + " fainted!");
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
