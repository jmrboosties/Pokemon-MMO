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
public class Bulbasaur extends PokemonSpecies{
    public Bulbasaur(){
        setBaseHP(45);
        setBaseAtk(49);
        setBaseDef(49);
        setBaseSpecA(65);
        setBaseSpecD(65);
        setBaseSpeed(45);
        genderRatio = 87.5;
        setType1(Types.GRASS);
        setType2(Types.POISON);
        pokeName = "Bulbasaur";
        String temp = GetSpeciesRow(1);
    }
}