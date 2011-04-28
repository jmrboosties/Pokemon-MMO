/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pokemon.mmo;

/**
 *
 * @author robosllim
 */
public class Charmander extends PokemonSpecies{
    public Charmander(){
        baseHP = 39;
        baseAtk = 52;
        baseDef = 43;
        baseSpecA = 60;
        baseSpecD = 50;
        baseSpeed = 65;
        genderRatio = 87.5;
        type1 = 2;
        type2 = 0;
        pokeName = "Charmander";
        String temp = GetSpeciesRow(4);
    }
}
