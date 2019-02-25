package proyecto_NeoDartis;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class ConstructorDataBase {
	public static void main(String[] args) {
		Depart dep1 = new Depart(10,"COCINA","Granada");
		Depart dep2 = new Depart(20,"ARTE","Barcelona");
		Depart dep3 = new Depart(30,"CONTABILIDAD","Madrid");
		Depart dep4 = new Depart(30,"DIRECCION","Santander");
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		
		Emple directorGeneral = new Emple(1, "Jimenez", "Director", null, date, (float)7356.23, dep4);
		directorGeneral = new Emple(1, "Jimenez", "Director", directorGeneral, date, (float)7356.23, dep4);
		Emple emple1 = new Emple(5, "Bardem", "Director Arte", directorGeneral, date, (float)5983.77, dep2);
		Emple emple2 = new Emple(2, "Coronado", "Actor", emple1, date, (float)3712.87, dep2);
		Emple emple3 = new Emple(3, "Chicote", "Chef", directorGeneral, date, (float)3298.04, dep1);
		Emple emple4 = new Emple(4, "Susi", "Cocinera", emple3, date, (float)1840.32, dep1);
		Emple emple5 = new Emple(6, "Rodriguez", "Contable", directorGeneral, date, (float)2167.73, dep3);
		Emple emple6 = new Emple(7, "Manzanares", "Secretario", emple5, date, (float)1724.12, dep3);
		
		ODB odb = ODBFactory.open("EMPRESA.DB"); // Abrir BD
		odb.store(directorGeneral);
		odb.store(emple1);
		odb.store(emple2);
		odb.store(emple3);
		odb.store(emple4);
		odb.store(emple5);
		odb.store(emple6);
		System.out.print("Data stored");
		odb.close(); // Cerrar BD
	}
}
