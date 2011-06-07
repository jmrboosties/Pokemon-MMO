package com.pokemon.mmo;

import java.sql.ResultSet;
import java.util.List;

import com.pokemon.mmo.Enums.MoveEffectGroup;
import com.pokemon.mmo.Enums.MoveKinds;
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

}
