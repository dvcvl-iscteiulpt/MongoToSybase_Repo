package Connections;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

	private MongoClient client;
	private String user;
	private String pass;
	private DBCollection dbcollection;
	private MongoDatabase database;
	public MongoConnection() {
		
	}
	
	public void makeConnection() {
//		this.user = user;
//		this.pass = pass;
		client = new MongoClient();
		database = client.getDatabase("HumidadeTemperatura");
		for(String name : database.listCollectionNames())
			System.out.println(name);
		
		
	}
	public ArrayList<Document> getHumidadeTemperaturaCollection(){
		ArrayList<Document> array = new ArrayList<Document>();
		MongoCollection<Document> coll =database.getCollection("naoMigrados");
		MongoCursor<Document> cursor = coll.find().iterator();
		while (cursor.hasNext()) {
			Document a=cursor.next();
			array.add(a);
//			System.out.println(a);
		}
		return array;
	}
	
	public void closeConnecion() {
		client.close();
	}
	
}
