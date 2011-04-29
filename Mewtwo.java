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
public class Mewtwo extends PokemonSpecies {
    public Mewtwo(){
        setBaseHP(106);
        setBaseAtk(110);
        setBaseDef(90);
        setBaseSpecA(154);
        setBaseSpecD(90);
        setBaseSpeed(130);
        //genderRatio = GENDERLESS;
        setType1(Types.PSYCHIC);
        setType2(Types.NONE);
        String temp = GetSpeciesRow(150);
    }
}
