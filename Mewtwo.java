/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pokemon.mmo;

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
        genderRatio = GENDERLESS;
        setType1(11);
        setType2(0);
        String temp = GetSpeciesRow(150);
    }
}
