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
		mPokemon2 = PokemonFactory.getPokemon(species);
		
		mPokemon1 = trainer1.getLeadingPokemon();
		
		GameFields math = new GameFields(mPokemon1, mPokemon2);
		
		System.out.println("Wild " + mPokemon2.getSpecies().getSpeciesName() + " appeared!");
		
		
		
		
		
		
		/*while(mPokemon2.getCurrentHP() > 0) {
			System.out.println("Charmander uses Ember!");
			int damage = math.damageCalc(mPokemon1, mPokemon2, MoveFactory.getMove(Moves.EMBER), this);
			int current = mPokemon2.getCurrentHP();
			mPokemon2.setCurrentHP(current - damage);
			if(mPokemon2.getCurrentHP() > 0) {
				System.out.println(mPokemon2.getSpecies().getSpeciesName() + " has " + mPokemon2.getCurrentHP() + " HP left!");
			}
		}*/
		System.out.print(mPokemon2.getSpecies().getSpeciesName() + " fainted!");
	}
	
	private void onRoundStart() {
		Pokemon firstToMove = whoGoesFirst();
		Pokemon secondToMove = whoGoesSecond(firstToMove);
		
		Move move1 = firstToMove.getSlot1();
		Move move2 = secondToMove.getSlot1();
		
		
		
		if(move1.getPriority() < move1.getPriority()) {
			
		}
		else {
			GameFields math = new GameFields(firstToMove, secondToMove);
			int damage = math.damageCalc(firstToMove, secondToMove, move1, this);
		}
	}
	
	private Pokemon whoGoesFirst() {
		//Speed = Stat * Stat Modifier * Speed Ability Modifier * Speed Item Modifier
		int pokemon1Speed = (int) (mPokemon1.getSpeed() * getSpeedAbilityMod(mPokemon1));
		pokemon1Speed = (int) (pokemon1Speed * getSpeedItemMod(mPokemon1));
		pokemon1Speed = (int) (pokemon1Speed * getPayalyzeMod(mPokemon1));
		pokemon1Speed = pokemon1Speed * getTailwindMod(mPokemon1);
		
		//Speed = Stat * Stat Modifier * Speed Ability Modifier * Speed Item Modifier
		int pokemon2Speed = (int) (mPokemon2.getSpeed() * getSpeedAbilityMod(mPokemon2));
		pokemon2Speed = (int) (pokemon2Speed * getSpeedItemMod(mPokemon2));
		pokemon2Speed = (int) (pokemon2Speed * getPayalyzeMod(mPokemon2));
		pokemon2Speed = pokemon2Speed * getTailwindMod(mPokemon2);
		
		if(pokemon1Speed > pokemon2Speed) {
			return mPokemon1;
		}
		else if(pokemon2Speed > pokemon1Speed) {
			return mPokemon2;
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
	
	private Pokemon whoGoesSecond(Pokemon pokemon) {
		if(pokemon == mPokemon1) {
			return mPokemon2;
		}
		else {
			return mPokemon1;
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

	public void setSport(Sport sport) {
		this.mSport = sport;
	}

	public Sport getSport() {
		return mSport;
	}
	
	public static double getSpeedAbilityMod(Pokemon pokemon) {
		double sm = 1;
		
		return sm;
	}
	
	public static double getSpeedItemMod(Pokemon pokemon) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static double getPayalyzeMod(Pokemon pokemon) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private int getTailwindMod(Pokemon pokemon) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
