package Connections;

import softwareBrain.Brain;

public class Main {

	public static void main(String[] args) {
		Brain a = new Brain();
		a.startUp();
//		MongoConnection a = new MongoConnection();
//		System.out.println(a.jsonString("Monitor_De_Culturas", "HumidadeTemperatura", "12/05/2018", "18:34:20", "40.2", "15.1"));
//		SybaseConnection sybase = new SybaseConnection();
//		sybase.makeConnection("dba", "sql");
	}
}
