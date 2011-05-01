/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pokemon.mmo;

import com.pokemon.mmo.Enums.Types;

/**
 *
 * @author robosllim
 */
public class PokemonSpecies {

    public PokemonSpecies(){
        setBaseHP(120);
        setBaseAtk(120);
        setBaseDef(120);
        setBaseSpecA(120);
        setBaseSpecD(120);
        setBaseSpeed(120);
        genderRatio = -1;
        setType1(Types.NONE);
        setType2(Types.NONE);
        pokeName = "Missingno.";
        
        
    }

    public String GetSpeciesRow(int dexNum){
        // get a row from the database
        return "";
    }
    
    public String getSpeciesName() {
    	return pokeName;
    }

    public void setBaseHP(int baseHP) {
		this.baseHP = baseHP;
	}

	public int getBaseHP() {
		return baseHP;
	}

	public void setBaseAtk(int baseAtk) {
		this.baseAtk = baseAtk;
	}

	public int getBaseAtk() {
		return baseAtk;
	}

	public void setBaseDef(int baseDef) {
		this.baseDef = baseDef;
	}

	public int getBaseDef() {
		return baseDef;
	}

	public void setBaseSpecA(int baseSpecA) {
		this.baseSpecA = baseSpecA;
	}

	public int getBaseSpecA() {
		return baseSpecA;
	}

	public void setBaseSpecD(int baseSpecD) {
		this.baseSpecD = baseSpecD;
	}

	public int getBaseSpecD() {
		return baseSpecD;
	}

	public void setBaseSpeed(int baseSpeed) {
		this.baseSpeed = baseSpeed;
	}

	public int getBaseSpeed() {
		return baseSpeed;
	}

	public void setType1(Types type1) {
		this.type1 = type1;
	}

	public Types getType1() {
		return type1;
	}

	public void setType2(Types type2) {
		this.type2 = type2;
	}

	public Types getType2() {
		return type2;
	}

	public void setDexNumber(int dexNumber) {
		this.dexNumber = dexNumber;
	}

	public int getDexNumber() {
		return dexNumber;
	}

	public String pokeName;
    private int baseHP;
    private int baseAtk;
    private int baseDef;
    private int baseSpecA;
    private int baseSpecD;
    private int baseSpeed;
    private double genderRatio;
    // Types are, in order, 0=none, normal, fire, water, elec, grass, ice, fight,
    // poison, ground, flying, psychic, bug, rock, ghost, dragon, dark, steel=17
    private Types type1;
    private Types type2;
    private int dexNumber;
}

/*
My list of other things that will need to be filled in:

species (name and dex number)
type1
type2 (0 is none)
base stats (this will depend on our level up algorithm)
abilities and chances (thinking 40/40/20 for pokes with 2 normal and 1 DW ability; 70/30 otherwise)
moves:
  level up (array; level and move index)
  TM/HM
  egg
  special tutor moves (like blast burn)
catch rate (can use current formula for catching, or make our own)
egg steps
growth rate (exp needed for level 100; need to work out level up system)
egg type
gender ratio (integer representing male rate out of 100; -1 means "genderless")
weight
back sprite
front sprite
shiny back
shiny front
overworld sprites (front, back, left, right, plus shiny)
inventory sprite
cry sound effect*/