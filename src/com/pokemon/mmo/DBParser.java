package com.pokemon.mmo;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;

import com.pokemon.mmo.Enums.Stats;

public class DBParser {
		
	public static void main(String[] args) {
		Point pnt1 = new Point(0,0);
		Point pnt2 = new Point(0,0);
		System.out.println("X: " + pnt1.x + " Y: " +pnt1.y); 
		System.out.println("X: " + pnt2.x + " Y: " +pnt2.y);
		System.out.println(" ");
		DiddlePiddle widdle = new DiddlePiddle(pnt1, pnt2);
		System.out.println("X: " + pnt1.x + " Y:" + pnt1.y); 
		System.out.println("X: " + pnt2.x + " Y: " +pnt2.y);
	}
	
	public static void test() {
		DbAdapter adapter;
		ResultSet rs;
		String fill = "failure";
		boolean isNull = false;
		
		try {
			adapter = new DbAdapter();
			rs = adapter.makeQuery("SELECT * FROM pokemon WHERE id = '1'");
			fill = rs.getString("name");
			
			if(fill == null) {
				System.out.println("Hoo hoo!");
				isNull = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(!isNull) {
			System.out.println(fill);
		}
	}
	
	public static void newTest() {
		DbAdapter adapter;
		ResultSet rs;
		int i = 0;
		
		try {
			adapter = new DbAdapter();
			rs = adapter.makeQuery("SELECT * FROM moves WHERE meta_category_id = '13'");
			while(rs.next()) {
				i++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(i);
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
	
	public static void insertValsIntoSecondary() {
		int i = 1;
		
		System.out.println("New row. Insert values.");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
		String input = "";
		
		System.out.println("Enter row to start from");
		int rowId = Integer.valueOf(doIt(reader, input));
		rowId = rowId - 1;
		
		while(i == 1) {
			
			rowId = rowId + 1;
			
			String id;
			String nonVol;
			String vol;
			
			String userAtt;
			String userDef;
			String userspecAtt;
			String userspecDef;
			String userSpe;
			
			String targetAtt;
			String targetDef;
			String targetSpecAtt;
			String targetSpecDef;
			String targetSpe;			
			
			System.out.println("Enter id");
			id = doIt(reader, input);
			System.out.println("Enter nonVol");
			nonVol = doIt(reader, input);
			System.out.println("Enter vol");
			vol = doIt(reader, input);
			System.out.println("Enter userAtt");
			userAtt = doIt(reader, input);
			System.out.println("Enter userDef");
			userDef = doIt(reader, input);
			System.out.println("Enter userSpecAtt");
			userspecAtt = doIt(reader, input);
			System.out.println("Enter userSpecDef");
			userspecDef = doIt(reader, input);
			System.out.println("Enter userSpe");
			userSpe = doIt(reader, input);
			System.out.println("Enter targetAtt");
			targetAtt = doIt(reader, input);
			System.out.println("Enter targetDef");
			targetDef = doIt(reader, input);
			System.out.println("Enter targetSpecAtt");
			targetSpecAtt = doIt(reader, input);
			System.out.println("Enter targetSpecDef");
			targetSpecDef = doIt(reader, input);
			System.out.println("Enter targetSpe");
			targetSpe = doIt(reader, input);
			
			DbAdapter adapter;
			
			String userStatChange = "{'attack':'"+userAtt+"','defense':'"+userDef+"','special_attack':'"+
				userspecAtt+"','special_defense':'"+userspecDef+"','speed':'"+userSpe+"'}";
			
			userStatChange = userStatChange.replace((char)39, (char)34);
			
			String targetStatChange = "{'attack':'"+targetAtt+"','defense':'"+targetDef+"','special_attack':'"+
				targetSpecAtt+"','special_defense':'"+targetSpecDef+"','speed':'"+targetSpe+"'}";
			
			targetStatChange = targetStatChange.replace((char)39, (char)34);
			
			try {
				adapter = new DbAdapter();
				adapter.insertValues("INSERT INTO move_secondary_effects VALUES " +
						"('"+rowId+"','"+id+"','"+nonVol+"','"+vol+"','"+userStatChange+"','"+targetStatChange+"')");
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("Continue? (y/n)");
			String answer = doIt(reader, input);
			if(!answer.equals("y")) {
				i = 2;
			}
		}
		System.out.println("Done inserting.");
	}	
	
	private static String doIt(BufferedReader reader, String input) {
		try {
			input = reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return input;
	}
}
