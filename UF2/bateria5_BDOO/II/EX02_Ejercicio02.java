package bateria5_BDOO.II;

import bateria5_BDOO.I.*;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/* Ejercicio 2.  Realiza un programa que devuelva los jugadores cuyas edades estén
comprendidas entre 14 y 20 años. Utiliza la interfaz ICriterion. */

public class EX02_Ejercicio02 {

	public static void main(String[] args) {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		String nombreJugador = null;

		ICriterion filtro = new And().add(Where.ge("edad", 14)).add(Where.le("edad", 20));
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
			System.out.println("No se encontró ningún jugador");
		}
	}
}