package com.pokemon.mmo;

import java.sql.ResultSet;

import com.pokemon.mmo.Enums.Stats;

public class DBParser {
	
	public static void main(String[] args) {
		natureEnumCreator();
	}
	
	public static void natureEnumCreator() {
		String result = "NONE(0, 'Natureless', Stats.HP, Stats.HP), ";
		DbAdapter adapter;
		ResultSet rs;
		
		try {
			adapter = new DbAdapter();
			rs = adapter.makeQuery("SELECT * FROM natures");
			while(rs.next()) {
				result += rs.getString("identifier").toUpperCase() + "(" + rs.getInt("id") + ", '" + 
					rs.getString("identifier") + "', " + "Stats." + String.valueOf(Stats.getStat(rs.getInt("decreased_stat_id") - 1)) +
					", " + "Stats." + String.valueOf(Stats.getStat(rs.getInt("increased_stat_id") - 1)) + "), ";			
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		result = result.replace((char)39, (char)34);
		
		System.out.println(result);
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
