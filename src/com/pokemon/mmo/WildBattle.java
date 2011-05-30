package com.pokemon.mmo;

import java.util.Random;

import com.pokemon.mmo.Enums.Status;
import com.pokemon.mmo.Enums.Weather;

public class WildBattle extends BattleStats {

	private Trainer mTrainer;
	private Pokemon mActivePokemonTrainer;
	private Pokemon mWildPokemon;

	private Weather mWeather;
	private int mWeatherCountdown;
	// private location mLocation (sets tile you're on)

	private int mBattleTotalTurns; // SYNONYMOUS WITH TOTAL TURNS

	public WildBattle(Trainer trainer) {
		this.mTrainer = trainer;
		this.mWeather = Weather.NORMAL; // INHERIT THIS FROM TILE
		this.mActivePokemonTrainer = mTrainer.getLeadingPokemon();
	}

	public boolean wildBattleThread() {
		onWildBattleStart();
		newPokemonIn(mActivePokemonTrainer);
		while (isBattleContinue()) {
			if (round()) {
				postRound();
			} else {
				if (mWildPokemon.getStatus() == Status.FAINTED) {
					setBattleContinue(false);
					return true;
				} else if (mActivePokemonTrainer.getStatus() == Status.FAINTED) {
					if (openSwitchDialog()) {
						newPokemonIn(mActivePokemonTrainer);
						postRound();
					} else {
						setBattleContinue(false);
						return false;
					}
				}
			}
		}
		return true;
	}

	private void onWildBattleStart() {
		System.out
				.println("THE SCREEN IS FLASHING! MUSIC IS PLAYING! WILD POKEMON ENCOUNTER!");
		Random generator = new Random();
		int dexNum = generator.nextInt(3) + 1; // THIS WILL BE INHERITED FROM
												// ROUTE

		if (dexNum == 2) {
			dexNum = 4;
		} else if (dexNum == 3) {
			dexNum = 7;
		}
		int val = generator.nextInt(649) + 1;
		PokemonSpecies species = Main.mSpeciesArray[val];
		mWildPokemon = PokemonFactory.getPokemon(species);

		System.out.println("Wild " + mWildPokemon.getNickName() + " appeared!");
	}

	private void newPokemonIn(Pokemon pokemon) {
		System.out.println("Go get 'em, " + pokemon.getNickName() + "!");
		// TODO ABILITY AND ITEM HANDLING GOES HERE
	}

	private boolean round() {
		Move trainerMove = getTrainerMove(mActivePokemonTrainer);
		Move wildMove = getWildMove();
		int wildPriority = wildMove.getPriority(trainerMove);
		int trainerPriority = trainerMove.getPriority(wildMove);
		if (wildPriority > trainerPriority) {
			return executeMoves(mWildPokemon, wildMove, mActivePokemonTrainer,
					trainerMove);
		} else if (trainerPriority > wildPriority) {
			return executeMoves(mActivePokemonTrainer, trainerMove,
					mWildPokemon, wildMove);
		} else if (mActivePokemonTrainer == determineOrder(
				mActivePokemonTrainer, mWildPokemon)) {
			return executeMoves(mActivePokemonTrainer, trainerMove,
					mWildPokemon, wildMove);
		} else {
			return executeMoves(mWildPokemon, wildMove, mActivePokemonTrainer,
					trainerMove);
		}
	}

	private void postRound() {
		// HANDLE THINGS LIKE WEATHER COUNTDOWN
	}

	private boolean openSwitchDialog() {
		// TODO Auto-generated method stub
		return false;
	}

	private Move getWildMove() {
		return mWildPokemon.getRandomMove();
	}
}
