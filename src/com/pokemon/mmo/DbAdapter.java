package com.pokemon.mmo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAdapter {

	private Connection mConn = null;
	private Statement mStat = null;

	public DbAdapter() throws Exception {
		Class.forName("org.sqlite.JDBC");
		mConn = DriverManager.getConnection("jdbc:sqlite:assets/pokemon.db");
		mStat = mConn.createStatement();
	}

	public ResultSet makeQuery(String query) {
		ResultSet rs = null;

		try {
			rs = mStat.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problem in query:  " + query);
		}
		return rs;
	}
	
	public void insertValues(String query) {
		try {
			mStat.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			mStat.close();
			mConn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
