package com.pokemon.mmo;

import java.sql.ResultSet;

public class Ability {
	
	public static void main(String[] args) {
		String result = "NONE(0, '---'), ";
		DbAdapter adapter;
		ResultSet rs;
		
		try {
			adapter = new DbAdapter();
			rs = adapter.makeQuery("SELECT * FROM ability_names");
			while(rs.next()) {
				result += rs.getString("name").toUpperCase().replace(" ", "_") + "(" + rs.getInt("ability_id") + ", '" + rs.getString("name") + "'), ";
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		result = result.replace((char)39, (char)34);
		
		System.out.println(result);
	}
	
}
