package com.pokemon.mmo;

import java.util.Scanner;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.NonVolatileStatusAilment;
import com.pokemon.mmo.Enums.Room;
import com.pokemon.mmo.Enums.Sport;
import com.pokemon.mmo.Enums.Stats;
import com.pokemon.mmo.Enums.VolatileStatus;
import com.pokemon.mmo.Enums.Weather;

public class Battle {

	protected RandomForPokemon mGenerator = new RandomForPokemon();
	
	protected Weather mWeather;
	protected Sport mSport;
	protected boolean mGravity = false;
	protected Room mRoom;

	protected BattlingPokemon mBattlePlayerYou;
	protected BattlingPokemon mBattlePlayerEnemy;
	
	protected VolatileStatus mUserPokemonVolatile; //TODO what does this do?

	protected boolean mBattleContinues = true;
	
	private static final int FIGHT = 1;
	private static final int POKEMON = 2;
	private static final int ITEM = 3;
	private static final int RUN = 4;

	public Battle(Trainer trainer1, Trainer trainer2) {
		mWeather = Weather.NORMAL;
		mBattlePlayerYou = new BattlingPokemon(trainer1); //TODO set trainer as a global
		mBattlePlayerEnemy = new BattlingPokemon(trainer2); //TODO method which will get this from online or gen, in due time
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
		switch(getTrainerCommand()) {
		case FIGHT :
			mBattlePlayerYou.setCurrentChosenMove(getTrainerMove(mBattlePlayerYou));
			break;
		case POKEMON :
			//TODO switch pokemon function
			System.out.println("Not here yet.");
			preRound();
			break;
		case ITEM :
			//TODO item function
			System.out.println("Not here yet.");
			preRound();
			break;
		case RUN :
			//TODO run function
			System.out.println("Not here yet.");
			preRound();
			break;
		}
		
		mBattlePlayerEnemy.setCurrentChosenMove(mGenerator.getRandomMove(mBattlePlayerEnemy.getPokemon()));
		return true;
	}
	
	protected void round(boolean b) {
		if(b) {
			if(mBattlePlayerYou == determineOrder(mBattlePlayerYou, mBattlePlayerEnemy)) {
				executeMove(mBattlePlayerYou, mBattlePlayerEnemy, true);
				if(mBattlePlayerEnemy.getStatus() == NonVolatileStatusAilment.FAINTED) {
					return;
				}
				System.out.println(mBattlePlayerEnemy.getNickname() + "'s Status: " + mBattlePlayerEnemy.getStatus()); //TODO TEMP
				executeMove(mBattlePlayerEnemy, mBattlePlayerYou, false);
				if(mBattlePlayerEnemy.getStatus() == NonVolatileStatusAilment.FAINTED) {
					return;
				}
			}
			else {
				executeMove(mBattlePlayerEnemy, mBattlePlayerYou, false);
				if(mBattlePlayerEnemy.getStatus() == NonVolatileStatusAilment.FAINTED) {
					return;
				}
				System.out.println(mBattlePlayerYou.getStatus()); //TODO TEMP
				executeMove(mBattlePlayerYou, mBattlePlayerEnemy, true);
			}
		}
		else {
			System.out.println("Can you get here?");
		}
		return; //when someone faints
	}
	
	protected void postRound() {
		mBattleContinues = false;
		//TODO if frozen, pokemon has 20% chance of thawing
	}

