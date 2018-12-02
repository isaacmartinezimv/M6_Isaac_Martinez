package Bateria7_AccesoDOM;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;  
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class EX02_CrearXml {
	public static void main (String args[]) throws IOException {
		BufferedReader file = new BufferedReader(new FileReader("Empleados.txt"));
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null,"Empleados", null);
			document.setXmlVersion("1.0");

			String line = null;

			while ((line = file.readLine())!=null) {
				System.out.println(line);
				String[] data = line.split(":");
				Element raiz = document.createElement ("empleado");
				document.getDocumentElement().appendChild(raiz);
				newElement("id", data[0], raiz, document);
				newElement("apellido", data[1], raiz, document);
				newElement("dep", data[2], raiz, document);
				newElement("salario", data[3], raiz, document);	
			}

			Source source = new DOMSource(document);
			Result result = new StreamResult(new File ("Empleados.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		file.close();		
	}

	static void newElement(String datoEmpleado, String valor, Element raiz, Document document) {
		Element elem = document.createElement (datoEmpleado);
		Text text = document.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}
}
