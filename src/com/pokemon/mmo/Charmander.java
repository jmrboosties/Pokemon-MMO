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
public class Charmander extends PokemonSpecies {
    
	public Charmander(){
        setBaseHP(39);
        setBaseAtk(52);
        setBaseDef(43);
        setBaseSpecA(60);
        setBaseSpecD(50);
        setBaseSpeed(65);
        //genderRatio = 87.5;
        setType1(Types.FIRE);
        setType2(Types.NONE);
        pokeName = "Charmander";
        setDexNumber(4);
        String temp = GetSpeciesRow(4);
        //Set available abilities and their % chance of getting...
        
    }
}
