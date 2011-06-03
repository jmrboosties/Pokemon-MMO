package com.pokemon.mmo;

import java.sql.ResultSet;
import java.util.HashMap;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.Types;

public class PokemonSpeciesFactory {

	public static PokemonSpecies[] createSpeciesArray() {
		PokemonSpecies[] speciesArray = new PokemonSpecies[669];
		speciesArray[0] = new PokemonSpecies();
		DbAdapter adapter;
		ResultSet rs = null;

		try {
			adapter = new DbAdapter();
			for (int i = 1; i < speciesArray.length; i++) {
				PokemonSpecies species = new PokemonSpecies();

				species.setDexNumber(i);
				
				int[] stats = new int[6];
				int s = 0;
				rs = adapter
						.makeQuery("SELECT * FROM pokemon_stats WHERE pokemon_id = '"
								+ String.valueOf(i) + "'");
				while (rs.next() && s <= 5) {
					stats[s] = rs.getInt("base_stat");
					s++;
				}
				species.setStats(stats);

				rs = adapter
						.makeQuery("SELECT * FROM pokemon_names WHERE pokemon_id = '"
								+ String.valueOf(i)
								+ "' AND "
								+ "local_language_id = '9'");
				while (rs.next()) {
					species.setSpeciesName(rs.getString("name"));
					species.setSpeciesClass(rs.getString("species"));
				}

				double[] chars = new double[4];
				rs = adapter.makeQuery("SELECT * FROM pokemon WHERE id = '"
						+ String.valueOf(i) + "'");
				while (rs.next()) {
					chars[0] = (rs.getInt("height") / 10);
					chars[1] = (rs.getInt("weight") / 10);
					chars[2] = rs.getInt("color_id");
					chars[3] = rs.getInt("pokemon_shape_id");
					species.setGenderRatio(parseGenderRatioCode(rs.getInt("gender_rate")));
				}
				
				rs = adapter.makeQuery("SELECT * FROM pokemon_types WHERE pokemon_id = '" +
						String.valueOf(i) + "' AND slot = '1'");
				while (rs.next()) {
					species.setTypeSlot1(Types.getType(rs.getInt("type_id")));
				}
				rs = adapter.makeQuery("SELECT * FROM pokemon_types WHERE pokemon_id = '" +
						String.valueOf(i) + "' AND slot = '2'");
				while (rs.next()) {
					species.setTypeSlot2(Types.getType(rs.getInt("type_id")));
				}

				HashMap<Integer, Integer> levelUpMoves = new HashMap<Integer, Integer>();
				rs = adapter.makeQuery("SELECT * FROM pokemon_moves WHERE "
						+ "(pokemon_move_method_id = '1') AND "
						+ "(pokemon_id = '" + String.valueOf(i) + "')");
				while (rs.next()) {
					levelUpMoves.put(new Integer(rs.getInt("level")),
							new Integer(rs.getInt("move_id")));
				}
				species.setHashMap(0, levelUpMoves);

				HashMap<Integer, Boolean> eggMoves = new HashMap<Integer, Boolean>();
				rs = adapter.makeQuery("SELECT * FROM pokemon_moves WHERE "
						+ "(pokemon_move_method_id = '2') AND "
						+ "(pokemon_id = '" + String.valueOf(i) + "')");
				while (rs.next()) {
					eggMoves.put(new Integer(rs.getInt("move_id")),
							new Boolean(true));
				}
				species.setHashMap(1, eggMoves);

				HashMap<Integer, Boolean> tutorMoves = new HashMap<Integer, Boolean>();
				rs = adapter.makeQuery("SELECT * FROM pokemon_moves WHERE "
						+ "(pokemon_move_method_id = '3') AND "
						+ "(pokemon_id = '" + String.valueOf(i) + "')");
				while (rs.next()) {
					tutorMoves.put(new Integer(rs.getInt("move_id")),
							new Boolean(true));
				}
				species.setHashMap(2, tutorMoves);

				HashMap<Integer, Boolean> machineMoves = new HashMap<Integer, Boolean>();
				rs = adapter.makeQuery("SELECT * FROM pokemon_moves WHERE "
						+ "(pokemon_move_method_id = '4') AND "
						+ "(pokemon_id = '" + String.valueOf(i) + "')");
				while (rs.next()) {
					machineMoves.put(new Integer(rs.getInt("move_id")),
							new Boolean(true));
				}
				species.setHashMap(3, machineMoves);
				
				for (int j = 1; j < 4; j++) {
					rs = adapter.makeQuery("SELECT * FROM pokemon_abilities WHERE " +
							"pokemon_id = '" + String.valueOf(i) + "' AND slot = '" + String.valueOf(j) + "'");
					while (rs.next()) {
						if(rs.getInt("is_dream") == 1) {
							species.setDreamAbility(Ability.getAbility(rs.getInt("ability_id")));
						}
						else {
							species.setSingleAbility(Ability.getAbility(rs.getInt("ability_id")), j-1);
						}
					}
				}

				speciesArray[i] = species;
			}
			rs.close();
			adapter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return speciesArray;
	}
	
	private static double parseGenderRatioCode(int i) {
		switch(i) {
		case -1 :
			return -1;
		case 0 :
			return 100;
		case 1 :
			return 87.5;
		case 2 :
			return 75;
		case 4 :
			return 50;
		case 6 :
			return 25;
		case 8 :
			return 0;
		}
		throw new IllegalArgumentException("Invalid int code, you really shouldn't see this.");
	}

}
