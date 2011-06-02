package com.pokemon.mmo;

import java.util.HashMap;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.Types;

public class PokemonSpecies {

	private int[] mStats = new int[6]; // Use Enums.Stats to enter in specific
										// values.
	private Types[] mTypeArray = new Types[2];
	private double[] mCharacteristics = new double[4]; // Height, weight, color,
														// shape
	private double mGenderRatio; // Chances of a male. 100 = all male, 0 = all
									// female. -1 for genderless.

	private String mPokemonName;
	private String mSpeciesClass;
	private int mDexNum;

	private HashMap[] mMoveHashMaps = new HashMap[4]; // 0 is level up, 1 is
														// machines, 2 is egg
														// moves, 3 tutor moves.
	private Ability[] mAbilities = new Ability[3]; // 0 and 1 are regular
														// abilities, 2 is dream
														// world.

	public PokemonSpecies() {
		mPokemonName = "Missingno.";
		mSpeciesClass = "Hacker";
		for (int i = 0; i < mStats.length; i++) {
			mStats[i] = 120;
		}
		for (int i = 0; i < mTypeArray.length; i++) {
			mTypeArray[i] = Enums.Types.NONE;
		}
		mGenderRatio = -1;
		mDexNum = 0;
	}

	public String getSpeciesName() {
		return mPokemonName;
	}

	public void setSpeciesName(String name) {
		this.mPokemonName = name;
	}

	public void setSpeciesClass(String speciesClass) {
		this.mSpeciesClass = speciesClass;
	}

	public String getSpeciesClass() {
		return mSpeciesClass;
	}

	public void setDexNumber(int dexNumber) {
		this.mDexNum = dexNumber;
	}

	public int getDexNumber() {
		return mDexNum;
	}

	public void setTypes(Types[] types) {
		for (int i = 0; i < types.length; i++) {
			mTypeArray[i] = types[i];
		}
	}

	public Types getType(int slot) {
		return mTypeArray[slot];
	}

	public Types[] getTypeArray() {
		return mTypeArray;
	}

	public void setStats(int[] stats) {
		for (int i = 0; i < stats.length; i++) {
			mStats[i] = stats[i];
		}
	}

	public int getSpecificStat(int stat) {
		return mStats[stat];
	}

	public int[] getStatArray() {
		return mStats;
	}

	public void setAbilityArray(Ability[] array) {
		this.mAbilities = array;
	}

	public void setSingleAbility(Ability ability, int index) {
		this.mAbilities[index] = ability;
	}

	public Ability getAbility(int index) {
		return mAbilities[index];
	}

	public Ability[] getAbilityArray() {
		return mAbilities;
	}

	public void setCharacteristics(int[] chars) {
		for (int i = 0; i < chars.length; i++) {
			mCharacteristics[i] = chars[i];
		}
	}

	public double getSpecificChar(int charac) {
		return mCharacteristics[charac];
	}

	public double[] getCharArray() {
		return mCharacteristics;
	}

	public void setGenderRatio(double value) {
		this.mGenderRatio = value;
	}

	public double getGenderRatio() {
		return mGenderRatio;
	}

	public void setHashMap(int hashMap, HashMap map) {
		this.mMoveHashMaps[hashMap] = map;
	}

	public HashMap getHashMap(int hashMap) {
		return mMoveHashMaps[hashMap];
	}

	public HashMap[] getLearnableMoveArray() {
		return mMoveHashMaps;
	}

	public void clearValues() {
		mPokemonName = "Missingno.";
		for (int i = 0; i < mStats.length; i++) {
			mStats[i] = 120;
		}
		for (int i = 0; i < mTypeArray.length; i++) {
			mTypeArray[i] = Enums.Types.NONE;
		}
		mGenderRatio = -1;
		mDexNum = 0;
	}
}

/*
 * My list of other things that will need to be filled in:
 * 
 * species (name and dex number) type1 type2 (0 is none) base stats (this will
 * depend on our level up algorithm) abilities and chances (thinking 40/40/20
 * for pokes with 2 normal and 1 DW ability; 70/30 otherwise) moves: level up
 * (array; level and move index) TM/HM egg special tutor moves (like blast burn)
 * catch rate (can use current formula for catching, or make our own) egg steps
 * growth rate (exp needed for level 100; need to work out level up system) egg
 * type gender ratio (integer representing male rate out of 100; -1 means
 * "genderless") weight back sprite front sprite shiny back shiny front
 * overworld sprites (front, back, left, right, plus shiny) inventory sprite cry
 * sound effect
 */