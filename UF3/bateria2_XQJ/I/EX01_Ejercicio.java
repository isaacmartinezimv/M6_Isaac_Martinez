package bateria2_XQJ.I;

/* Ejercicio 1. Descarga la API y prueba el ejemplo anterior */

import javax.xml.xquery.*;
import net.xqj.exist.ExistXQDataSource;
public class EX01_Ejercicio {
	public static void main(String[] args){
		try{
			XQDataSource server = new ExistXQDataSource();

			server.setProperty ("serverName", "192.168.56.102");
			server.setProperty ("port","8080");
			server.setProperty ("user","admin");
			server.setProperty ("password","austria");

			XQConnection conn = server.getConnection();
			XQPreparedExpression consulta;
			XQResultSequence resultado;

			consulta = conn.prepareExpression ("for $pr in doc('nueva/productos.xml')/productos/produc return $pr");
			resultado = consulta.executeQuery();

			while (resultado.next()) {
				System.out.println(resultado.getItemAsString(null));
			}
			conn.close();

		} catch (XQException ex) {
			System.out.println("Error al operar"+ex.getMessage());
		}
	}
}