package com.pokemon.mmo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

//import com.pokemon.mmo.Battle.Sport;
import com.pokemon.mmo.Enums.ModdableBattleStats;
import com.pokemon.mmo.Enums.MoveEffectGroup;
import com.pokemon.mmo.Enums.MoveKinds;
import com.pokemon.mmo.Enums.MoveMetaCategory;
import com.pokemon.mmo.Enums.MoveSecondaryNonVolatileEffect;
import com.pokemon.mmo.Enums.MoveSecondaryVolatileEffect;
import com.pokemon.mmo.Enums.StatusAilment;
import com.pokemon.mmo.Enums.Types;

public class MoveFactory {

	public static Move[] createMoveArray() {
		Move[] moveArray = new Move[560];
		moveArray[0] = new Move();
		DbAdapter adapter;
		ResultSet rs;

		try {
			adapter = new DbAdapter();
			for (int i = 1; i < moveArray.length; i++) {
				Move move = new Move();

				rs = adapter.makeQuery("SELECT * FROM moves WHERE id = '"
						+ String.valueOf(i) + "'");
				while (rs.next()) {
					move.setMoveName(rs.getString("name"));
					move.setBasePower(rs.getInt("power"));
					move.setAccuracy(rs.getInt("accuracy"));
					move.setMoveId(rs.getInt("id"));
					move.setPP(rs.getInt("pp"));
					move.setPriority(rs.getInt("priority"));
					move.setKind(MoveKinds.getMoveKind(rs.getInt("damage_class_id")));
					move.setType(Types.getType(rs.getInt("type_id")));
					move.setMoveMetaCategory(MoveMetaCategory.getCategory(rs.getInt("meta_category_id")));
					move.setStatusAilment(StatusAilment.getAilment(rs.getInt("meta_ailment_id")));
					move.setMoveEffect(rs.getInt("effect_id"));
					
					move.setMaxHits(rs.getInt("max_hits"));
					move.setMinHits(rs.getInt("min_hits"));
					
					move.setMaxTurns(rs.getInt("max_turns"));
					move.setMinTurns(rs.getInt("min_turns"));
					
					int[] stats = new int[7];
					stats[0] = rs.getInt("attack_change");
					stats[1] = rs.getInt("defense_change");
					stats[2] = rs.getInt("spattack_change");
					stats[3] = rs.getInt("spdefense_change");
					stats[4] = rs.getInt("speed_change");
					stats[5] = rs.getInt("accuracy_change");
					stats[6] = rs.getInt("evasion_change");
					move.setMoveStatChangesArray(stats);
					
				}
				int effect = move.getMoveEffect();
				if(effect == 30 || effect == 45 || effect == 78 || effect == 105) {
					move.setMoveMetaCategory(MoveMetaCategory.MULTI_HIT);
				}
				
				if(move.getMoveId() == 251) {
					move.setMoveMetaCategory(MoveMetaCategory.UNIQUE_EFFECT);
				}
				moveArray[i] = move;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return moveArray;
	}
	
	private static List<Integer> getMoveEffectIdList(MoveEffectGroup group) {
		return null;
	}
	
	public static MoveEffectGroup getMoveEffectGroup(Move move) {
		int effectId = move.getMoveCode();
		switch(effectId) {
		case 1 : 
			
			return MoveEffectGroup.REGULAR;
		case 2 :
			
		}
		return null;
	}
	
//	private static void setMoveNonVolatileEffect(ResultSet rs, Move move) {
//		int effectId = 0;
//		try {
//			effectId = rs.getInt("effect_id");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		switch (effectId) {
//		
//		/**Non-Volatile Status Effects*/
//		case 2 :
//			move.setNonVolatileEffect(MoveSecondaryNonVolatileEffect.SLEEPS);
//			break;
//		case 3 :
//		case 67 :
//		case 78 :
//		case 210 :
//			move.setNonVolatileEffect(MoveSecondaryNonVolatileEffect.POISONS);
//			break;
//		case 34 :
//		case 203 :
//			move.setNonVolatileEffect(MoveSecondaryNonVolatileEffect.BADLY_POISONS);
//			break;
//		case 5 :
//		case 126 :
//		case 168 :
//		case 201 :
//		case 254 :
//		case 274 :
//			move.setNonVolatileEffect(MoveSecondaryNonVolatileEffect.BURNS);
//			break;
//		case 6 :
//		case 261 :
//		case 275 :
//			move.setNonVolatileEffect(MoveSecondaryNonVolatileEffect.FREEZES);
//			break;
//		case 7 :
//		case 68 :
//		case 153 :
//		case 263 :
//		case 276 :
//			move.setNonVolatileEffect(MoveSecondaryNonVolatileEffect.PARALYZES);
//			break;
//		case 37 :
//			move.setNonVolatileEffect(MoveSecondaryNonVolatileEffect.TRI_ATTACK);
//			break;
//			
//		/**Positive Stat Changes*/
//		case 11 :
//		case 140 : //This is not like howl... I need to change this around.
//			move.setMoveStatChanges(ModdableBattleStats.ATTACK, 1);
//			break;
//		case 12 :
//		case 139 : //Steel wing, you attack target and own stat goes up. Maybe need a diff stat mod thing for user. good idea actually.
//		case 146 : //Same
//		case 157 : //defense curl, diff cause its a battle stat thing for rollout but belongs here.
//			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, 1);
//			break;
//		case 14 : //dead, used to be growth but that now does both attacks, leave it though
//		case 167 : //for flatter, works leave it here
//		case 277 : //TODO hit target raises user stats, like steel wing
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_ATTACK, 1);
//			break;
//		case 175 : //user, is fine
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_DEFENSE, 1);
//		case 17 :
//			move.setMoveStatChanges(ModdableBattleStats.EVASION, 1);
//			break;
//		case 51 :
//		case 119 :
//			move.setMoveStatChanges(ModdableBattleStats.ATTACK, 2);
//			break;
//		case 52 :
//			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, 2);
//			break;
//		case 53 :
//		case 285 :
//			move.setMoveStatChanges(ModdableBattleStats.SPEED, 2);
//			break;
//		case 54 :
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_ATTACK, 2);
//			break;
//		case 55 :
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_DEFENSE, 2);
//			break;
//		case 109 :
//			move.setMoveStatChanges(ModdableBattleStats.EVASION, 2);
//			break;
//		case 141 : //ancient power, hits target helps user
//			int[] ancientPower = {1,1,1,1,1,0,0};
//			move.setMoveStatChangesArray(ancientPower);
//			break;
//		case 143 :
//			move.setMoveStatChanges(ModdableBattleStats.ATTACK, 6);
//			break;
//		case 207 : //OK
//			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, 1);
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_DEFENSE, 1);
//			break;
//		case 209 :
//			move.setMoveStatChanges(ModdableBattleStats.ATTACK, 1);
//			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, 1);
//			break;
//		case 212 :
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_ATTACK, 1);
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_DEFENSE, 1);
//			break;
//		case 213 :
//			move.setMoveStatChanges(ModdableBattleStats.ATTACK, 1);
//			move.setMoveStatChanges(ModdableBattleStats.SPEED, 1);
//			break;
//		case 227 : //Acupressure
//			Random generator = new Random();
//			int i = generator.nextInt(7);
//			ModdableBattleStats stat = ModdableBattleStats.getStat(i);
//			move.setMoveStatChanges(stat, 2);
//			break;
//		case 278 :
//			move.setMoveStatChanges(ModdableBattleStats.ATTACK, 1);
//			move.setMoveStatChanges(ModdableBattleStats.ACCURACY, 1);
//			break;
//		case 291 :
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_ATTACK, 1);
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_DEFENSE, 1);
//			move.setMoveStatChanges(ModdableBattleStats.SPEED, 1);
//			break;
//		case 296 : //TODO hit target raise user speed
//			move.setMoveStatChanges(ModdableBattleStats.SPEED, 1);
//			
//		/**Negative Stat Changes*/
//		case 19 : 
//		case 69 :
//			move.setMoveStatChanges(ModdableBattleStats.ATTACK, -1);
//			break;
//		case 20 :
//		case 70 :
//			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, -1);
//			break;
//		case 72 :
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_ATTACK, -1);
//			break;
//		case 73 :
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_DEFENSE, -1);
//			break;
//		case 21 :
//		case 71 :
//		case 219 : //TODO hits target affects user, group diff
//			move.setMoveStatChanges(ModdableBattleStats.SPEED, -1);
//			break;
//		case 24 :
//		case 74 :
//			move.setMoveStatChanges(ModdableBattleStats.ACCURACY, -1);
//			break;
//		case 25 :
//			move.setMoveStatChanges(ModdableBattleStats.EVASION, -1);
//			break;
//		case 59 :
//			move.setMoveStatChanges(ModdableBattleStats.ATTACK, -2);
//			break;
//		case 60 :
//			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, -2);
//			break;
//		case 61 :
//			move.setMoveStatChanges(ModdableBattleStats.SPEED, -2);
//			break;
//		case 63 :
//		case 272 :
//		case 297 :
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_DEFENSE, -2);
//			break;
//		case 169 : //prob do sep case
//			move.setMoveStatChanges(ModdableBattleStats.ATTACK, -2);
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_ATTACK, -2);
//			break;
//		case 206 :
//			move.setMoveStatChanges(ModdableBattleStats.ATTACK, -1);
//			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, -1);
//			break;
//		case 217 :
//			move.setMoveStatChanges(ModdableBattleStats.EVASION, -6);
//			break;
//		case 230 : //TODO hits target affects user
//			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, -1);
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_DEFENSE, -1);
//			break;
//		case 259 :
//			move.setMoveStatChanges(ModdableBattleStats.EVASION, -1);
//			break;
//		case 266 :
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_ATTACK, -2);
//			break;
//		case 309 : //SHELL SMASH HITS USER
//			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_DEFENSE, -1);
//			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, -1);
//			break;
//			
//		
//		/**Volatile Status Effects*/
//		case 32 :
//		case 76 :
//		case 93 :
//		case 147 :
//		case 151 :
//		case 159 :
////		case 274 :
////		case 275 :
////		case 276 :
//			move.setVolatileEffect(MoveSecondaryVolatileEffect.FLINCHES);
//			break;
//		case 50 :
//		case 77 :
////		case 119 :
//		case 200 :
//			move.setVolatileEffect(MoveSecondaryVolatileEffect.CONFUSES);
//			break;
//		case 85 :
//			move.setVolatileEffect(MoveSecondaryVolatileEffect.LEECH_SEEDS);
//		case 108 :
//			move.setVolatileEffect(MoveSecondaryVolatileEffect.NIGHTMARES);
//			break;
//		case 110 :
//			move.setVolatileEffect(MoveSecondaryVolatileEffect.CURSES);
//			break;
//		case 114 :
//			move.setVolatileEffect(MoveSecondaryVolatileEffect.IDENTIFIES);
//			break;
//		case 115 :
//			move.setVolatileEffect(MoveSecondaryVolatileEffect.PERISH_SONG);
//			break;
//		case 121 :
//			move.setVolatileEffect(MoveSecondaryVolatileEffect.ATTRACTS);
//			break;
//		case 176 :
//			move.setVolatileEffect(MoveSecondaryVolatileEffect.TAUNTS);
//			break;
//		case 188 :
//			move.setVolatileEffect(MoveSecondaryVolatileEffect.YAWNS);
//			break;
//		case 262 :
//			move.setVolatileEffect(MoveSecondaryVolatileEffect.PARTIALLY_TRAPS);
//			break;
//		case 286 :
//			move.setVolatileEffect(MoveSecondaryVolatileEffect.TELEKINESIS);
//			break;
//		case 288 :
//			move.setVolatileEffect(MoveSecondaryVolatileEffect.KNOCKED_DOWN);
//		}
//	}

}
