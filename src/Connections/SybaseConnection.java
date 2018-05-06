package Connections;

import java.sql.*;

public class SybaseConnection {
	
	private static Connection conn;
	private String  user;
	private String pass;
	private String db = "C:\\Users\\duart\\Desktop\\Work\\SID\\MonitorDeCulturasNADIR.db";
	private Statement stat;
	private ResultSet result;
	
	public SybaseConnection() {		
	}

	public void makeConnection(String user, String pass) {
		this.user = user;
		this.pass = pass;
		String dbUrl = "jdbc:sqlanywhere:Tds:localhost:2638?eng="+db;
		try {
			conn = DriverManager.getConnection(dbUrl, user, pass);
			conn.setAutoCommit(false);
			System.out.println("Connected to Sybase");
		} catch (SQLException e) {
			System.out.println("Server down. Unable to connect to database.");
		}
	}
	
	public void executeQuery(String query) {
		try {
			stat = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			result = stat.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
