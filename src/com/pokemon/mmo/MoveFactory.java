package com.pokemon.mmo;

import java.sql.ResultSet;

import com.pokemon.mmo.Enums.MetaStatusAilment;
import com.pokemon.mmo.Enums.MoveFlag;
import com.pokemon.mmo.Enums.MoveKinds;
import com.pokemon.mmo.Enums.MoveMetaCategory;
import com.pokemon.mmo.Enums.MoveTargetId;
import com.pokemon.mmo.Enums.Room;
import com.pokemon.mmo.Enums.Sport;
import com.pokemon.mmo.Enums.Types;
import com.pokemon.mmo.Enums.Weather;

public class MoveFactory {

	public static Move[] createMoveArray() {
		Move[] moveArray = new Move[563];
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
					if(rs.getInt("accuracy") == 0) {
						move.setAccuracy(-1);
					}
					else {
						move.setAccuracy(rs.getInt("accuracy"));
					}
					move.setMoveId(rs.getInt("id"));
					move.setMoveEffectId(rs.getInt("effect_id"));
					move.setPP(rs.getInt("pp"));
					move.setPriority(rs.getInt("priority"));
					move.setKind(MoveKinds.getMoveKind(rs.getInt("damage_class_id")));
					move.setType(Types.getType(rs.getInt("type_id")));
					move.setMoveMetaCategory(MoveMetaCategory.getCategory(rs.getInt("meta_category_id")));
					move.setStatusAilment(MetaStatusAilment.getAilment(rs.getInt("meta_ailment_id")));
					
					if(rs.getInt("ailment_chance") == 0) {
						move.setSecondaryAilmentChance(100);
					}
					else {
						move.setSecondaryAilmentChance(rs.getInt("ailment_chance"));
					}
					if(rs.getInt("stat_chance") == 0) { 
						move.setSecondaryStatChangeChance(100);
					}
					else {
						move.setSecondaryStatChangeChance(rs.getInt("stat_chance"));
					}
					move.setMoveEffect(rs.getInt("effect_id"));
					move.setMoveTarget(MoveTargetId.getTarget(rs.getInt("target_id")));
					
					move.setMaxHits(rs.getInt("max_hits"));
					move.setMinHits(rs.getInt("min_hits"));
					
					move.setMaxTurns(rs.getInt("max_turns"));
					move.setMinTurns(rs.getInt("min_turns"));
					
					move.setRecoilPercentage(rs.getInt("recoil"));
					
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
				
				rs = adapter.makeQuery("SELECT * FROM move_flag_map WHERE move_id = '" + 
						String.valueOf(i) + "'");
				while(rs.next()) {
					MoveFlag flag = MoveFlag.getFlag(rs.getInt("move_flag_id"));
					move.setMoveFlag(flag);					
				}
								
				switch(move.getMoveEffect()) {
				case 30 :
				case 45 :
				case 78 :
				case 105 :
					move.setMoveMetaCategory(MoveMetaCategory.MULTI_HIT);
					break;
				case 314 :
					move.setMoveMetaCategory(MoveMetaCategory.DAMAGE_AND_FORCE_SWITCH);
					break;
				}
				
				//TODO outrage and stuff
				
				switch(move.getMoveId()) { //TODO code each case for unique effects including these
				case 251 :
				case 91 :
				case 19 :
				case 291 :
				case 507 :
				case 275 :
				case 50 :
					move.setMoveMetaCategory(MoveMetaCategory.UNIQUE_EFFECT);
					break;
//				case 34 :
//					move.setStatusAilment(MetaStatusAilment.TOXIC);
//					//TODO i think more can toxic
//					break;
				case 286 :
					move.setStatusAilment(MetaStatusAilment.RISE);
					break;
				case 114 :
					move.setMoveMetaCategory(MoveMetaCategory.UNIQUE_EFFECT);
					break;
				case 201 :
					move.setConjuredWeather(Weather.SANDSTORM);
					break;
				case 240 :
					move.setConjuredWeather(Weather.RAIN_DANCE);
					break;
				case 241 :
					move.setConjuredWeather(Weather.SUNNY_DAY);
					break;
				case 258 :
					move.setConjuredWeather(Weather.HAIL);
					break;
				case 300 :
					move.setMoveSport(Sport.MUD_SPORT);
					break;
				case 346 :
					move.setMoveSport(Sport.WATER_SPORT);
					break;
				case 433 :
					move.setRoom(Room.TRICK_ROOM);
					break;
				case 472 :
					move.setRoom(Room.WONDER_ROOM);
					break;
				case 478 :
					move.setRoom(Room.MAGIC_ROOM);
					break;
				}
				
				moveArray[i] = move;
			}
			adapter.close();
			
			Move confusion = new Move();
			confusion.setBasePower(40);
			confusion.setMoveTarget(MoveTargetId.USER);
			confusion.setMoveMetaCategory(MoveMetaCategory.CONFUSED);
			moveArray[560] = confusion;
			
			Move notAttacking = new Move();
			notAttacking.setMoveMetaCategory(MoveMetaCategory.NOT_ATTACKING);
			moveArray[561] = notAttacking;
			
			Move switched = new Move();
			switched.setMoveMetaCategory(MoveMetaCategory.SWITCHED);
			switched.setPriority(7);
			moveArray[562] = switched;

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done populating moves.");
		return moveArray;
	}

}
