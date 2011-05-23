package com.pokemon.mmo;

import java.util.Random;

public class Main {
	
	public static PokemonSpecies[] mSpeciesArray;
	public static Move[] mMoveArray;

	public static void main(String[] args) {
		mSpeciesArray = PokemonSpeciesFactory.createSpeciesArray();
		mMoveArray = MoveFactory.createMoveArray();
		
		Random generator = new Random();
		int val = generator.nextInt(649) + 1;
		PokemonSpecies species = Main.mSpeciesArray[val];
		Pokemon wildPokemon = PokemonFactory.getPokemon(species);
		Move move = mMoveArray[val];
		System.out.println("Wild " + wildPokemon.getNickName() + " appeared!");
		System.out.println(move.getMoveName() + " has a base power of " + move.getBasePower() + " and an accuracy of " + move.getAccuracy());
	}
	
	
	/*public static void main(String[] args) {    	    	
    	
    	System.out.println("IT'S ELF'S WORLD!!");
    	System.out.println("Trainer, what is your name?");
    	
    	Trainer trainer = new Trainer();
    	
    	String input = "";
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	
    	try {
    		input = reader.readLine();
    		trainer.setName(input);
    	}
    	catch(IOException ioe) {
    		System.out.println("Exception.");
    	}
    	
    	System.out.println(trainer.getName() + ", here is a Charmander. Give your Charmander a nickname?");
    	
    	PokemonSpecies mander = PokemonSpeciesFactory.getPokemonSpecies(4);
    	Pokemon myPokemon = PokemonFactory.getCharmander(mander);
    	
    	try {
    		input = reader.readLine();
    		myPokemon.setNickName(input);    		
    	} catch(IOException e) {
    		System.out.println("Failure.");
    	}
    	
    	System.out.println("From now on this little guy is named " + myPokemon.getNickName() + "!");
    	
    	trainer.setPokemon1(myPokemon);
    	
    	System.out.println("Oh no! Here comes a wild one! This is a demo so just let your pokemon do the work.");
    	
    	WildBattle battle = new WildBattle(trainer);
    	boolean result = battle.wildBattleThread();
    	if(result) {
    		System.out.println("Congrats on the win!");
    	}
    	else {
    		System.out.println("You suck.");
    	}
    	
    }*/

}
