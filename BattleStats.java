package com.pokemon.mmo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

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
	}
	
	public BattleStats(Trainer trainer) {
		mWeather = Weather.NORMAL; //GET WEATHER
	}
	
	protected Pokemon determineOrder(Pokemon pokemon1, Pokemon pokemon2) {
		int speed1 = battleAdjustedSpeed(pokemon1);
		int speed2 = battleAdjustedSpeed(pokemon2);
		if(speed1 > speed2) {
			return pokemon1;
		}
		else if(speed2 > speed1) {
			return pokemon2;
		}
		else {
			Random generator = new Random();
			int random = generator.nextInt(2);
			if(random == 0) {
				return mPokemon1;
			}
			else {
				return mPokemon2;
			}
		}
	}
	
	protected int battleAdjustedSpeed(Pokemon pokemon) {
		//Speed = Stat * Stat Modifier * Speed Ability Modifier * Speed Item Modifier
		int pokemonSpeed = (int) (pokemon.getSpeed() * getSpeedAbilityMod(pokemon));
		pokemonSpeed = (int) (pokemonSpeed * getSpeedItemMod(pokemon));
		pokemonSpeed = (int) (pokemonSpeed * getPayalyzeMod(pokemon));
		pokemonSpeed = pokemonSpeed * getTailwindMod(pokemon);
		return pokemonSpeed;
	}
	
	protected boolean executeMoves(Pokemon firstPokemon, Move firstMove, Pokemon secondPokemon, Move secondMove) {
		int firstHpValue = firstPokemon.getCurrentHP();
		int secondHpValue = secondPokemon.getCurrentHP();
		
		System.out.println(firstPokemon.getNickName() + " uses " + firstMove.getMoveName() + "!");
		
		int damage1 = GameFields.damageCalc(firstPokemon, secondPokemon, firstMove, this);
		System.out.println(secondPokemon.getNickName() + " takes " + damage1 + " damage!");
		secondPokemon.setCurrentHP(secondHpValue - damage1);
		
		if(secondPokemon.getCurrentHP() <= 0) {
			secondPokemon.setCurrentHP(0);
			secondPokemon.setStatus(Status.FAINTED);
			System.out.println(secondPokemon.getNickName() + " fainted!");
			return false;
		}
		
		System.out.println(secondPokemon.getNickName() + " uses " + secondMove.getMoveName() + "!");
		
		int damage2 = GameFields.damageCalc(secondPokemon, firstPokemon, secondMove, this);
		System.out.println(firstPokemon.getNickName() + " takes " + damage2 + " damage!");
		firstPokemon.setCurrentHP(firstHpValue - damage2);
		
		if(firstPokemon.getCurrentHP() <= 0) {
			firstPokemon.setCurrentHP(0);
			firstPokemon.setStatus(Status.FAINTED);
			System.out.println(firstPokemon.getNickName() + " fainted!");
			return false;
		}
		
		return true;
	}
	
	protected Move getTrainerMove(Pokemon pokemon) {
		System.out.println("Choose your move!");
		
		Move slot1 = pokemon.getSlot1();
		Move slot2 = pokemon.getSlot2();
		Move slot3 = pokemon.getSlot3();
		Move slot4 = pokemon.getSlot4();
		
		System.out.println("Enter the move you wish to select exactly how you see it.");
		
		String moveName1 = "";		
		String moveName2 = "";
		String moveName3 = "";
		String moveName4 = "";
		
		if(slot1 != null) {
			moveName1 = slot1.getMoveName();
		}
		if(slot2 != null) {
			moveName2 = slot2.getMoveName();
		}
		if(slot3 != null) {
			moveName3 = slot3.getMoveName();
		}
		if(slot4 != null) {
			moveName4 = slot4.getMoveName();
		}
		
		System.out.println("Your options:");
		System.out.println(moveName1);
		System.out.println(moveName2);
		System.out.println(moveName3);
		System.out.println(moveName4);
		
		String input =  "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Enter something jackass.");
			getTrainerMove(pokemon);
		}
		
		if(slot1 != null && input.equals(moveName1)) {
			return slot1;
		}
		else if(slot2 != null && input.equals(moveName2)) {
			return slot2;
		}
		else if(slot3 != null && input.equals(moveName3)) {
			return slot3;
		}
		else if(slot4 != null && input.equals(moveName4)) {
			return slot4;
		}
		else {
			System.out.println("Enter a valid move.");
			
			return getTrainerMove(pokemon);
		}
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
