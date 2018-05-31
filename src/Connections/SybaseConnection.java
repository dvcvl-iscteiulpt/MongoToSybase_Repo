package Connections;

import java.sql.*;
import java.util.ArrayList;

import utils.HumidadeTemperatura;

public class SybaseConnection{
	
	private static Connection conn;
	private String  user;
	private String pass;
	private String db = "C:\\Users\\duart\\Desktop\\Work\\SID\\MonitorDeCulturas.db";
	private Statement stat;
	private ResultSet result;
	private boolean isImported;
	
	public SybaseConnection() {		
		isImported = true;
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
			e.printStackTrace();
		}
	}

	public void executeQuery(String query) {
		try {
			stat = conn.createStatement();
			result = stat.executeQuery(query);
		} catch (Exception e) {
			isImported = false;
		}
		isImported = true;
	}
	
	public void setHumTempCollection(ArrayList<HumidadeTemperatura> array) {
		ArrayList<HumidadeTemperatura> tmp = new ArrayList<HumidadeTemperatura>();
		for(HumidadeTemperatura i : array)
			tmp.add(i);
		for(HumidadeTemperatura i : array) {
				executeQuery("Insert Into dba.HumidadeTemperatura Values(('"+i.getDataMedicao()+"'), '"+
						i.getHoraMedicao()+"', "+i.getValorMedicaoHumidade()+", "+i.getValorMedicaoTemperatura()+", "+"null)");
				if (isImported) 
					tmp.remove(i);
		}
		for(int i=0; i<array.size(); i+=6) {
			executeQuery("Insert Into dba.Verifica Values(('"+array.get(i).getDataMedicao()+"'), '"+
					array.get(i).getHoraMedicao()+"', "+array.get(i).getValorMedicaoHumidade()+", "+array.get(i).getValorMedicaoTemperatura()+", "+"null)");
		}
		try {
			if(tmp.isEmpty()) {
				conn.commit();
				array.clear();
			}else
				conn.rollback();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
