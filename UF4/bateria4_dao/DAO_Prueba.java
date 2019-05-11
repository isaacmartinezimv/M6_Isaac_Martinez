package bateria4_dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class DAO_Prueba {
	public static void main(String[] args) throws IOException, InterruptedException {
		DepartamentoDAO depDAO = new DepartamentoImpl();
		
		//GENERAMOS UN DEPARTAMENTO
		Departamento newDep = new Departamento(1, "VENTAS", "MALLORCA");
		
		//LLAMAMOS AL PRIMER METODO PARA INSERTAR EL DEPARTAMENTO
		depDAO.InsertarDep(newDep);
		
		//IMPRIMIMOS LOS DATOS PARA COMPROBAR QUE SE HA INSERTADO
		System.out.println(depDAO.ConsultarDep(1).getDeptno());
		System.out.println(depDAO.ConsultarDep(1).getDnombre());
		System.out.println(depDAO.ConsultarDep(1).getLoc());

		//CREAMOS UN NUEVO DEPARTAMENTO CON LAS MODIFICACIONES
		Departamento modifDep = new Departamento(1, "DESARROLLO", "LLEIDA");
		
		//APLICAMOS LAS MODIFICACIONES LLAMANDO AL MEDOTO MODIFICARDEP
		depDAO.ModificarDep(1, modifDep);
		
		//IMPRIMIMOS EL DEPARTAMENTO CON LAS MODIFICACIONES
		System.out.println(depDAO.ConsultarDep(1).getDeptno());
		System.out.println(depDAO.ConsultarDep(1).getDnombre());
		System.out.println(depDAO.ConsultarDep(1).getLoc());
	
		//ELIMINAMOS EL DEPARTAMENTO INDICADO
		depDAO.EliminarDep(1);
		try {
			System.out.println(depDAO.ConsultarDep(1).getDeptno());
			System.out.println(depDAO.ConsultarDep(1).getDnombre());
			System.out.println(depDAO.ConsultarDep(1).getLoc());
		} catch (Exception e) { }
	}
}
