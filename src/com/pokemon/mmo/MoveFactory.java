package com.pokemon.mmo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.pokemon.mmo.Enums.ModdableBattleStats;
import com.pokemon.mmo.Enums.MoveEffectGroup;
import com.pokemon.mmo.Enums.MoveKinds;
import com.pokemon.mmo.Enums.MoveSecondaryNonVolatileEffect;
import com.pokemon.mmo.Enums.MoveSecondaryVolatileEffect;
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

				rs = adapter
						.makeQuery("SELECT * FROM move_names WHERE move_id = '"
								+ String.valueOf(i) + "'");
				while (rs.next()) {
					move.setMoveName(rs.getString("name"));
				}

				rs = adapter.makeQuery("SELECT * FROM moves WHERE id = '"
						+ String.valueOf(i) + "'");
				while (rs.next()) {
					move.setBasePower(rs.getInt("power"));
					move.setAccuracy(rs.getInt("accuracy"));
					move.setMoveId(rs.getInt("id"));
					move.setPP(rs.getInt("pp"));
					move.setPriority(rs.getInt("priority"));
					move.setKind(MoveKinds.getMoveKind(rs.getInt("damage_class_id")));
					move.setType(Types.getType(rs.getInt("type_id")));
				}

				moveArray[i] = move;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return moveArray;
	}
	
	private static List<Integer> getMoveEffectIdList(MoveEffectGroup group) {
		
	}
	
	public static MoveEffectGroup getMoveEffectGroup(Move move) {
		int effectId = move.getMoveCode();
		switch(effectId) {
		case 1 : 
			
			return MoveEffectGroup.REGULAR;
		case 2 :
			
		}
	}
	
	private static void setMoveNonVolatileEffect(ResultSet rs, Move move) {
		int effectId = 0;
		try {
			effectId = rs.getInt("effect_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		switch (effectId) {
		
		/**Non-Volatile Status Effects*/
		case 2 :
			move.setNonVolatileEffect(MoveSecondaryNonVolatileEffect.SLEEPS);
			break;
		case 3 :
		case 67 :
		case 78 :
		case 210 :
			move.setNonVolatileEffect(MoveSecondaryNonVolatileEffect.POISONS);
			break;
		case 34 :
		case 203 :
			move.setNonVolatileEffect(MoveSecondaryNonVolatileEffect.BADLY_POISONS);
			break;
		case 5 :
		case 126 :
		case 168 :
		case 201 :
			move.setNonVolatileEffect(MoveSecondaryNonVolatileEffect.BURNS);
			break;
		case 6 :
			move.setNonVolatileEffect(MoveSecondaryNonVolatileEffect.FREEZES);
			break;
		case 7 :
		case 68 :
		case 153 :
			move.setNonVolatileEffect(MoveSecondaryNonVolatileEffect.PARALYZES);
			break;
		case 37 :
			move.setNonVolatileEffect(MoveSecondaryNonVolatileEffect.TRI_ATTACK);
			break;
			
		/**Positive Stat Changes*/
		case 11 :
		case 140 :
			move.setMoveStatChanges(ModdableBattleStats.ATTACK, 1);
			break;
		case 12 :
		case 139 :
		case 146 :
		case 157 :
			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, 1);
			break;
		case 14 :
		case 167 :
			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_ATTACK, 1);
			break;
		case 17 :
			move.setMoveStatChanges(ModdableBattleStats.EVASION, 1);
			break;
		case 51 :
		case 119 :
			move.setMoveStatChanges(ModdableBattleStats.ATTACK, 2);
			break;
		case 52 :
			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, 2);
			break;
		case 53 :
			move.setMoveStatChanges(ModdableBattleStats.SPEED, 2);
			break;
		case 54 :
			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_ATTACK, 2);
			break;
		case 55 :
		case 175 :
			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_DEFENSE, 2);
			break;
		case 109 :
			move.setMoveStatChanges(ModdableBattleStats.EVASION, 2);
			break;
		case 141 :
			int[] ancientPower = {1,1,1,1,1,0,0};
			move.setMoveStatChangesArray(ancientPower);
			break;
		case 143 :
			move.setMoveStatChanges(ModdableBattleStats.ATTACK, 6);
			break;
		case 207 :
			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, 1);
			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_DEFENSE, 1);
			break;
		case 209 :
			move.setMoveStatChanges(ModdableBattleStats.ATTACK, 1);
			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, 1);
			break;
		case 212 :
			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_ATTACK, 1);
			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_DEFENSE, 1);
			break;
		case 213 :
			move.setMoveStatChanges(ModdableBattleStats.ATTACK, 1);
			move.setMoveStatChanges(ModdableBattleStats.SPEED, 1);
			break;
			
		/**Negative Stat Changes*/
		case 19 : 
		case 69 :
			move.setMoveStatChanges(ModdableBattleStats.ATTACK, -1);
			break;
		case 20 :
		case 70 :
			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, -1);
			break;
		case 72 :
			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_ATTACK, -1);
			break;
		case 73 :
			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_DEFENSE, -1);
			break;
		case 21 :
		case 71 :
			move.setMoveStatChanges(ModdableBattleStats.SPEED, -1);
			break;
		case 24 :
		case 74 :
			move.setMoveStatChanges(ModdableBattleStats.ACCURACY, -1);
			break;
		case 25 :
			move.setMoveStatChanges(ModdableBattleStats.EVASION, -1);
			break;
		case 59 :
			move.setMoveStatChanges(ModdableBattleStats.ATTACK, -2);
			break;
		case 60 :
			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, -2);
			break;
		case 61 :
			move.setMoveStatChanges(ModdableBattleStats.SPEED, -2);
			break;
		case 63 :
			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_DEFENSE, -2);
			break;
		case 169 :
			move.setMoveStatChanges(ModdableBattleStats.ATTACK, -2);
			move.setMoveStatChanges(ModdableBattleStats.SPECIAL_ATTACK, -2);
			break;
		case 206 :
			move.setMoveStatChanges(ModdableBattleStats.ATTACK, -1);
			move.setMoveStatChanges(ModdableBattleStats.DEFENSE, -1);
			break;
		
		/**Volatile Status Effects*/
		case 32 :
		case 76 :
		case 93 :
		case 147 :
		case 151 :
		case 159 :
			move.setVolatileEffect(MoveSecondaryVolatileEffect.FLINCHES);
			break;
		case 50 :
		case 77 :
		case 119 :
		case 200 :
			move.setVolatileEffect(MoveSecondaryVolatileEffect.CONFUSES);
			break;
		case 85 :
			move.setVolatileEffect(MoveSecondaryVolatileEffect.LEECH_SEEDS);
		case 108 :
			move.setVolatileEffect(MoveSecondaryVolatileEffect.NIGHTMARES);
			break;
		case 110 :
			move.setVolatileEffect(MoveSecondaryVolatileEffect.CURSES);
			break;
		case 114 :
			move.setVolatileEffect(MoveSecondaryVolatileEffect.IDENTIFIES);
			break;
		case 115 :
			move.setVolatileEffect(MoveSecondaryVolatileEffect.PERISH_SONG);
			break;
		case 121 :
			move.setVolatileEffect(MoveSecondaryVolatileEffect.ATTRACTS);
			break;
		case 176 :
			move.setVolatileEffect(MoveSecondaryVolatileEffect.TAUNTS);
			break;
		case 188 :
			move.setVolatileEffect(MoveSecondaryVolatileEffect.YAWNS);
		}
	}

}
