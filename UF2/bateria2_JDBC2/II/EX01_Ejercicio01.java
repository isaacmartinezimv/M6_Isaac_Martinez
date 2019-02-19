package bateria2_JDBC2.II;

/* 1. Realiza un programa en Java que suba el salario a los empleados de un 
 * departamento. El programa recibirá el número de departamento y el incremento. */

import java.sql.*;

public class EX01_Ejercicio01 {
	public static void main (String[] args){

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection
					("jdbc:mysql://localhost/ejemplo","austria","austria");

			String depart = args[0];
			String increment = args[1];

			String sql = String.format("UPDATE emple SET salario = salario + %s where dept_no = %s", increment, depart);
			Statement statement = conexion.createStatement();
			
			int filas = statement.executeUpdate(sql);
			System.out.printf("Filas afectadas: %d %n", filas);

			statement.close();
			conexion.close();
		}
		
		catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
}
