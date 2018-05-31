package Connections;

import java.util.ArrayList;

import org.bson.BSON;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import utils.HumidadeTemperatura;

public class MongoConnection {

	private MongoClient client;
	private String user;
	private String pass;
	private MongoCollection<Document> migrados;
	private MongoCollection<Document> naoMigrados;
	private MongoDatabase database;
	public static String dataS = "date";
	public static String horaS = "time";
	public static String temperaturaS= "temperatura";
	public static String humidadeS = "humidade";
	public static String migrado = "Migrado";
	public static String naoMigrado ="naoMigrados";
	public MongoConnection() {
		
	}
	
	public void makeConnection() {
		client = new MongoClient();
		database = client.getDatabase("HumidadeTemperatura");
		migrados = database.getCollection(migrado);
		naoMigrados = database.getCollection(naoMigrado);
		for(String name : database.listCollectionNames())
			System.out.println("Collection:"+name);
		
		
	}
	public ArrayList<Document> getHumidadeTemperaturaCollection(){
		ArrayList<Document> array = new ArrayList<Document>();
		MongoCollection<Document> coll =database.getCollection(naoMigrado);
		MongoCursor<Document> cursor = coll.find().iterator();
		while (cursor.hasNext()) {
			Document a=cursor.next();
			array.add(a);
			BasicDBObject docToDelete = new BasicDBObject("_id", a.get("_id"));
			naoMigrados.deleteOne(docToDelete);
			migrados.insertOne(a);
		}
		return array;
	}
	
	public void closeConnecion() {
		client.close();
	}
	public String jsonString(String database, String collection, String data, String hora, String temp, String hum) {
		String json="{'database':'"+database+"','table':'"+collection+"','detail':{'"+dataS+"':'"+
				  data+"','"+horaS+"':'"+hora+"','"+temperaturaS+"':'"+temp+"','"+humidadeS+"':'"+hum+"'}}";
		
		return json;
	}
	public void insertMongo(String data, String hora, String temp, String hum) {
		migrados.insertOne(new Document().append(dataS, data).append(horaS, hora).append(temperaturaS, temp).append(humidadeS, hum));	
	}
	
}
