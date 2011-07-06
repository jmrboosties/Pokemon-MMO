package com.pokemon.mmo;

import java.util.Scanner;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.NonVolatileStatusAilment;
import com.pokemon.mmo.Enums.Stats;
import com.pokemon.mmo.Enums.TeamBuff;
import com.pokemon.mmo.Enums.VolatileStatus;
import com.pokemon.mmo.Enums.Weather;

public class Battle {

	protected RandomForPokemon mGenerator = new RandomForPokemon();
	
	protected Weather mWeather;
	protected Sport mSport;
	protected TeamBuff mTeamBuff;

	protected BattlePlayer mBattlePlayerYou;
	protected BattlePlayer mBattlePlayerEnemy;
	
	protected VolatileStatus mUserPokemonVolatile;

	protected boolean mBattleContinues = true;

	public static enum Sport {
		MUD_SPORT, WATER_SPORT
	}

	public Battle(Trainer trainer1, Trainer trainer2) {
		mWeather = Weather.NORMAL;
		mBattlePlayerYou = new BattlePlayer(trainer1); //TODO set trainer as a global
		mBattlePlayerEnemy = new BattlePlayer(trainer2); //TODO method which will get this from online or gen, in due time
		mBattlePlayerEnemy.setCurrentChosenMove(Main.mMoveArray[1]);
	}

	public Battle(Trainer trainer) {
		//TODO necessary? or something like it, alt constructor i mean
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
		System.out.println("Challenge Appears!");
		System.out.println("Trainer 1 sends out " + mBattlePlayerYou.getPokemon().getNickName());
		System.out.println("Trainer 2 sends out " + mBattlePlayerEnemy.getPokemon().getNickName());
		//TODO make a method which handles a pokemon's entry to the battlefield. call this whenever someone switches too
	}
	
	protected boolean preRound() {
		mBattlePlayerYou.setCurrentChosenMove(getTrainerMove(mBattlePlayerYou.getPokemon()));
		mBattlePlayerEnemy.setCurrentChosenMove(mGenerator.getRandomMove(mBattlePlayerEnemy.getPokemon()));
		return true;
	}
	
	protected void round(boolean b) {
		while(mBattlePlayerYou.getPokemon().getStatus() != NonVolatileStatusAilment.FAINTED && 
				mBattlePlayerEnemy.getPokemon().getStatus() != NonVolatileStatusAilment.FAINTED) {
			
			if(b) {
				if(mBattlePlayerYou == determineOrder(mBattlePlayerYou, mBattlePlayerEnemy)) {
					executeMove(mBattlePlayerYou, mBattlePlayerEnemy, true);
					executeMove(mBattlePlayerEnemy, mBattlePlayerYou, false);
				}
				else {
					executeMove(mBattlePlayerEnemy, mBattlePlayerYou, false);
					executeMove(mBattlePlayerYou, mBattlePlayerEnemy, true);
				}
			}
			else {
				System.out.println("Can you get here?");
			}
			return; //when someone faints
		}
		
	}
	
	protected void postRound() {
		mBattleContinues = false;
	}

	protected BattlePlayer determineOrder(BattlePlayer player1, BattlePlayer player2) {
		Move player1Move = player1.getCurrentChosenMove();
		Move player2Move = player2.getCurrentChosenMove();
		
		if(player1Move.getPriority() > player2Move.getPriority()) {
			return player1;
		}
		else if(player1Move.getPriority() < player2Move.getPriority()) {
			return player2;
		}
		else {
			int speed1 = battleAdjustedSpeed(player1);
			int speed2 = battleAdjustedSpeed(player2);
			if (speed1 > speed2) {
				return player1;
			} else if (speed2 > speed1) {
				return player2;
			} else {
				if(player1.getTrainer().getTrainerId() > player2.getTrainer().getTrainerId()) {
					return player1;
				}
				else {
					return player2;
				}
			}
		}
	}

	protected int battleAdjustedSpeed(BattlePlayer player) {
		// Speed = Stat * Stat Modifier * Speed Ability Modifier * Speed Item
		// Modifier
		Pokemon pokemon = player.getPokemon();
		int pokemonSpeed = (int) (pokemon.getStat(Stats.SPEED) * getSpeedAbilityMod(player));
		pokemonSpeed = (int) (pokemonSpeed * getSpeedItemMod(pokemon));
		pokemonSpeed = (int) (pokemonSpeed * getPayalyzeMod(pokemon));
		pokemonSpeed = pokemonSpeed * getTailwindMod(pokemon);
		return pokemonSpeed;
	}
	
