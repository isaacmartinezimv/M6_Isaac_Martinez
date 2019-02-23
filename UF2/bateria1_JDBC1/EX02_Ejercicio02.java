package bateria1_JDBC1;

/* 2. Realiza otro programa Java utilizando la base de datos “ejemplo” que visualice 
 * el APELLIDO del empleado con máximo salario, visualiza también su SALARIO y el 
 * nombre del departamento. */

import java.sql.*;

public class EX02_Ejercicio02 {
	public static void main(String[] args) {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo","austria","austria");
			Statement sentencia = conexion.createStatement();

			String sql = "SELECT * from emple join depart using(dept_no) where salario = (select MAX(salario) from emple);";
			ResultSet result = sentencia.executeQuery(sql);

			while (result.next()) {
				System.out.printf("%s, %s, %d %n",
						result.getString("apellido"), result.getString("oficio"), result.getInt("salario"));
				System.out.println("");
			}

			result.close();
			sentencia.close();
			conexion.close();

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
