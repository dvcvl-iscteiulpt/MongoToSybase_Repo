package softwareBrain;

import java.util.ArrayList;

import org.bson.Document;

import Connections.MongoConnection;
import Connections.SybaseConnection;
import utils.HumidadeTemperatura;
// Classe utilizada como motor do programa, tudo que é transformação de dados acontece aqui

public class Brain {
	
	private ArrayList<HumidadeTemperatura> arrayHT = new ArrayList<HumidadeTemperatura>();
	private ArrayList<Document> docs = new ArrayList<Document>();
	
	public ArrayList<HumidadeTemperatura> getArrayHT() {
		return arrayHT;
	}
	public void setArrayHT(ArrayList<HumidadeTemperatura> arrayHT) {
		this.arrayHT = arrayHT;
	}
	public ArrayList<Document> getDocs() {
		return docs;
	}
	public void setDocs(ArrayList<Document> docs) {
		this.docs = docs;
	}
	public void startUp() {
		MongoConnection mongo = new MongoConnection();
		mongo.makeConnection();
		docs = mongo.getHumidadeTemperaturaCollection();
		updateDocs();
		SybaseConnection sybase = new SybaseConnection();
		sybase.makeConnection("dba", "sql");
		sybase.setHumTempCollection(arrayHT);
	}
	
	public void changeDateFormat(String date) {
		String[] aux = date.split("/");
		String day = aux[0];
		String month = aux[1];
		String year = aux[2];
		System.out.println(day);
		System.out.println(month);
		System.out.println(year);
	}
	
	// Recebe um documento e converte-o para uma instância da class humidadeTemperatura
	public HumidadeTemperatura convertToHumidadeTemperatura(Document docToConvert) {
		HumidadeTemperatura converted = new HumidadeTemperatura(docToConvert.get("date").toString(), docToConvert.get("time").toString(), 
				Double.parseDouble(docToConvert.get("temperatura").toString()), 
				Double.parseDouble(docToConvert.get("humidade").toString()));
		return converted;
	}
	
	// esvazia a lista docs, metendo todos os registos na Lista de HumidadeTemperatura
	public void updateDocs() {
		ArrayList<Document> aux = new ArrayList<Document>();
		for(Document i : docs) {
			arrayHT.add(convertToHumidadeTemperatura(i));
			aux.add(i);
		}
		for( Document i: aux) {
			docs.remove(i);
		}
	}
	
	public void printHTList() {
		for(HumidadeTemperatura it : arrayHT) {
			System.out.println(it.toString());
		}
	}
}
