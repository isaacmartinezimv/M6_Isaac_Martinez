package bateria2_XQJ.II;

/* Ejercicio 2. A partir de los documentos productos.xml y zonas.xml, haz un 
 * programa que reciba un número de zona por parámetro y genere un documento 
 * con nombre zonaXX.xml donde XX es la zona solicitada. El documento debe 
 * contener los productos de esta zona y las siguientes etiquetas: <cod_prod>,
 * <denominación>, <precio>, <nombre_zona>, <director> y <stock>. Donde el stock
 * se calcula restando el stock actual y el stock mínimo.*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

public class EX02_Ejercicio {
	public static void main(String[] args) throws XQException, IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		try{
			XQDataSource server = new ExistXQDataSource();

			server.setProperty ("serverName", "192.168.56.102");
			server.setProperty ("port","8080");
			server.setProperty ("user","admin");
			server.setProperty ("password","austria");

			XQConnection conn = server.getConnection();
			XQPreparedExpression consulta;
			XQResultSequence resultado;

			System.out.println("Escoge el numero de la zona para la que deseas generar el documento :");
			String inputZona = reader.readLine();

			consulta = conn.prepareExpression(
					"let $pro := /productos/produc[cod_zona="+inputZona+"]"
							+ " let $numerozona := /zonas/zona[cod_zona="+inputZona+"]/nombre"
							+ " let $directorzona := /zonas/zona[cod_zona="+inputZona+"]/director"
							+ " return"
							
							+ " <productos>{"
							+ " for $producto in $producto"
							+ " let $stock:=$producto/(stock_actual - stock_minimo)"
							+ " return"
							
							+ " <producto>"
							+ " <cod_prod>{data($producto/cod_prod)}</cod_prod>"
							+ " <denominacion>{data($producto/denominacion)}</denominacion>"
							+ " <precio>{data($producto/precio)}</precio>"
							+ " <nombre_zona>{data($numerozona)}</nombre_zona>"
							+ " <director>{data($directorzona)}</director>"
							+ " <stock>{data($stock)}</stock>"
							+ " </producto>"
							
							+ " }"
							+ " </productos>");

			resultado = consulta.executeQuery();

			/*	Establecemos la ruta del fichero de salida en el directorio de 
			 * ejecucion del programa y con el nombre establecido */
			String fileName = "zona"+inputZona+".xml";
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

			writer.write("<?xml version='1.0' encoding='UTF-8'?>");
			writer.newLine();

			// Escribimos los resultados obtenidos
			while (resultado.next()) {
				String line = resultado.getItemAsString(null);
				writer.write(line);
				writer.newLine();
			}

			conn.close();
			writer.close();
			System.out.print("Se ha creado el fichero "+fileName+ " con éxito.");

		} catch (XQException ex) {
			System.out.println("Error al operar"+ex.getMessage());
		}
	}
}
