package com.pokemon.mmo;

import java.util.Random;
import java.util.Scanner;

import com.pokemon.mmo.Enums.Status;
import com.pokemon.mmo.Enums.Weather;

public class BattleStats {

	private Weather mWeather;
	private Move mLastMove;
	private Sport mSport;

	private Pokemon mPokemon1;
	private Pokemon mPokemon2;

	private boolean mBattleContinues = true;

	public static enum Sport {
		MUD_SPORT, WATER_SPORT
	}

	public BattleStats() {
		mWeather = Weather.NORMAL;
		mLastMove = Main.mMoveArray[0];
	}

	public BattleStats(Trainer trainer) {
		mWeather = Weather.NORMAL; // GET WEATHER
	}

	protected Pokemon determineOrder(Pokemon pokemon1, Pokemon pokemon2) {
		int speed1 = battleAdjustedSpeed(pokemon1);
		int speed2 = battleAdjustedSpeed(pokemon2);
		if (speed1 > speed2) {
			return pokemon1;
		} else if (speed2 > speed1) {
			return pokemon2;
		} else {
			Random generator = new Random();
			int random = generator.nextInt(2);
			if (random == 0) {
				return mPokemon1;
			} else {
				return mPokemon2;
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
		Move[] moves = pokemon.getSlots();
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

		return sm;
	}

	protected static double getSpeedItemMod(Pokemon pokemon) {
		// TODO Auto-generated method stub
		return 1;
	}

	protected static double getPayalyzeMod(Pokemon pokemon) {
		// TODO Auto-generated method stub
		return 1;
	}

	protected int getTailwindMod(Pokemon pokemon) {
		// TODO Auto-generated method stub
		return 1;
	}

	public void setBattleContinue(boolean continues) {
		this.mBattleContinues = continues;
	}

	public boolean isBattleContinue() {
		return mBattleContinues;
	}

}
