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
public class Squirtle extends PokemonSpecies {
    public Squirtle(){
        setBaseHP(44);
        setBaseAtk(48);
        setBaseDef(65);
        setBaseSpecA(50);
        setBaseSpecD(64);
        setBaseSpeed(43);
        //genderRatio = 87.5;
        setType1(Types.WATER);
        setType2(Types.NONE);
        pokeName = "Squirtle";
        String temp = GetSpeciesRow(7);
    }
}
