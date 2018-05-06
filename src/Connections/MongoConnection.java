package Connections;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

	private MongoClient client;
	private String user;
	private String pass;
	
	public MongoConnection() {
		
	}
	
	public void makeConnection() {
//		this.user = user;
//		this.pass = pass;
		client = new MongoClient();
		MongoDatabase database = client.getDatabase("user");
		for(String name : database.listCollectionNames())
			System.out.println(name);
	}
	
}