	protected void executeMove(BattlePlayer attacker, BattlePlayer target, boolean bool) {
		//TODO this is the big one, better put it here than in each and every move object, I think...
		//if bool is true, user goes first, if false, enemy goes first
		Move move = attacker.getCurrentChosenMove();
		MoveExecutionThread execution = new MoveExecutionThread(attacker, target, move, this);
		switch(move.getMoveMetaCategory()) {
		case INFLICTS_DAMAGE : //means move is "normal, damage with possible effect to user or target.
			execution.standardMove();
			break;
		case MULTI_HIT :
			execution.multiHit();
			break;
		default :
			System.out.println(move.getMoveName() + " has a meta category of " + move.getMoveMetaCategory() + " and we don't have that coded.");
			break;
		}
		if(bool) {
			setBattlePlayerYou(execution.getAttacker());
			setBattlePlayerEnemy(execution.getDefender());
		}
		else {
			setBattlePlayerEnemy(execution.getAttacker());
			setBattlePlayerYou(execution.getDefender());
		}
	}
	
	protected void setBattlePlayerYou(BattlePlayer player) {
		this.mBattlePlayerYou = player;
	}
	
	protected void setBattlePlayerEnemy(BattlePlayer player) {
		this.mBattlePlayerEnemy = player;
	}

//	protected boolean executeMoves(Pokemon firstPokemon, Move firstMove,
//			Pokemon secondPokemon, Move secondMove) {
//		int firstHpValue = firstPokemon.getCurrentHP();
//		int secondHpValue = secondPokemon.getCurrentHP();
//
//		System.out.println(firstPokemon.getNickName() + " uses "
//				+ firstMove.getMoveName() + "!");
//
//		int damage1 = GameFields.damageCalc(firstPokemon, secondPokemon,
//				firstMove, this);
//		System.out.println(secondPokemon.getNickName() + " takes " + damage1
//				+ " damage!");
//		secondPokemon.setCurrentHP(secondHpValue - damage1);
//
//		if (secondPokemon.getCurrentHP() <= 0) {
//			secondPokemon.setCurrentHP(0);
//			secondPokemon.setStatus(NonVolatileStatusAilment.FAINTED);
//			System.out.println(secondPokemon.getNickName() + " fainted!");
//			return false;
//		}
//
//		System.out.println(secondPokemon.getNickName() + " uses "
//				+ secondMove.getMoveName() + "!");
//
//		int damage2 = GameFields.damageCalc(secondPokemon, firstPokemon,
//				secondMove, this);
//		System.out.println(firstPokemon.getNickName() + " takes " + damage2
//				+ " damage!");
//		firstPokemon.setCurrentHP(firstHpValue - damage2);
//
//		if (firstPokemon.getCurrentHP() <= 0) {
//			firstPokemon.setCurrentHP(0);
//			firstPokemon.setStatus(NonVolatileStatusAilment.FAINTED);
//			System.out.println(firstPokemon.getNickName() + " fainted!");
//			return false;
//		}
//
//		return true;
//	}

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
			System.out.print("Enter a number between 1 and " + String.valueOf(cnt-1) + ": ");
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

	protected void setSport(Sport sport) {
		this.mSport = sport;
	}

	protected Sport getSport() {
		return mSport;
	}

	protected double getSpeedAbilityMod(BattlePlayer player) {
		double sm = 1;
		Pokemon pokemon = player.getPokemon();
		if(player.isGastroAcid()) {
			return sm;
		}
		switch(pokemon.getBattleAbility()) {
		case CHLOROPHYLL :
			if(mWeather == Weather.SUNNY_DAY) {
				sm =  2;
			}
			break;
		case QUICK_FEET :
			if(pokemon.getStatus() != NonVolatileStatusAilment.NONE) {
				sm = 1.5;
			}
			break;
		case SAND_RUSH :
			if(mWeather == Weather.SANDSTORM) {
				sm = 2;
			}
			break;
		case SLOW_START :
			if(pokemon.getTurnsInBattle() <= 5) {
				sm = 0.5;
			}
			break;
		case SWIFT_SWIM :
			if(mWeather == Weather.RAIN_DANCE) {
				sm = 2;
			}
			break;
		case UNBURDEN :
			//TODO know if an item was held and now gone
			break;
		}
		return sm;
	}

	protected double getSpeedItemMod(Pokemon pokemon) {
		// TODO Work on this later, we don't have items yet.
		return 1;
	}

	protected double getPayalyzeMod(Pokemon pokemon) {
		if(pokemon.getStatus() == NonVolatileStatusAilment.PARALYZE
				&& pokemon.getBattleAbility() != Ability.QUICK_FEET) {
			return 0.25;
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
