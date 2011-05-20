package com.pokemon.mmo;

public class Main {

	public static void main(String[] args) {
		PokemonSpecies[] speciesArray = PokemonSpeciesFactory.createSpeciesArray();
		System.out.println("Another test! Mew has base speed of " + speciesArray[151].getSpecificStat(5));
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