	protected BattlingPokemon determineOrder(BattlingPokemon player1, BattlingPokemon player2) {
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

	protected int battleAdjustedSpeed(BattlingPokemon pokemon) {
		// Speed = Stat * Stat Modifier * Speed Ability Modifier * Speed Item
		// Modifier
		int pokemonSpeed = (int) (pokemon.getPokemon().getStat(Stats.SPEED) * getSpeedAbilityMod(pokemon));
		pokemonSpeed = (int) (pokemonSpeed * getSpeedItemMod(pokemon));
		pokemonSpeed = (int) (pokemonSpeed * getPayalyzeMod(pokemon));
		pokemonSpeed = pokemonSpeed * getTailwindMod(pokemon);
		return pokemonSpeed;
	}
	
	protected void executeMove(BattlingPokemon attacker, BattlingPokemon target, boolean bool) {
		//TODO this is the big one, better put it here than in each and every move object, I think...
		//if bool is true, user goes first, if false, enemy goes first
		Move move = attacker.getCurrentChosenMove();
		MoveExecutionThread execution = new MoveExecutionThread(attacker, target, move, this);
		System.out.println(attacker.getPokemon().getNickName() + " uses " + move.getMoveName() + "!");
		switch(move.getMoveMetaCategory()) {
		case INFLICTS_DAMAGE : 
			execution.standardMove();
			break;
		case MULTI_HIT :
			execution.multiHit();
			break;
		case HEALS :
			execution.healUser();
			break;
		case CHANGE_STATS_ONLY :
			execution.changeStatsOnly();
			break;
		case STATUS_AILMENT_ONLY :
			execution.ailmentOnly();
			break;
		case INFLICTS_DAMAGE_AND_STATUS_AILMENT :
			execution.inflictDamageAndStatusAilment();
			break;
		case INFLICTS_DAMAGE_LOWER_TARGET_STATS :
			execution.inflictDamageAndChangeTargetStats();
			break;
		case INFLICTS_DAMAGE_RAISES_USER_STATS :
			execution.inflictDamageAndChangeUserStats();
			break;
		case INFLICTS_AILMENT_AND_STAT_CHANGE :
			execution.swaggerAndFlatter();
			break;
		case INFLICTS_AND_ABSORBS :
			execution.damageAndAbsorb();
			break;
		case OHKO :
			execution.onehitKO();
			break;
		case FORCES_TARGET_TO_SWITCH :
			execution.forceSwitchNoDamage();
			break;
		case DAMAGE_AND_FORCE_SWITCH :
			execution.damageAndForceSwitch();
			break;
		case FULL_FIELD_EFFECT :
			execution.getFullFieldEffect();
			break;
		case ONE_SIDE_FIELD_EFFECT :
			execution.getOneSideFieldEffect();
			break;
//		case UNIQUE_EFFECT :
//			
//			break;
		default :
			System.out.println(move.getMoveName() + " has a meta category of " + move.getMoveMetaCategory() + " and we don't have that coded.");
			break;
		}
//		if(bool) {
//			setBattlePlayerYou(execution.getAttacker());
//			setBattlePlayerEnemy(execution.getDefender());
//		}
//		else {
//			setBattlePlayerEnemy(execution.getAttacker());
//			setBattlePlayerYou(execution.getDefender());
//		}
		//TODO set this to battle...? how will we do this...
	}
	
	protected void setBattlePlayerYou(BattlingPokemon player) {
		this.mBattlePlayerYou = player;
	}
	
	protected void setBattlePlayerEnemy(BattlingPokemon player) {
		this.mBattlePlayerEnemy = player;
	}
	
	protected int getTrainerCommand() {
		Scanner scan = new Scanner(System.in);
		int choice = -1;
		
		do {
			System.out.println("What will you do?");
			System.out.println("(1) Fight");
			System.out.println("(2) Pokemon");
			System.out.println("(3) Items");
			System.out.println("(4) Run");
			
			System.out.println("Enter a number between 1 and 4");
			String input = scan.nextLine();
			Integer number = new Integer(input);
			if(number > 4 || number < 1) {
				System.out.println("1 through 4 jackass");
				getTrainerCommand();
			}
			else {
				choice = number;
			}
		} while(choice == -1);
		
		return choice;
	}

	protected Move getTrainerMove(BattlingPokemon pokemon) {
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
		System.out.println(weather.getBattleText());
	}

	public void setSport(Sport sport) {
		this.mSport = sport;
		System.out.println(sport.getBattleText());
	}

	public Sport getSport() {
		return mSport;
	}

	protected double getSpeedAbilityMod(BattlingPokemon pokemon) {
		double sm = 1;
		if(pokemon.isGastroAcid()) {
			return sm;
		}
		switch(pokemon.getAbility()) {
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

	protected double getSpeedItemMod(BattlingPokemon pokemon) {
		// TODO Work on this later, we don't have items yet.
		return 1;
	}

	protected double getPayalyzeMod(BattlingPokemon pokemon) {
		if(pokemon.getStatus() == NonVolatileStatusAilment.PARALYZE
				&& pokemon.getAbility() != Ability.QUICK_FEET) {
			return 0.25;
		}
		else {
			return 1;
		}
	}

	protected int getTailwindMod(BattlingPokemon pokemon) {
		// TODO Need to work in Tailwind into battle, for later.
		return 1;
	}
	
	public void setRoom(Room room) {
		this.mRoom = room;
	}
	
	public void setGravity(boolean bool) {
		this.mGravity = bool;
		if(bool) {
			System.out.println("Gravity's effect has been increased!");
		}
		else {
			System.out.println("Gravity returns to normal.");
		}
	}
	
//	private void handleFullFieldEffect(FullFieldEffect effect) {
//		/** 
//		 *  -1 = no effect, error shouldn't happen
//		 *   1 = weather
//		 *   2 = sport
//		 *   3 = gravity
//		 *   4 = room		  
//		 */
//		int flag = effect.getFlag();
//		switch(flag) {
//		case -1 :
//			throw new IllegalArgumentException();
//		case 1 :
//			weatherChange(effect);
//			break;
//		case 2 :
//			sportChange(effect);
//		case 3 :
//			changeGravity(true);
//			break;
//		case 4 :
//			roomChange(effect);
//			break;
//		}
//	}
//	
//	private void weatherChange(FullFieldEffect effect) {
//		//TODO timer for postround
//		switch(effect) {
//		case SUNNY :
//			mWeather = Weather.SUNNY_DAY;
//			System.out.println("The sun begins to shine brightly!");
//			break;
//		case RAIN :
//			mWeather = Weather.RAIN_DANCE;
//			System.out.println("It started to rain!");
//			break;
//		case HAIL :
//			mWeather = Weather.HAIL;
//			System.out.println("It started to hail!");
//			break;
//		case SANDSTORM :
//			mWeather = Weather.SANDSTORM;
//			System.out.println("A sandstorm has brewed!");
//			break;
//		}
//	}
//	
//	private void stopWeather(Weather weather) {
//		//TODO
//	}
//	
//	private void sportChange(FullFieldEffect effect) {
//		switch(effect) {
//		case MUD_SPORT :
//			mSport = Sport.MUD_SPORT;
//			System.out.println("Electricity's power weakened!");
//			break;
//		case WATER_SPORT :
//			mSport = Sport.WATER_SPORT;
//			System.out.println("Fire's power weakened!");
//			break;
//		}
//	}
//	
//	private void changeGravity(boolean b) {
//		//TODO timer for post
//		mGravity = b;
//	}
//	
//	private void roomChange(FullFieldEffect effect) {
//		//TODO timer
//		switch(effect) {
//		case TRICK_ROOM :
//			mRoom = Room.TRICK_ROOM;
//			System.out.println("Dimensions have been warped by the Trick Room!");
//			break;
//		case WONDER_ROOM :
//			mRoom = Room.WONDER_ROOM;
//			System.out.println("Dimensions have been warped by the Wonder Room!");
//			break;
//		case MAGIC_ROOM :
//			mRoom = Room.MAGIC_ROOM;
//			System.out.println("Dimensions have been warped by the Magic Room!");
//			break;
//		}
//	}
//	
////	private void handleOneSideFieldEffect(OneSideFieldEffect effect, boolean b) {
////		/** If b is true, side effect affects the user. If not, it affects the opponent. */
////		
////		if(b) {
////			mBattlePlayerYou.setSideFieldEffect(effect, b);
////		}
////		else if(!b) {
////			mBattlePlayerEnemy.setSideFieldEffect(effect, b);
////		}
////		else {
////			throw new IllegalArgumentException("Target for one side field effect not set");
////		}
////	}
	
	private void handleTimedStatusAilments(BattlingPokemon pokemon) {
		if(pokemon.getStatus() == NonVolatileStatusAilment.FREEZE) {
			int chanc = mGenerator.nextInt(100) + 1;
			if(chanc <= 20) {
				pokemon.setStatus(NonVolatileStatusAilment.NONE);
			}
		}
		
	}

}
