package com.pokemon.mmo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import com.pokemon.mmo.Enums.Status;
import com.pokemon.mmo.Enums.Weather;

public class WildBattle extends BattleStats {

	private Trainer mTrainer;
	private Pokemon mActivePokemonTrainer;
	private Pokemon mWildPokemon;
	
	private Weather mWeather;
	private int mWeatherCountdown;
	//private location mLocation (sets tile you're on)
	
	private int mBattleTotalTurns; //SYNONYMOUS WITH TOTAL TURNS
	
	public WildBattle(Trainer trainer) {
		this.mTrainer = trainer;
		this.mWeather = Weather.NORMAL; //INHERIT THIS FROM TILE
		this.mActivePokemonTrainer = mTrainer.getLeadingPokemon();
	}
	
	public boolean wildBattleThread() {
		onWildBattleStart();
		newPokemonIn(mActivePokemonTrainer);
		while(isBattleContinue()) {
			if(round()) {
				postRound();
			}
			else {
				if(mWildPokemon.getStatus() == Status.FAINTED) {
					setBattleContinue(false);
					return true;
				}
				else if(mActivePokemonTrainer.getStatus() == Status.FAINTED) {
					if(openSwitchDialog()) {
						newPokemonIn(mActivePokemonTrainer);
						postRound();
					}
					else {
						setBattleContinue(false);
						return false;
					}
				}
			}
		}
		return true;
	}

	private void onWildBattleStart() {
		System.out.println("THE SCREEN IS FLASHING! MUSIC IS PLAYING! WILD POKEMON ENCOUNTER!");
		Random generator = new Random();
		int dexNum = generator.nextInt(3) + 1; //THIS WILL BE INHERITED FROM ROUTE
	
		if(dexNum == 2) {
			dexNum = 4;
		}
		else if(dexNum == 3) {
			dexNum = 7;
		}
		PokemonSpecies species = PokemonSpeciesFactory.getPokemonSpecies(1);
		mWildPokemon = PokemonFactory.getPokemon(species);
		
		System.out.println("Wild " + mWildPokemon.getNickName() + " appeared!");
	}
	
	private void newPokemonIn(Pokemon pokemon) {
		System.out.println("Go get 'em, " + pokemon.getNickName() + "!");
		//TODO ABILITY AND ITEM HANDLING GOES HERE
	}
	
	private boolean round() {
		Move trainerMove = getTrainerMove();
		Move wildMove = getWildMove();
		int wildPriority = wildMove.getPriority(trainerMove);
		int trainerPriority = trainerMove.getPriority(wildMove);
		if(wildPriority > trainerPriority) {
			return executeMoves(mWildPokemon, wildMove, mActivePokemonTrainer, trainerMove);
		}
		else if(trainerPriority > wildPriority) {
			return executeMoves(mActivePokemonTrainer, trainerMove, mWildPokemon, wildMove);
		}
		else if(mActivePokemonTrainer == determineOrder(mActivePokemonTrainer, mWildPokemon)) {
			return executeMoves(mActivePokemonTrainer, trainerMove, mWildPokemon, wildMove);
		}
		else {
			return executeMoves(mWildPokemon, wildMove, mActivePokemonTrainer, trainerMove);
		}
	}
	
	private void postRound() {
		//HANDLE THINGS LIKE WEATHER COUNTDOWN
	}
	
	private boolean openSwitchDialog() {
		// TODO Auto-generated method stub
		return false;
	}

	private Move getWildMove() {
		return mWildPokemon.getRandomMove();
	}

	private Move getTrainerMove() {
		System.out.println("Choose your move!");
		System.out.println("Type either Scratch or Ember just how you see them.");
		String input =  "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Enter something jackass.");
			getTrainerMove();
		}
		if(input.equals("Scratch")) {
			return mActivePokemonTrainer.getSlot1();
		}
		else if(input.equals("Ember")) {
			return mActivePokemonTrainer.getSlot2();
		}
		else {
			System.out.println("Enter Scratch or Ember.");
			return getTrainerMove();
		}
		
	}

}
