package com.pokemon.mmo;

import java.sql.ResultSet;

public class PokemonSpeciesFactory {

	public static PokemonSpecies getPokemonSpecies(int dexNum) {
        if (dexNum < 0 || dexNum > 649){
            return new PokemonSpecies();
        }
        switch (dexNum){
            case 1:
                return new Bulbasaur();
            case 4:
                return new Charmander();
            case 7:
                return new Squirtle();
            default:
                break;
        }
        return new PokemonSpecies();
    }
	
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
				int j = 0;
				rs = adapter.makeQuery("SELECT * FROM pokemon_stats WHERE pokemon_id = '" + String.valueOf(i) + "'");
				
				while(rs.next() && j <= 5) {
					stats[j] = rs.getInt("base_stat");
					j++;
				}
				species.setStats(stats);
				speciesArray[i] = species;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return speciesArray;
	}
	
}
