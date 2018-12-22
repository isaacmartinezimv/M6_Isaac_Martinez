package Bateria8_AccesoSAX;
// EJERCICIO 2. Prueba a leer con el programa del ejercicio anterior el documento â€œpersonas.xmlâ€� (se trata del xml que generamos en la clase anterior)

import java.io.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class EX02_LecturaXMLAccesoSAX {
	public static void main (String [] args) throws FileNotFoundException, IOException, SAXException {
		/* A continuaciÃ³n se crea objeto procesador XML-XMLReader-. Durante la creaciÃ³n de este objeto se puede producir una excepciÃ³n SAXException. */
		XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
		/*A continuaciÃ³n, mediante setContentHandler establecemos que la clase que gestiona los eventos provocados por la 
		lectura del XML serÃ¡ GestionContenido */
		GestionContenido2 gestor = new GestionContenido2();
		procesadorXML.setContentHandler(gestor);
		/* Por Ãºltimo, se define el fichero que se va leer mediante InputSource
 		y se procesa el documento XML mediante el mÃ©todo parse() de XMLReader */
		InputSource fileXML = new InputSource ("Empleados.xml");
		procesadorXML.parse(fileXML);
	}
}

/* GestionContenido es la clase que implementa los mÃ©todos necesarios para crear nuestro parser de XML. Es decir, 
definimos los mÃ©todos que serÃ¡n llamados al provocarse los eventos comentados anteriormente: startDocument, 
startElement, characters, etc. Si quisieramos tratar mÃ¡s eventos definirÃ­amos el mÃ©todo asociado en esta clase. */

class GestionContenido2 extends DefaultHandler {
	public GestionContenido2(){
		super();
	}
	public void startDocument(){
		System.out.println("Comienzo del documento XML");
	}
	public void endDocument(){
		System.out.println("Final del documento XML");
	}
	public void startElement (String uri, String nombre, String nombreC, Attributes atts) {
		System.out.printf("\tPrincipio Elemento: %s %n", nombre);
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

