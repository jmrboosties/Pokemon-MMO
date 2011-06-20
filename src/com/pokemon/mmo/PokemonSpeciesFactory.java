package com.pokemon.mmo;

import java.sql.ResultSet;
import java.util.HashMap;

import com.pokemon.mmo.Enums.Ability;
import com.pokemon.mmo.Enums.EggGroup;
import com.pokemon.mmo.Enums.Types;

public class PokemonSpeciesFactory {

	public static PokemonSpecies[] createSpeciesArray() {
		PokemonSpecies[] speciesArray = new PokemonSpecies[668];
		PokemonSpecies base_poke = null;
		ResultSet info = null;
		ResultSet moves = null;

		try {
			System.out.print("Loading Pokedex.");
			for (int i = 1; i < 668; i++) {
				if (i%50 == 0) {
					System.out.print('.');
				}
				info = (new DbAdapter()).makeQuery("SELECT * FROM pokemon WHERE id = '" + i + "'");
				moves = (new DbAdapter()).makeQuery("SELECT * FROM pokemon_moves WHERE pokemon_id = '" + i + "'");
				if(info.next()) {
					base_poke = new PokemonSpecies(info, moves);
				}

				speciesArray[i] = base_poke;
			}
			System.out.println("Done.");
			info.close();
			moves.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return speciesArray;
	}
}
