/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pokemon.mmo;

/**
 *
 * @author robosllim
 */
public class PokemonSpecies {

    public PokemonSpecies(){
        baseHP = 1;
        baseAtk = 1;
        baseDef = 1;
        baseSpecA = 1;
        baseSpecD = 1;
        baseSpeed = 1;
        genderRatio = GENDERLESS;
        type1 = 1;
        type2 = 0;
        pokeName = "Missingno.";
    }

    public String GetSpeciesRow(int dexNum){
        // get a row from the database
        return "";
    }

    public String pokeName;
    public int baseHP;
    public int baseAtk;
    public int baseDef;
    public int baseSpecA;
    public int baseSpecD;
    public int baseSpeed;
    public double genderRatio;
    // Types are, in order, 0=none, normal, fire, water, elec, grass, ice, fight,
    // poison, ground, flying, psychic, bug, rock, ghost, dragon, dark, steel=17
    public int type1;
    public int type2;

    public static final int GENDERLESS = -1;
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