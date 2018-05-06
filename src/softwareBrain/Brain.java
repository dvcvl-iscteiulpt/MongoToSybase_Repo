package softwareBrain;

import java.util.ArrayList;

import org.bson.Document;

import Connections.MongoConnection;
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
		HumidadeTemperatura teste = convertToHumidadeTemperatura(docs.get(0));
		System.out.println(teste.getDataMedicao()+" "+ teste.getHoraMedicao()+" "+ teste.getValorMedicaoHumidade()+ " "+ teste.getValorMedicaoTemperatura());
		updateDocs();
	}
	
	// Recebe um documento e converte-o para uma instância da class humidadeTemperatura
	public HumidadeTemperatura convertToHumidadeTemperatura(Document docToConvert) {
		HumidadeTemperatura converted = new HumidadeTemperatura(docToConvert.get("DataMedicao").toString(), 
				docToConvert.get("HoraMedicao").toString(), 
				Double.parseDouble(docToConvert.get("ValorMedicaoTemperatura").toString()), 
				Double.parseDouble(docToConvert.get("ValorMedicaoHumidade").toString()));
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
}
