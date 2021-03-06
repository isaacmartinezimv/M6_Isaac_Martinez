package bateria5_BDOO.II;

import bateria5_BDOO.I.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/* Ejercicio 1.  Realiza un programa que realice una búsqueda por nombre en la base de
 * datos “EQUIPOS.DB”. Pedirá un nombre al usuario y devolverá los datos del
 * jugador que responda a dicho nombre o un mensaje del estilo “no hay ningún
 * jugador que tenga ese nombre en la base de datos” */

public class EX01_Ejercicio01 {

	public static void main(String[] args) {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String nombreJugador = null;

		try {
			System.out.print("Inserte el nombre que quiera buscar: ");
			nombreJugador = reader.readLine();
		} catch ( IOException e ) {
			e.printStackTrace();
			System.exit(-1);
		}

		ICriterion filtro = Where.equal("nombre", nombreJugador);
		CriteriaQuery query = new CriteriaQuery (Jugadores.class, filtro);
		Objects<Jugadores> objects = odb.getObjects(query);

		if (objects.size() > 0) {
			while (objects.hasNext()) {
				Jugadores jugador = objects.next();
				System.out.println(
								"NOMBRE: " + jugador.getNombre() + "\n" +
								"DEPORTE: " + jugador.getDeporte() + "\n" +
								"CIUDAD: " + jugador.getCiudad() + "\n" + 
								"EDAD: " + jugador.getEdad() + "\n" +
								"PAIS: " + jugador.getPais() + "\n");
			}
		} else {
			System.out.println("No se han encontrado coincidencias");
		}
	}
}

