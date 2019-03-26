package proyecto_MongoDB;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

public class Proyecto_MongoDB {
	public static void main(String[] args) {

	MongoClient  client = new MongoClient("192.168.56.102", 13158);
	MongoDatabase dataBase = client.getDatabase ("mongoDB");
	System.out.print(dataBase.listCollectionNames());
	
	MongoCollection <Document> collection = dataBase.getCollection("amigos");
	List<Document> consulta = collection.find().into(new ArrayList<Document> ());
	for (int i =0; i < consulta.size(); i++) {
		System.out.println(" - " +  consulta.get(i).toString());
		}
	
	Document amigo = new Document();
	amigo.put("nombre", "Pepito");
	amigo.put("teléfono", 925677);
	amigo.put("curso","2DAM");
	amigo.put("fecha", new Date());
	collection.insertOne(amigo);
	
	MongoCursor<Document> cursor = collection.find().iterator();
	
	while (cursor.hasNext()) {
		Document doc = cursor.next();
		System.out.println (doc.toJson());
		}
	
	cursor.close();
	
	}
}
