package com.pokemon.mmo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.EggGroup;
import com.pokemon.mmo.Enums.Stats;
import com.pokemon.mmo.Enums.Types;

public class PokemonSpecies {

	/******************************************/
	/********     Member Variables     ********/
	/******************************************/

	// Base Info
	private int mPokedexNo = 0;
	private String mPokemonName = "";
	private String mSpecies = "";
	private int mHeight = 0;
	private int mWeight = 0;
	private int mShape = 0;
	private byte mColor = 0;

	// Base Stats
	private short mHP = 120;
	private short mAttack = 120;
	private short mDefense = 120;
	private short mSpAttack = 120;
	private short mSpDefense = 120;
	private short mSpeed = 120;

	// Types
	private byte mType1 = 0;
	private byte mType2 = 0;

	// Battling
	private short mBaseExp = 0;
	private byte mEffortHP = 0;
	private byte mEffortAttack = 0;
	private byte mEffortDefense = 0;
	private byte mEffortSpeed = 0;
	private byte mEffortSpAttack = 0;
	private byte mEffortSpDefense = 0;
	private short mAbility1 = 0;
	private short mAbility2 = 0;
	private short mAbility3 = 0;
	private short mHappiness = 0;
	private byte mLevelUpRate = 0;

	// Breeding and Catching
	private short mGender = 0xFF;
	private short mEggCycles = 0;
	private byte mEggGroup1 = 0;
	private byte mEggGroup2 = 0;
	private short mCatchRate = 0;
	private int mItem1 = 0;
	private int mItem2 = 0;

	// Moves
	private HashMap<Integer, Integer> mLevelMoves = new HashMap<Integer, Integer>();
	private HashMap<Integer, Boolean> mEggMoves = new HashMap<Integer, Boolean>();
	private HashMap<Integer, Boolean> mTutorMoves = new HashMap<Integer, Boolean>();
	private HashMap<Integer, Boolean> mTMMoves = new HashMap<Integer, Boolean>();





	/**************************************/
	/********     Constructors     ********/
	/**************************************/

	public PokemonSpecies() {}

	public PokemonSpecies(ResultSet info) throws Exception, SQLException {
		//System.out.println("BasePokemon(ResultSet)");

		// Base Info
		mPokedexNo = info.getInt("id");
		mPokemonName = info.getString("name");
		mSpecies = info.getString("species");
		mHeight = info.getInt("height");
		mWeight = info.getInt("weight");
		mShape = info.getInt("pokemon_shape_id");
		mColor = info.getByte("color_id");

		// Base Stats
		mHP = info.getShort("base_hp");
		mAttack = info.getShort("base_attack");
		mDefense = info.getShort("base_defense");
		mSpAttack = info.getShort("base_spattack");
		mSpDefense = info.getShort("base_spdefense");
		mSpeed = info.getShort("base_speed");

		// Types
		mType1 = info.getByte("type1");
		mType2 = info.getByte("type2");

		// Battling
		mBaseExp = info.getShort("base_experience");
		mEffortHP = info.getByte("effort_hp");
		mEffortAttack = info.getByte("effort_attack");
		mEffortDefense = info.getByte("effort_defense");
		mEffortSpeed = info.getByte("effort_speed");
		mEffortSpAttack = info.getByte("effort_spattack");
		mEffortSpDefense = info.getByte("effort_spdefense");
		mAbility1 = info.getShort("ability1");
		mAbility2 = info.getShort("ability2");
		mAbility3 = info.getShort("ability3");
		mHappiness = info.getShort("base_happiness");
//		mLevelUpRate = 0;

		// Breeding and Catching
		switch (info.getShort("gender_rate")) {
			// NOTE:  there are no 25% male, 75% female pokemon (which would be 0xBF)
			case -1:			// Genderless
				mGender = (short) 0xFF;
				break;
			case 0:				// All male
				mGender = (short) 0x00;
				break;
			case 1:				// Mostly male
				mGender = (short) 0x1F;
				break;
			case 2:				// More than half male
				mGender = (short) 0x3F;
				break;
			case 4:				// Half male, half female
				mGender = (short) 0x7F;
				break;
			case 6:				// Mostly female
				mGender = (short) 0xDF;
				break;
			case 8:				// All female
				mGender = (short) 0xFE;
				break;
			default:
				throw new Exception();
		}
//		mEggCycles = 0;				// hatch_counter does not match Bulbapedia
		mEggGroup1 = info.getByte("egggroup1");
		mEggGroup2 = info.getByte("egggroup2");
		mCatchRate = info.getShort("capture_rate");
//		mItem1 = 0;
//		mItem2 = 0;

		
	}
	
