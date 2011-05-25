package com.pokemon.mmo;

import java.sql.ResultSet;

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
				
				rs = adapter.makeQuery("SELECT * FROM move_names WHERE move_id = '" + String.valueOf(i) + "'");
				while(rs.next()) {
					move.setMoveName(rs.getString("name"));
				}
				
				rs = adapter.makeQuery("SELECT * FROM moves WHERE id = '" + String.valueOf(i) + "'");
				while(rs.next()) {
					move.setBasePower(rs.getInt("power"));
					move.setAccuracy(rs.getInt("accuracy"));
					move.setMoveId(rs.getInt("id"));
					move.setPP(rs.getInt("pp"));
					move.setPriority(rs.getInt("priority"));
					move.setKind(Enums.getMoveKindFromInt(rs.getInt("damage_class_id")));
					move.setType(Enums.getTypeFromInt(rs.getInt("type_id")));
				}
				
				moveArray[i] = move;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return moveArray;
	}

}
