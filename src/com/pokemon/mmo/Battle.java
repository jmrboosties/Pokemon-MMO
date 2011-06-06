package com.pokemon.mmo;

import java.util.Random;
import java.util.Scanner;

import com.pokemon.mmo.Enums.Status;
import com.pokemon.mmo.Enums.TeamBuff;
import com.pokemon.mmo.Enums.VolatileStatus;
import com.pokemon.mmo.Enums.Weather;

public class Battle {

	protected Weather mWeather;
	protected Sport mSport;
	protected TeamBuff mTeamBuff;
	
	protected Move mLastMove;

	protected Pokemon mUserPokemon;
	protected Pokemon mOtherPokemon;
	
	protected Trainer mUserTrainer;
	protected Trainer mOtherTrainer;
	
	protected VolatileStatus mUserPokemonVolatile;

	protected boolean mBattleContinues = true;

	public static enum Sport {
		MUD_SPORT, WATER_SPORT
	}

	public Battle() {
		mWeather = Weather.NORMAL;
		mLastMove = Main.mMoveArray[0];
	}

	public Battle(Trainer trainer) {
		mUserTrainer = trainer;
		mWeather = Weather.NORMAL;
	}
	
	protected boolean battleThread() {
		onBattleStart();
		while(mBattleContinues) {
			round(preRound());
			postRound();
		}
		
		return true;
	}
	
	protected void onBattleStart() {
		
	}
	
	protected boolean preRound() {
		return true;
	}
	
	protected void round(boolean b) {
		if(b) {
			
		}
		else {
			
		}
		return; //when someone faints
	}
	
	protected void postRound() {
		
	}

	protected Pokemon determineOrder(Pokemon pokemon1, Pokemon pokemon2) {
		int speed1 = battleAdjustedSpeed(pokemon1);
		int speed2 = battleAdjustedSpeed(pokemon2);
		if (speed1 > speed2) {
			return pokemon1;
		} else if (speed2 > speed1) {
			return pokemon2;
		} else {
			if(mUserTrainer.getTrainerId() > mOtherTrainer.getTrainerId()) {
				return pokemon1;
			}
			else {
				return pokemon2;
			}
		}
	}

	protected int battleAdjustedSpeed(Pokemon pokemon) {
		// Speed = Stat * Stat Modifier * Speed Ability Modifier * Speed Item
		// Modifier
		int pokemonSpeed = (int) (pokemon.getSpeed() * getSpeedAbilityMod(pokemon));
		pokemonSpeed = (int) (pokemonSpeed * getSpeedItemMod(pokemon));
		pokemonSpeed = (int) (pokemonSpeed * getPayalyzeMod(pokemon));
		pokemonSpeed = pokemonSpeed * getTailwindMod(pokemon);
		return pokemonSpeed;
	}

	protected boolean executeMoves(Pokemon firstPokemon, Move firstMove,
			Pokemon secondPokemon, Move secondMove) {
		int firstHpValue = firstPokemon.getCurrentHP();
		int secondHpValue = secondPokemon.getCurrentHP();

		System.out.println(firstPokemon.getNickName() + " uses "
				+ firstMove.getMoveName() + "!");

		int damage1 = GameFields.damageCalc(firstPokemon, secondPokemon,
				firstMove, this);
		System.out.println(secondPokemon.getNickName() + " takes " + damage1
				+ " damage!");
		secondPokemon.setCurrentHP(secondHpValue - damage1);

		if (secondPokemon.getCurrentHP() <= 0) {
			secondPokemon.setCurrentHP(0);
			secondPokemon.setStatus(Status.FAINTED);
			System.out.println(secondPokemon.getNickName() + " fainted!");
			return false;
		}

		System.out.println(secondPokemon.getNickName() + " uses "
				+ secondMove.getMoveName() + "!");

		int damage2 = GameFields.damageCalc(secondPokemon, firstPokemon,
				secondMove, this);
		System.out.println(firstPokemon.getNickName() + " takes " + damage2
				+ " damage!");
		firstPokemon.setCurrentHP(firstHpValue - damage2);

		if (firstPokemon.getCurrentHP() <= 0) {
			firstPokemon.setCurrentHP(0);
			firstPokemon.setStatus(Status.FAINTED);
			System.out.println(firstPokemon.getNickName() + " fainted!");
			return false;
		}

		return true;
	}

	protected Move getTrainerMove(Pokemon pokemon) {
		Scanner scan = new Scanner(System.in);
		int choice = -1;
		Move[] moves = pokemon.getMoveArray();
		int cnt = 1;
		do {
			System.out.println("Available moves:");
			for (Move move : moves) {
				if (move.getMoveName() != null) {
					System.out.println("(" + cnt + ") " + move.getMoveName());
					cnt++;
				}
			}
			System.out.print("Enter a number between 1 and " + cnt + ": ");
			String input = scan.nextLine();
			if (input.matches("^[1-" + cnt + "]$")) {
				choice = Integer.parseInt(input);
			}
		} while (choice == -1);
		return moves[choice - 1];
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

	protected void setSport(Sport sport) {
		this.mSport = sport;
	}

	protected Sport getSport() {
		return mSport;
	}

	protected static double getSpeedAbilityMod(Pokemon pokemon) {
		double sm = 1;
		//TODO use Bulbapedia to find abilities which affect pokemon speed, code accordingly.
		return sm;
	}

	protected static double getSpeedItemMod(Pokemon pokemon) {
		// TODO Work on this later, we don't have items yet.
		return 1;
	}

	protected static double getPayalyzeMod(Pokemon pokemon) {
		if(pokemon.getStatus() == Status.PARALYZE) {
			return 0.5;
		}
		else {
			return 1;
		}
	}

	protected int getTailwindMod(Pokemon pokemon) {
		// TODO Need to work in Tailwind into battle, for later.
		return 1;
	}

}