	public void setMoves(ResultSet moves) {
		try {
			while(moves.next()) {
				switch (moves.getInt("pokemon_move_method_id")) {
					case 1:
						mLevelMoves.put(new Integer(moves.getInt("level")), new Integer(moves.getInt("move_id")));
						break;
					case 2:
						mEggMoves.put(new Integer(moves.getInt("move_id")), new Boolean(true));
						break;
					case 3:
						mTutorMoves.put(new Integer(moves.getInt("move_id")), new Boolean(true));
						break;
					case 4:
						mTMMoves.put(new Integer(moves.getInt("move_id")), new Boolean(true));
						break;
					default:
						throw new Exception();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printPokemon() throws Exception {
		System.out.println();
		System.out.println("Pokedex No.:\t" + mPokedexNo);
		System.out.println("Pokemon Name:\t" + mPokemonName);
		System.out.println("Species Name:\t" + mSpecies);
		System.out.println("Height:\t\t" + mHeight);
		System.out.println("Weight:\t\t" + mWeight);
		System.out.println("Shape:\t\t" + mShape);
		System.out.println("Color:\t\t" + mColor);
		if (mGender < 0) {
			System.out.println("Gender:\t\t" + mGender);
			throw new Exception();
		}
	}


















	/***********************************/
	/********     Accessors     ********/
	/***********************************/

	// Base Info
	public int getDexNumber()			{ return mPokedexNo; }
	public String getSpeciesName()			{ return mPokemonName; }
	public String getSpeciesClass()			{ return mSpecies; }
	public int getHeight()				{ return mHeight; }
	public int getWeight()				{ return mWeight; }
	public int getShape()				{ return mShape; }
	public int getColor()				{ return mColor; }


	// Base Stats
	public int getSpecificStat(Stats stat)		{ return this.getStatArray()[stat.ordinal()]; }
	public int[] getStatArray() {
		int[] stats = new int[6];
		stats[0] = mHP;
		stats[1] = mAttack;
		stats[2] = mDefense;
		stats[3] = mSpAttack;
		stats[4] = mSpDefense;
		stats[5] = mSpeed;
		return stats;
	}
	public int getBaseHP()				{ return mHP; }
	public int getBaseAttack()			{ return mAttack; }
	public int getBaseDefense()			{ return mDefense; }
	public int getBaseSpAttack()			{ return mSpAttack; }
	public int getBaseSpDefense()			{ return mSpDefense; }
	public int getBaseSpeed()			{ return mSpeed; }


	// Types
	public Types getType(int slot)			{ return this.getTypeArray()[slot - 1]; }	// indexed to 1
	public Types[] getTypeArray() {
		Types[] types = new Types[2];
		types[0] = Types.getType(mType1);
		types[1] = Types.getType(mType2);
		return types;
	}
	public Types getType1()				{ return Types.getType(mType1); }
	public Types getType2()				{ return Types.getType(mType2); }


	// Battling
	public int getBaseExp()				{ return mBaseExp; }
	public int getEffortHP()			{ return mEffortHP; }
	public int getEffortAttack()			{ return mEffortAttack; }
	public int getEffortDefense()			{ return mEffortDefense; }
	public int getEffortSpAttack()			{ return mEffortSpAttack; }
	public int getEffortSpDefense()			{ return mEffortSpDefense; }
	public int getEffortSpeed()			{ return mEffortSpeed; }
	public int[] getEffortArray() {
		int[] effort = new int[6];
		effort[0] = mEffortHP;
		effort[1] = mEffortAttack;
		effort[2] = mEffortDefense;
		effort[3] = mEffortSpAttack;
		effort[4] = mEffortSpDefense;
		effort[5] = mEffortSpeed;
		return effort;
	}
	public Ability getAbility(int index)		{ return this.getAbilityArray()[index]; }
	public Ability getDreamAbility()		{ return Ability.getAbility(mAbility3); }
	public Ability[] getAbilityArray() {
		Ability[] abilities = new Ability[3];
		abilities[0] = Ability.getAbility(mAbility1);
		abilities[1] = Ability.getAbility(mAbility2);
		abilities[2] = Ability.getAbility(mAbility3);
		return abilities;
	}
	public int getHappiness()			{ return mHappiness; }
	public int getLevelUpRate()			{ return mLevelUpRate; }


	// Breeding and Catching
	public double getGenderRatio() {
		if (mGender == 0xFF) {
			return -1;
		} else {
			return mGender/256.;
		}
	}
	public int getGender()				{ return mGender; }
	public int EggCycles()				{ return mEggCycles; }
	public List<EggGroup> getEggGroupList() {
		LinkedList<EggGroup> egg_groups = new LinkedList<EggGroup>();
		egg_groups.add(EggGroup.getEggGroup(mEggGroup1));
		egg_groups.add(EggGroup.getEggGroup(mEggGroup2));
		return egg_groups;
	}
	public EggGroup getEggGroup1()			{ return EggGroup.getEggGroup(mEggGroup1); }
	public EggGroup getEggGroup2()			{ return EggGroup.getEggGroup(mEggGroup2); }
	public int CatchRate()				{ return mCatchRate; }
	public int Item1()				{ return mItem1; }
	public int Item2()				{ return mItem2; }


	// Moves
	public HashMap[] getLearnableMoveArray() {
		HashMap[] move_maps = new HashMap[4];
		move_maps[0] = mLevelMoves;
		move_maps[1] = mEggMoves;
		move_maps[2] = mTutorMoves;
		move_maps[3] = mTMMoves;
		return move_maps;
	}
	public HashMap getHashMap(int hashMap)		{ return this.getLearnableMoveArray()[hashMap]; }
	public HashMap getLevelMoves()			{ return mLevelMoves; }
	public HashMap getEggMoves()			{ return mEggMoves; }
	public HashMap getTutorMoves()			{ return mTutorMoves; }
	public HashMap getTMMoves()			{ return mTMMoves; }



	// Boolean Tests
	public boolean isDualType() {
		if(this.getType2() == Types.NONE) {
			return false;
		}
		return true;
	}

	public boolean hasDreamAbility() {
		if(this.getDreamAbility() != Ability.NONE) {
			return true;
		}
		return false;
	}

//	// Other
//	public void clearValues() {
//		mPokemonName = "Missingno.";
//		for (int i = 0; i < mStats.length; i++) {
//			mStats[i] = 120;
//		}
//		for (int i = 0; i < mTypeArray.length; i++) {
//			mTypeArray[i] = Enums.Types.NONE;
//		}
//		mGenderRatio = -1;
//		mDexNum = 0;
//	}






//	// Set
//	public void setSpeciesName(String name)				{ this.mPokemonName = name; }
//	public void setSpeciesClass(String speciesClass)		{ this.mSpeciesClass = speciesClass; }
//	public void setDexNumber(int dexNumber)				{ this.mDexNum = dexNumber; }
//	public void setTypes(Types[] types)				{ this.mTypeArray = types; }
//	public void setType(int slot, Types types)			{ this.mTypeArray[slot-1] = types; }
//	public void setAbilityArray(Ability[] array)			{ this.mAbilities = array; }
//	public void setSingleAbility(Ability ability, int index)	{ this.mAbilities[index] = ability; }
//	public void setDreamAbility(Ability ability)			{ this.mDreamWorldAbility = ability; }
//	public void addEggGroup(EggGroup group)				{ mEggGroups.add(group); }
//	public void setGenderRatio(double value)			{ this.mGenderRatio = value; }
//	public void setHashMap(int hashMap, HashMap map)		{ this.mMoveHashMaps[hashMap] = map; }

//	public void setStats(int[] stats) {
//		for (int i = 0; i < stats.length; i++) {
//			mStats[i] = stats[i];
//		}
//	}

//	public void setCharacteristics(int[] chars) {
//		for (int i = 0; i < chars.length; i++) {
//			mCharacteristics[i] = chars[i];
//		}
//	}
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
