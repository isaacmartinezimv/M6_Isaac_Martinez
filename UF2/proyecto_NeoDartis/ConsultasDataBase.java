package proyecto_NeoDartis;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class ConsultasDataBase {
	public static void main(String[] args) throws IOException {
		int sub3partSelected=0;
		int sub2partSelected=0;
		int sub5partSelected=0;
		int sub4partSelected=0;
		int insertSelected=0; 
		String file = "EMPRESA.DB";
		ODB odb=null;

		HashMap<Integer, Emple> listaEmple = new HashMap<>();
		HashMap<Integer, Depart> listaDeparts = new HashMap<>();
		ArrayList<String> listaPersonas = new ArrayList<>();
		ArrayList<Integer> listaNoPersonas = new ArrayList<>();
		ArrayList<Integer> listaNoDepart = new ArrayList<>();
		ArrayList<String> listaDepart = new ArrayList<>();

		int partSelected = preguntaType(listaPreguntas("Mostrar toda la Base de Datos [1]","Segunda Parte [2]","Tercera Parte [3]","Cuarta Parte [4]","Quinta Parte [5]", "Salir [0]"), "Introduce un entero disponible","Integer",1,listaRespuestas(1,2,3,4,5),null,null,null);
		if (partSelected==2) {
			sub2partSelected = preguntaType(listaPreguntas("Insertar [1]","Mostrar info [2]"),"Introduce un entero disponible","Integer",1,listaRespuestas(1,2),null,null,null);
			if (sub2partSelected==1) {
				insertSelected = preguntaType(listaPreguntas("Insertar Departamento [1]","Insertar Empleado [2]"),"Introduce un entero disponible","Integer",1,listaRespuestas(1,2),null,null,null);
			}
			sub3partSelected = 21;
		}

		try {
			odb = ODBFactory.open(file);
		} catch(Exception e) {
			text("Error al abrir el fichero: "+file+" comprueba que no este siendo usado.");
			System.exit(-1);
		}
		
		Objects<Depart> objectsDepart =odb.getObjects(Depart.class);
		Objects<Emple> objects =odb.getObjects(Emple.class);
		Objects<ObjectValues>  objects2 = null;

		if (partSelected==3) {
			sub3partSelected = preguntaType(listaPreguntas("Apellidos de los empleados del departamento 10 [1]","Numero de empleados del departamento de VENTAS [2]","Apellido de los empleados cuyo director es FERNANDEZ [3]", "Por cada departamento el numero de empleados [4]"),"Introduce un entero disponible","Integer",1,listaRespuestas(1,2,3,4),null,null,null);
			if(sub3partSelected==4) {
				objects2 = odb.getValues(new ValuesCriteriaQuery(Emple.class)
						.count("count").field("dept.deptNo")
						.groupBy("dept.deptNo"));
				if(objects2!=null) {
					while(objects2.hasNext()) {
						ObjectValues objectValues= (ObjectValues) objects2.next();
						System.out.println("Depart:"+objectValues.getByAlias("dept.deptNo")+" Empleados: "+objectValues.getByAlias("count"));
					}
					odb.close();
					System.exit(0);
				}
			}
			IQuery query = iQueryConstructor(sub3partSelected);

			try{
				objects = odb.getObjects(query);
			}catch(Exception e ) {
				switch(sub3partSelected) {
				case 1:
					text("No existe el departamento 10");

					break;
				case 2:
					text("No existe el departamento de VENTAS");
					break;
				case 3:
					text("No existe el empleado FERNANDEZ");
					break;
				}
				odb.close();
				System.exit(0);
			}
		}

		if (partSelected!=5 && sub2partSelected!=1) {
			if (objects.size()==1) {
				System.out.printf("%d Empleado%n", objects.size());	
			}
			else {
				System.out.printf("%d Empleados%n", objects.size());
			}
			if (partSelected!=3) {
				if (objectsDepart.size()==1) {
					System.out.printf("%d Departamento%n", objectsDepart.size());	
				}
				else {
					System.out.printf("%d Departamentos%n", objectsDepart.size());
				}
			}
		}

		while(objectsDepart.hasNext()) {
			Depart depart = objectsDepart.next();
			if (depart!=null) {
				listaDeparts.put(depart.getDeptNo(), depart);
				listaDepart.add(depart.getDnombre());
				listaNoDepart.add(depart.getDeptNo());
				if (partSelected==1) {
					System.out.printf("Depart Nombre: %s, DepartNo: %s, Localidad: %s.%n",depart.getDnombre(), depart.getDeptNo(), depart.getLoc());
				}
			}
		}

		while(objects.hasNext()){ // visualizar los objetos
			Emple emple = objects.next(); 
			if (emple!=null) {
				String dir = "No tiene";
				String dep = "No tiene";
				int depNo = 0;
				if (emple.getDir()!=null) {
					dir = emple.getDir().getApellido();
				}
				if (emple.getDept()!=null) {
					dep = emple.getDept().getDnombre();
					depNo = emple.getDept().getDeptNo();
				}
				if (partSelected==5 || insertSelected==2 || partSelected==4) {
					listaEmple.put(emple.getEmpNo(),emple);
					listaPersonas.add(emple.getApellido());
					listaNoPersonas.add(emple.getEmpNo());
				}
				if (partSelected==3 || partSelected==1 || sub2partSelected==2) {
					if (partSelected==1) {
						sub3partSelected=11;
					}
					print(sub3partSelected,emple,dir,dep,depNo,listaPersonas);
				}
			}
		}
		
		if (insertSelected==2) {
			int empNoInsert = preguntaType(listaPreguntas("Numero empleado:"),"ID ya existe","Integer",2,listaNoPersonas,null,null,null);
			String ApellidopersonaInsert = preguntaType(listaPreguntas("Apellido:"),"Empleado ya existe","String",2,null,listaPersonas,null,null);
			String oficioInsert = preguntaType(listaPreguntas("Oficio:"),"Introduce un valor valido","String");
			float comisionInsert = preguntaType(listaPreguntas("Comision"),"Introduce un valor disponible","Float");
			float salarioInsert = preguntaType(listaPreguntas("Salario"),"Introduce un valor disponible","Float");
			Emple direcInsert = preguntaType(listaPreguntas("Numero director:"),"Empleado no existe","Emple",0,null,null,listaEmple,null);
			Depart departInsert = preguntaType(listaPreguntas("Numero departamento:"),"Departamento no existe","Depart",0,null,null,null,listaDeparts);
			insertBDEmple(empNoInsert,ApellidopersonaInsert,oficioInsert,direcInsert,comisionInsert,departInsert,null,odb);
		}

		if(insertSelected==1) {
			int depNoInsert = preguntaType(listaPreguntas("Numero departamento:"),"ID ya existe","Integer",2,listaNoDepart,null,null,null);
			String depNombreInsert = preguntaType(listaPreguntas("Nombre departamento:"),"Departamento ya existe","String",0,null,listaDepart,null,null);
			String locInsert = preguntaType(listaPreguntas("Localidad:"),"Introduce un valor valido","String");
			insertBDDepart(depNoInsert, depNombreInsert,locInsert,odb);
		}

		if(partSelected==5) {
			sub5partSelected = preguntaType(listaPreguntas("Modificar el salario de un empleado que identificamos mediante su Apellido. [1]","Eliminar un empleado que identificamos mediante su Apellido. [2]"),"Introduce un entero disponible","Integer",1,listaRespuestas(1,2),null,null,null);
			//lista de personas per lista de respostas??????? 
			String Apellidopersona = preguntaType(listaPreguntas("Apellido:"),"Empleado no encontrado","String",1,null,listaPersonas,null,null);
			float salarioPersona=0; 
			IQuery query = new CriteriaQuery(Emple.class,
					Where.equal("apellido", Apellidopersona));
			Objects<Emple> empleado = odb.getObjects(query);				
			Emple empleado1 = (Emple) empleado.getFirst();
			if (sub5partSelected == 1) {
				salarioPersona = preguntaType(listaPreguntas("Salario: "),"Introduce un valor disponible","Float");
				empleado1.setComision(salarioPersona);
				odb.store(empleado1);
				text("Salario cambiado correctamente");
			}else {
				odb.delete(empleado1);
				text("Usuario borrado correctamente");
			}
		}

		if (partSelected==4) {
			sub4partSelected = preguntaType(listaPreguntas("Apellido [1]","Emp_no [2]"),"Introduce un entero disponible","Integer",1,listaRespuestas(1,2),null,null,null);
			try{
				Emple emple = null;
				Depart depart = null;
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conexion=DriverManager.getConnection("jdbc:mysql://192.168.56.10/ejemplo","austria","123");
				Statement sentencia =conexion.createStatement();
				String sql=null;
				if (sub4partSelected==1) {
					String ApellidopersonaInsert = preguntaType(listaPreguntas("Apellido:"),"Empleado ya existe","String",2,null,listaPersonas,null,null);	
					sql = "select emp_no,apellido,oficio,dir,fecha_alt,salario,comision,dept_no from emple where apellido='"+ApellidopersonaInsert+"';";	
				}else {
					int empNoInsert = preguntaType(listaPreguntas("Numero empleado:"),"ID ya existe","Integer",2,listaNoPersonas,null,null,null);
					sql = "select emp_no,apellido,oficio,dir,fecha_alt,salario,comision,dept_no from emple where emp_no="+empNoInsert+";";
				}

				ResultSet result = sentencia.executeQuery(sql);
				while (result.next()){
					if(listaEmple.containsKey(result.getInt(4))) {
						emple= listaEmple.get(result.getInt(4));
					}else {
						System.out.print("No existe su director");
					}
					if(listaDeparts.containsKey(result.getInt(8))) {
						depart = listaDeparts.get(result.getInt(8));
					}	
					insertBDEmple(result.getInt(1),
							result.getString(2),
							result.getString(3),
							emple,
							result.getFloat(6),
							depart,
							result.getDate(5),
							odb);
				}
				result.close();
				sentencia.close();
				conexion.close();

			} catch (ClassNotFoundException cn) {cn.printStackTrace(); 
			System.out.println("No existe su director");
			} catch (SQLException e) {e.printStackTrace();
			System.out.println("No existe su director2");
			}
		}
		odb.close(); // Cerrar BD
	}

	public static void print(int exNum, Emple emple, String dir, String dep, int depNo,ArrayList<String> listaPersonas) {
		switch(exNum) {
		case 11:
			System.out.printf("empNo: %s, apellido: %s, oficio: %s, dir: %s, fechaAlt: %s, comision: %s, dept: %s.%n",
					emple.getEmpNo(), emple.getApellido(),emple.getOficio(),dir,emple.getFechaAlt(),emple.getComision(), dep);
			break;
		case 21:
			System.out.printf("Nombre: %s, NombreDir: %s ,NombreDep: %s.%n",
					emple.getApellido(), dir,dep);
			break;
		case 1:
			System.out.printf("Apellido: %s, DeptNo: %s.%n",
					emple.getApellido(), depNo);
			break;
		case 2:
			break;
		case 3:
			System.out.printf("Nombre: %s, NombreDir: %s.%n",
					emple.getApellido(), dir);
			break;
		case 6:
			System.out.println("No existe el empleado.");
			break;
		}
	}

	public static void insertBDEmple(int empNo, String apellido, String oficio, Emple dir, float comision, Depart dept,Date date, ODB odb) {
		if (date==null) {
			date = new java.sql.Date(System.currentTimeMillis());
		}
		odb.store(new Emple(empNo,apellido,oficio,dir,date,comision,dept));
		text("Empleado introducido.");
	}

	public static void insertBDDepart(int deptNo, String dnombre, String loc,ODB odb) {
		odb.store(new Depart(deptNo,dnombre,loc));
		System.out.simplePrint("Departamento Introducido correctamente.");
	}	

	//Crea las querys para la parte 3.
	public static IQuery iQueryConstructor(int exnum){
		IQuery query = null;
		switch(exnum){
		case 1:
			query = new CriteriaQuery(Emple.class, Where.equal("dept.deptNo",10));
			return query;
		case 2:
			query = new CriteriaQuery(Emple.class, Where.equal("dept.dnombre","VENTAS"));
			return query;
		case 3:
			query = new CriteriaQuery(Emple.class, Where.equal("dir.apellido","FERNANDEZ"));
			return query;
		default:
			return null;
		}
	}

	private static <T> T preguntaType(ArrayList<String> listaPreguntas, String string, String string2) throws IOException {
		return (T) preguntaType(listaPreguntas, string, string2,0,null,null,null,null);
	}

	public static <T> T preguntaType(ArrayList<String> preguntas, String error, String type, int mode, ArrayList<Integer> repuestasI, ArrayList<String> respuestasS,HashMap<Integer, Emple> listaEmple, HashMap<Integer, Depart> listaDeparts) throws IOException {
		textos(preguntas);
		switch (type) {
		case "Integer":
			int partSelectedI = testType(error,"Integer");
			if (mode==1) {
				while (!repuestasI.contains(partSelectedI)) {
					text(error);
					partSelectedI = testType(error,"Integer");
				}
			}else if (mode==2) {
				while (repuestasI.contains(partSelectedI)) {
					text(error);
					partSelectedI = testType(error,"Integer");
				}
			}
			return (T) Integer.valueOf(partSelectedI);
		case "Float":
			float partSelectedF = testType(error, "Float");
			return (T) Float.valueOf(partSelectedF);
		case "String":
			String partSelectedS = testType(error,"String");
			if (mode==1) {
				while (!respuestasS.contains(partSelectedS)) {
					text(error);
					partSelectedS = testType(error,"String");
				}
			}else if (mode==2) {
				while (respuestasS.contains(partSelectedS)) {
					text(error);
					partSelectedS = testType(error,"String");
				}
			}
			return (T) partSelectedS;
		case "Emple":
			int partSelectedE = testType(error,"Integer");
			while (!listaEmple.containsKey(partSelectedE)) {
				text(error);
				partSelectedE = testType(error,"Integer");
			}
			Emple emple = listaEmple.get(partSelectedE);
			return (T) emple;
		case "Depart":
			int partSelectedD = testType(error,"Integer");
			while (!listaDeparts.containsKey(partSelectedD)) {
				text(error);
				partSelectedD = testType(error,"Integer");
			}
			Depart depart = listaDeparts.get(partSelectedD);
			return (T) depart;	
		default:
			return null;
		}
	}

	public static <T> T testType(String error,String type) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				switch(type) {
				case "Integer":
					int partSelectedI = Integer.parseInt(reader.readLine());
					return (T) Integer.valueOf(partSelectedI);
				case "Float":
					float partSelectedF = Float.parseFloat(reader.readLine());
					return (T) Float.valueOf(partSelectedF);
				case "String":
					return (T) reader.readLine();
				}
			}catch(Exception e) {
				System.out.println(error);
			}
		}
	}

	public static void textos(ArrayList<String> preguntas){
		for (String arg : preguntas) {
			System.out.println(arg);
		}
	}

	public static void text(String error){
		System.out.println(error);
	}

	public static ArrayList<String> listaPreguntas(String... args) {
		ArrayList<String> lista = new ArrayList<>();
		for (String arg : args) {
			lista.add(arg);
		}
		return lista;
	}

	public static ArrayList<Integer> listaRespuestas(Integer... args) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for (int arg : args) {
			lista.add(arg);
		}
		return lista;
	}
}
