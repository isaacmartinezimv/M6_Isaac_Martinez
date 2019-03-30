package bateria2_XQJ.I;

/* Ejercicio 3. Realiza un programa que devuelva el número de productos con 
 * precio mayor a 50. */

import javax.xml.xquery.*;
import net.xqj.exist.ExistXQDataSource;

public class EX03_Ejercicio {
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

			consulta = conn.prepareExpression ("for $producto in /productos\n"
					+ "let $numeroProductos := /$producto/produc[precio > 50]\n"
					+ "return <num_productos>{count($numeroProductos)}</num_productos>");
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