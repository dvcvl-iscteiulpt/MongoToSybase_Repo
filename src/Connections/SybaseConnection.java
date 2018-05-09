package Connections;

import java.sql.*;
import java.util.ArrayList;

import utils.HumidadeTemperatura;

public class SybaseConnection {
	
	private static Connection conn;
	private String  user;
	private String pass;
	private String db = "C:\\Users\\duart\\Desktop\\Work\\SID\\MonitorDeCulturas.db";
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
			conn.setAutoCommit(true);
			System.out.println("Connected to Sybase");
		} catch (SQLException e) {
			System.out.println("Server down. Unable to connect to database.");
		}
	}
	
	public void executeQuery(String query) {
		try {
			stat = conn.createStatement();
			result = stat.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Unable to execute Query successfully");
		}
	}

	public void setHumTempCollection(ArrayList<HumidadeTemperatura> array) {
		for(HumidadeTemperatura i : array) {
			executeQuery("Insert Into HumidadeTemperatura Values(('"+i.getDataMedicao()+"'), '"+
					i.getHoraMedicao()+"', "+i.getValorMedicaoHumidade()+", "+i.getValorMedicaoTemperatura()+", "+"null)");
		}
	}
}
