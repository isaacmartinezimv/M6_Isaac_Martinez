package Bateria7_AccesoDOM;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class EX05_LeerXml {
	public static void main (String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File ("albums.xml"));
			System.out.printf ("Elemento raíz : %s %n", document.getDocumentElement().getNodeName());
			NodeList albums = document.getElementsByTagName("album");
			System.out.printf ("Nodos album a recorrer: %d %n", albums.getLength());
			System.out.println();
			
			for (int i = 0; i < albums.getLength(); i++) {
				Node emple = albums.item(i);
				
				if (emple.getNodeType() == Node.ELEMENT_NODE){
					Element elemento = (Element) emple;
					System.out.printf("AUTOR: %s %n", elemento.getElementsByTagName("autor").item(0).getTextContent());
					System.out.printf(" - TITULO: %s %n", 
							elemento.getElementsByTagName("titulo").item(0).getTextContent());
					System.out.printf(" - FORMATO: %s %n", 
							elemento.getElementsByTagName("formato").item(0).getTextContent());
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
}


