package proyecto_MongoDB;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Proyecto_MongoDB {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		boolean statusOn = true;
		MongoClient  client = new MongoClient(); 
		MongoDatabase dataBase = client.getDatabase ("piezas"); 
		MongoCollection<org.bson.Document> collection = dataBase.getCollection("todohardware");
		
		do {
			System.out.println("--------------------------------------------");
			System.out.println("|         Bienvenido a TodoHardware        |");
			System.out.println("--------------------------------------------");
			System.out.println("              ¿Que desea hacer?             ");
			System.out.println("");
			System.out.println("   1. Insertar una pieza nueva en la Base de Datos: ");
			System.out.println("   2. Visualizar todas las piezas de la Base de Datos: ");
			System.out.println("   3. Visualizar las piezas de un tipo: ");
			System.out.println("   4. Modificar pieza: ");
			System.out.println("   5. Eliminar pieza: ");
			System.out.println("   6. Exportar los datos de un tipo de piezas en fichero de texto: ");
			System.out.println("   7. Salir: ");
			System.out.println("");
			
			String input = reader.readLine();
			int userOption = Integer.parseInt(input);
			
			switch(userOption) {
			case 1:
				insertProduct(collection);
				break;
			case 2:
				showFullDataBase(collection);
				break;
			case 3:
				showProductsByType(collection);
				break;
			case 4:
				renameProduct(collection);
				break;
			case 5:
				deleteProduct(collection);
				break;
			case 6:
				fromDatabaseToTextfile(collection);
				break;
			case 7:
				System.out.println("¡Adios!");
				statusOn = false;
				break;
			default:
				System.out.print("Esta opción no es válida. Escoga una de las opciones disponibles.");
				break;
			}
		} while (statusOn == true);
	}


	public static void insertProduct(MongoCollection<org.bson.Document> collection) throws IOException {
		Document product = new Document();
		
		// Registramos el nombre de la pieza (no tiene filtro porque puede contener numeros, letras y caracteres especiales
		System.out.print("Nombre de la pieza: ");
		String name = reader.readLine();
		product.put("nombre",name); 
		
		// Pedimos el nombre de la marca
		System.out.print("Marca de la pieza: ");
		
		// Validamos que el nombre no contiene números
		String brand = reader.readLine();
		boolean allLetters = brand.chars().allMatch(Character::isLetter);
		
		while (allLetters == false) {
			System.out.println("El nombre de la marca no puede contener números. Introduce un nombre válido: ");
			brand = reader.readLine();
			allLetters = brand.chars().allMatch(Character::isLetter);
		}
		
		//Una vez el dato es validado, lo registramos
		product.put("marca", brand); 
		
	
		// Pedimos el tipo de pieza
		System.out.print("Que tipo de pieza es: ");
		String[] protucTypes = {"Placa base", "Procesador", "Disco duro", "Tarjeta grafica", "Memoria RAM", "Torre", "Fuente de alimentacion", "Refrigeracion"};
		String type = reader.readLine();
		
		// Comprobamos que el tipo de pieza esta permitido en nuestra base de datos
		boolean correctType = false;

		while (correctType == false ) {
			for (int i=0; i < protucTypes.length; i++) {
				if (type.equals(protucTypes[i])) {
					correctType = true;
				}
			}

			if (correctType) {
				product.put("tipo", type);
			} else {
				System.out.println("El tipo de producto no es correcto. Los tipos permitidos son "+ protucTypes + ". Vuelve a introducir el tipo: ");
				type = reader.readLine();
			}
		}
				
		// Pedimos el modelo de la pieza (numero de serie)
		System.out.print("Número de serie de la pieza: ");
		String serialNumber = reader.readLine();
		
		// Comprobamos que el numero de serie contenga 8 caracteres (puede ser alfanumerico)
		while (serialNumber.length() != 8) {
			System.out.println("Vuelve a introducir el número de serie. Este número debe tener 8 caracteres: ");
			serialNumber = reader.readLine();
		}
		
		// Una vez validado registramos el numero de serie
		product.put("numero", serialNumber);
		
		
		// Pedimos el stock actual de la pieza
		System.out.print("Cuantas piezas hay en stock: ");
		String stock = reader.readLine();
		boolean allNumbers = brand.chars().allMatch(Character::isDigit);
		
		// Validamos que sea un numero
		while (allNumbers == false) {
			System.out.println("La cantidad de producto debe ser un numero entero. Vuelve a introducir la cantidad: ");
			stock = reader.readLine();
			allNumbers = stock.chars().allMatch(Character::isLetter);
		}
		
		//Una vez el dato es validado, lo escribimos en el documento
		int finalStock = Integer.parseInt(stock);
		product.put("stock", finalStock); 
		collection.insertOne(product);
	}


	public static void showFullDataBase(MongoCollection<org.bson.Document> collection) {
		List<Document> query = collection.find().into(new ArrayList<Document> ()); 
		
		for (int i =0; i < query.size(); i++) { 
			System.out.println(i+". " +  query.get(i).toString()); 
		}
	}


	public static void showProductsByType(MongoCollection<org.bson.Document> collection) throws IOException {
		MongoCursor<Document> cursor = collection.find().iterator(); 
		System.out.println("Que tipo de piezas deseas visualizar: ");
		String type = reader.readLine();
		
		while (cursor.hasNext()) {
			Document document = cursor.next(); 
			if(document.getString("tipo").equals(type)) {
				System.out.println (document.toJson().toString()); 
			}
		}
		
		cursor.close();
	}


	public static void renameProduct(MongoCollection<org.bson.Document> collection) throws IOException {
		System.out.println("Escribe el nombre del producto que deseas modificar: ");
		String oldName = reader.readLine();	
		Document oldData = new Document().append("nombre", oldName);
		
		Document newData = new Document();
		System.out.println("Escribe el nuevo nuevo: ");
		String newName = reader.readLine();
		newData.append("$set", new Document().append("nombre", newName));

		collection.updateOne(oldData, newData);
	}


	public static void deleteProduct(MongoCollection<org.bson.Document> coleccion) throws IOException {
		System.out.println("Escribe el numero de serie del producto que deseas eliminar: ");
		String serialNumber = reader.readLine();
		
		coleccion.deleteOne(new Document("numero", serialNumber));
	}


	public static void fromDatabaseToTextfile(MongoCollection<org.bson.Document> collection) {
		MongoCursor<Document> cursor = collection.find().iterator(); 
		FileWriter file = null;
		PrintWriter writer = null;
		
		try{
			file = new FileWriter("C:\\Users\\Isaac\\Desktop\\ProyectoMongo\\mongoDatabase.txt");
			writer = new PrintWriter(file);

			System.out.println("Escribe el tipo de piezas que deseas exportar: ");
			String type = reader.readLine();
			while (cursor.hasNext()) {
				Document document = cursor.next(); 
				if(document.getString("tipo").equals(type)) {
					writer.print(document.toJson()); 
				}
			} 
			cursor.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (null != file)
					file.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}

