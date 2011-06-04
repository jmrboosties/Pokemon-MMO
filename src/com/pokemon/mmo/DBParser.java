package com.pokemon.mmo;

import java.sql.ResultSet;

public class DBParser {
	
	public static void main(String[] args) {
		eggGroupEnumCreator();
	}
	
	public static void abilityEnumCreator() {
		String result = "NONE(0, '---'), ";
		DbAdapter adapter;
		ResultSet rs;
		
		try {
			adapter = new DbAdapter();
			rs = adapter.makeQuery("SELECT * FROM ability_names");
			while(rs.next()) {
				result += rs.getString("name").toUpperCase().replace(" ", "_") + "(" + rs.getInt("ability_id") + ", '" + rs.getString("name") + "'), ";
			}
			adapter.close();
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		result = result.replace((char)39, (char)34);
		
		System.out.println(result);
	}
	
	public static void eggGroupEnumCreator() {
		String result = "";
		DbAdapter adapter;
		ResultSet rs;
		
		try {
			adapter = new DbAdapter();
			rs = adapter.makeQuery("SELECT * FROM egg_group_prose");
			while(rs.next()) {
				result += rs.getString("name").toUpperCase().replace(" ", "_") + "(" +
					rs.getInt("egg_group_id") + ", '" + rs.getString("name") + "'), "; 
			}
			adapter.close();
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		result = result.replace((char)39, (char)34);
		
		System.out.println(result);
	}
	
}
