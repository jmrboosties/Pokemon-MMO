/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pokemon.mmo;

/**
 *
 * @author robosllim
 */
public class Bulbasaur extends PokemonSpecies{
    public Bulbasaur(){
        baseHP = 45;
        baseAtk = 49;
        baseDef = 49;
        baseSpecA = 65;
        baseSpecD = 65;
        baseSpeed = 45;
        genderRatio = 87.5;
        type1 = 5;
        type2 = 8;
        pokeName = "Bulbasaur";
        String temp = GetSpeciesRow(1);
    }
}