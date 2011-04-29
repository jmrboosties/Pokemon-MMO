package com.pokemon.mmo;

public class Trainer {

	private String mName;
	private int mId;
	private Pokemon mPokemon1;
	//INITIALIZE POKEMON WHEN YOU INITIALIZE TRAINER?
	
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
		//NORMALLY WOULD RETURN A DB QUERY
		return mPokemon1;
	}
	
	public void setPokemon1(Pokemon pokemon) {
		this.mPokemon1 = pokemon;
	}

}
