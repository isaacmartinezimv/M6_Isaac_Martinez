package Bateria8_AccesoSAX;
/* EJERCICIO 3. Crea un programa que lea el archivo “discoteca.xml” (adjunto en el moodle) y determine el 
 * número de discos listados en el archivo. AYUDA: contabilizando el número de títulos. */

import java.io.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class EX03_ContadorDiscosSAX {
	public static void main (String [] args) throws FileNotFoundException, IOException, SAXException {
		XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
		NuevoGestor gestor = new NuevoGestor();
		procesadorXML.setContentHandler(gestor);
		InputSource fileXML = new InputSource ("discoteca.xml");
		procesadorXML.parse(fileXML);
	}
}

class NuevoGestor extends DefaultHandler {
	int recount = 0;
	
	public NuevoGestor(){
		super();
	}
	
	public void startDocument(){
		System.out.println("Comienzo del documento XML");
	}
	public void endDocument(){
		System.out.println("Final del documento XML");
		System.out.println("\nEl total de discos del XML es: " + recount);
	}
	public void startElement (String uri, String nombre, String nombreC, Attributes atts) {
		System.out.printf("\tPrincipio Elemento: %s %n", nombre);
		if (nombre == "TITLE")
			recount += 1;
	}
	public void endElement (String uri, String nombre, String nombreC){
		System.out.printf("\tFin Elemento: %s %n",nombre);
	}
	public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		String car = new String (ch, inicio, longitud);
		car = car.replaceAll("[\t\n]","");
		System.out.printf("\tCaracteres: %s %n", car);
	}
}
