package Connections;

public class Main {

	public static void main(String[] args) {
		MongoConnection mongo = new MongoConnection();
		SybaseConnection sybase = new SybaseConnection();
		
		mongo.makeConnection();
		sybase.makeConnection("dba", "sql");
	}
}
