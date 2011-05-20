package com.pokemon.mmo;

import java.sql.ResultSet;

public class PokemonSpeciesFactory {
	
	public static PokemonSpecies[] createSpeciesArray() {
		PokemonSpecies[] speciesArray = new PokemonSpecies[669];
		speciesArray[0] = new PokemonSpecies();
		DbAdapter adapter;
		ResultSet rs;
		
		try {
			adapter = new DbAdapter();
			System.out.println(speciesArray.length);
			for (int i = 1; i < speciesArray.length; i++) {
				PokemonSpecies species = new PokemonSpecies();
				
				int[] stats = new int[6];
				int s = 0;
				rs = adapter.makeQuery("SELECT * FROM pokemon_stats WHERE pokemon_id = '" + String.valueOf(i) + "'");
				while(rs.next() && s <= 5) {
					stats[s] = rs.getInt("base_stat");
					s++;
				}
				species.setStats(stats);
				
				rs = adapter.makeQuery("SELECT * FROM pokemon_names WHERE pokemon_id = '" + String.valueOf(i) + "' AND " +
						"local_language_id = '9'");
				while(rs.next()) {
					species.setSpeciesName(rs.getString("name"));
					species.setSpeciesClass(rs.getString("species"));
				}
				
				double[] chars = new double[4];
				rs = adapter.makeQuery("SELECT * FROM pokemon WHERE pokemon_id = '" + String.valueOf(i) + "'");
				while(rs.next()) {
					chars[0] = (rs.getInt("height") / 10);
					chars[1] = (rs.getInt("weight") / 10);
					chars[2] = rs.getInt("color_id");
					chars[3] = rs.getInt("pokemon_shape_id");
				}
				
				speciesArray[i] = species;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return speciesArray;
	}
	
}
