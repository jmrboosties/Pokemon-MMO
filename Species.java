package com.pokemon.mmo;

public class Species {
  private String mName;
  private int mDexNum;
  private int mType1;
  private int mType2;
  private int[] mBaseStats = {0,0,0,0,0,0};
  private int[] mAbilities = new int[3];
  private int[][] mLevelMoves = new int[100][20]; 	// need level and move index here
  private int[] mTmMoves = new int[100]; 		// Mew will be the only one to fill this up
  private int[] mEggMoves = new int[30];
  private int[] mTutorMoves = new int[30];		// These are special moves like Blast Burn, Draco Meteor, etc.
  private int catchRate;
  private int eggSteps;
  private int growthRate;			// Currently, this is usually explained as the level 100 experience
  private int eggType;
  private int genderRatio;			// Ratio for male; 0 is always female, 100 is always male, -1 is genderless
  private float weight;
  // These will end up being actual images... I think
  private String backSprite;
  private String frontSprite;
  private String backSpriteSh;
  private String frontSpriteSh;
  private String[] overworldSprites = new String[8]; // up, down, left, right; both normal and shiny
  private String inventorySprite;
  // And this should be a sound file
  private  String crySound;
  
  Species(){
    mName = "Missingno.";
	mDexNum = 999;
	mType1 = -1;
	mType2 = -1;
	
	mBaseStats[0] = 10;
	//mAbilities = [-1,-1,-1];
	for (int i = 0; i < 20; i++){
	  levelMoves[i][0] = 0;
	  levelMoves[i][1] = 0;
	}
	for (int i = 0; i < 110; i++){
	  tmMoves[i] = 0;
	}
	for (int i = 0; i < 30; i++){
	  eggMoves[i] = 0;
	}
	for (int i = 0; i < 30; i++){
	  tutorMoves[i] = 0;
	}
	catchRate = 0;
	eggSteps = 0;
	growthRate = 0;
	eggType = 0;
	genderRatio = -1;
	weight = 0.00;
	// These should actually open some files, based on pre-defined data (ie open path/images/dexNum.png)
	backSprite = "null";
	frontSprite = "null";
	backSpriteSh = "null";
	frontSpriteSh = "null";
	overworldSprites = null;//["null","null","null","null","null","null","null","null"];
	inventorySprite = null;//"null";
	crySound = null;//"null";
  }
}