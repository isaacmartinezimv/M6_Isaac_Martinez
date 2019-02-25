package proyecto_NeoDartis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.IValuesQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

import bateria5_BDOO.I.Jugadores;

public class ConsultasDataBase {
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String user;
		int userOption;
		String file = "EMPRESA.DB";
		ODB odb = null;

		try {
			odb = ODBFactory.open(file);
		} catch(Exception e) {
			simplePrint("Error al abrir el fichero: "+file+" comprueba que no este siendo usado.");
			System.exit(-1);
		}

		Objects<Emple> empleList = odb.getObjects(Emple.class);
		Objects<Depart> departList =odb.getObjects(Depart.class);
		Objects<ObjectValues>  objects2 = null;

		simplePrint("Bienvenido a DataBase Manager");

		do {
			simplePrint("\n¿Que desea hacer? \n"
					+ "	1. Mostrar la Base de Datos al completo\n"
					+ "	2. Insertar elementos en la Base de Datos\n"
					+ "	3. Realizar consultas\n"
					+ "	4. Migrar Datos\n"
					+ "	5. Modificar datos\n"
					+ "	0. Salir\n");

			user = reader.readLine();
			userOption = Integer.parseInt(user);

			switch (userOption) {
			case 1: 
				showEmpleDB(empleList, departList, odb);
				showDepartDB(departList, empleList, odb);
				odb.close();
				break;
			case 2: 
				System.out.print("PENDIENTE DE DESARROLLO");
				//insertData();
				break;
			case 3: 
				simplePrint(" 1. Consultar el apellido de los empleados del departamento 10\n"
						+ " 2. Consultar el número de empleados del departamento de VENTAS\n"
						+ " 3. Consultar el apellido de los empleados cuyo director es FERNÁNDEZ\n"
						+ " 4. Consultar el número de empleados por cada departamento");
				user = reader.readLine();
				int userSubOption = Integer.parseInt(user);
				
				
				switch (userSubOption) {
				case 1:
					employeesInDepartTen(odb);
					break;
				case 2:
					employeesInSells(odb);
					break;
				case 3:
					showEmplesInVentas(odb);
					break;
				case 4: 
					employeesPerDepart(odb);
					break;
				}
			case 4:
				System.out.print("PENDIENTE DE DESARROLLO");
				break;
			case 5: 
				System.out.print("PENDIENTE DE DESARROLLO");
				break;

			default:
				System.exit(0);
			}
		} while (userOption != 0 && userOption > -1);

	}
	
	public static void employeesPerDepart(ODB odb) {
		Objects<ObjectValues>  objects2 = null;
		IQuery query = new ValuesCriteriaQuery(Emple.class).count("count").field("dept.deptNo").groupBy("dept.deptNo"); 
		objects2 = odb.getValues((IValuesQuery) query.orderByAsc("dept.deptNo"));
		
		if (objects2 != null) {
			while (objects2.hasNext()) {
				ObjectValues objectValues = (ObjectValues) objects2.next();
				System.out.println("El departamento num. " + objectValues.getByAlias("dept.deptNo") + 
								   " tiene un total de " + objectValues.getByAlias("count") + " empleados.");
			}
			odb.close();
			System.exit(0);
		}
	}
	
	public static void employeesInDepartTen(ODB odb) {
		Objects<Emple> empleList = null;
		IQuery query = new CriteriaQuery(Emple.class, Where.equal("dept.deptNo",10));
		empleList = odb.getObjects(query);

		
		if (empleList != null) {
			System.out.println("En el departamento 10 encontramos a: ");
			while (empleList.hasNext()) {
				Emple objectValues = empleList.next();
				System.out.println(" - " + objectValues.getApellido());
			}
			odb.close();
			System.exit(0);
		}
	}
	
	public static void employeesInSells(ODB odb) {
		Objects<Emple> empleList = null;
		IQuery query = new CriteriaQuery(Emple.class, Where.equal("dept.dnombre","VENTAS"));
		empleList = odb.getObjects(query);
		System.out.print("En el departamento de VENTAS encontramos a " + empleList.size() + " trabajadores.");
		odb.close();
		System.exit(0);
	}

	public static void simplePrint(String error){
		System.out.println(error);
	}

	public static void showEmpleDB(Objects<Emple> empleList, Objects<Depart> departList, ODB odb) {			
		if (empleList.size() > 0) {
			System.out.println("-------------------------------------------------");
			System.out.println("|                Tabla Empleados                |");
			System.out.println("-------------------------------------------------");
			while (empleList.hasNext()) {
				Emple empleado = empleList.next();

				System.out.print(
						"NUM. EMPLEADO:\t" + empleado.getEmpNo() + "\n" +
								"APELLIDO:\t" + empleado.getApellido() + "\n" +
								"OFICIO:\t\t" + empleado.getOficio() + "\n" +
								"DIRECTOR:\t" + empleado.getDir() + "\n" +
								"FECHA ALTA:\t" + empleado.getFechaAlt() + "\n" +
								"COMISION\t" + empleado.getComision() + "\n" +
								"DEPARTAMENTO:\t" + empleado.getDept() + "\n");
				System.out.println("-------------------------------------------------");
			}
		}
	}

	public static void showDepartDB(Objects<Depart> departList, Objects<Emple> empleList, ODB odb) {			
		if (departList.size() > 0) {
			System.out.println("-------------------------------------------------");
			System.out.println("|              Tabla Departamentos              |");
			System.out.println("-------------------------------------------------");
			while (departList.hasNext()) {
				Depart departamento = departList.next();
				System.out.print(
						"NUM. DEPARTAMENTO:\t" + departamento.getDeptNo() + "\n" +
								"NOMBRE DEPARTAMENTO:\t" + departamento.getDnombre() + "\n" +
								"LOCALIZACIÓN:\t\t" + departamento.getLoc() + "\n");
				System.out.println("-------------------------------------------------");
			}
		}
	}

	public static void insertData() {


	}

	public static void showEmplesInVentas(ODB odb) {

		ICriterion criterion = Where.like("depart", "VENTAS");
		IQuery query = new CriteriaQuery(Jugadores.class, criterion);

		Objects<Emple> empleList = odb.getObjects(query);

        
        while (empleList.hasNext()) {
        	Emple empleado = empleList.next();
			System.out.print(
					"NUM. EMPLEADO:\t" + empleado.getEmpNo() + "\n" +
							"APELLIDO:\t" + empleado.getApellido() + "\n" +
							"OFICIO:\t\t" + empleado.getOficio() + "\n" +
							"DIRECTOR:\t" + empleado.getDir() + "\n" +
							"FECHA ALTA:\t" + empleado.getFechaAlt() + "\n" +
							"COMISION\t" + empleado.getComision() + "\n" +
							"DEPARTAMENTO:\t" + empleado.getDept() + "\n");
			System.out.println("-------------------------------------------------");
			}
        odb.close();
	}
}
