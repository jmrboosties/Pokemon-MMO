package com.pokemon.mmo;

import com.pokemon.mmo.Enums.NonVolatileStatusAilment;

public class Trainer {

	private String mName;
	private int mId;
	private Pokemon mLeadingPokemon;

	// INITIALIZE POKEMON WHEN YOU INITIALIZE TRAINER?

	public Trainer() {
		this.mName = "";
	}

	public void setName(String name) {
		this.mName = name;
	}

	public String getName() {
		return mName;
	}

	public int getTrainerId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Pokemon getLeadingPokemon() {
		// NORMALLY WOULD RETURN A DB QUERY
		return mLeadingPokemon;
	}

	public void setLeadingPokemon(Pokemon pokemon) {
		if(pokemon.getStatus() == NonVolatileStatusAilment.FAINTED) {
			throw new IllegalArgumentException("Can't choose fainted pokemon, shouldn't get here.");
		}
		this.mLeadingPokemon = pokemon;
	}
	
	public Pokemon selectRandomPokemon() {
		RandomForPokemon randy = new RandomForPokemon();
		Pokemon pokemon = randy.randomPokemon(20);
		System.out.println(mName + " sends out " + pokemon.getNickName());
		return pokemon;
	}
	
	public Pokemon selectPokemon() {
		return Main.choosePokemon();
	}

}
