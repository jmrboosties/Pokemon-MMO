package com.pokemon.mmo;

import java.sql.ResultSet;

public class PokemonSpeciesFactory {

	public static PokemonSpecies[] createSpeciesArray() {
		PokemonSpecies[] speciesArray = new PokemonSpecies[668];
		PokemonSpecies base_poke = null;
		ResultSet info = null;
		ResultSet moves = null;
		DbAdapter adapter;

		try {
			System.out.print("Loading Pokedex.");
			adapter = new DbAdapter();
			for (int i = 1; i < 668; i++) {
				if (i%50 == 0) {
					System.out.print('.');
				}
				info = null;
				info = adapter.makeQuery("SELECT * FROM pokemon WHERE id = '" + i + "'");
				
				if(info.next()) {
					base_poke = new PokemonSpecies(info);
				}

				speciesArray[i] = base_poke;
			}
			info.close();
			
			for (int i = 1; i < 668; i++) {
				if (i%50 == 0) {
					System.out.print('.');
				}
				moves = null;
				moves = adapter.makeQuery("SELECT * FROM pokemon_moves WHERE pokemon_id = '" + i + "'");
				speciesArray[i].setMoves(moves);
			}
			moves.close();
			adapter.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return speciesArray;
	}
}
