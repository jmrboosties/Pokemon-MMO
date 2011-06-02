package com.pokemon.mmo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbAdapter {

	private Connection mConn;
	private Statement mStat;

	public DbAdapter() throws Exception {
		Class.forName("org.sqlite.JDBC").newInstance();
		mConn = DriverManager.getConnection("jdbc:sqlite:pokemon.db");
		mStat = mConn.createStatement();
	}

	public ResultSet makeQuery(String query) {
		ResultSet rs = null;

		try {
			rs = mStat.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problem in query");
		}
		return rs;
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
