package bateria1_JDBC1;

/* 3. Realiza un programa que busque los departamentos de una localidad. El programa 
 * solicitará el nombre de una localidad al usuario y devolverá los departamentos 
 * asociados a dicha localidad y los empleados de dicho departamentos. */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class EX03_Ejercicio03 {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduce la localidad que quieres consultar: ");
		String localidad = reader.readLine();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection
					("jdbc:mysql://localhost/ejemplo","austria","austria");
			Statement sentencia = conexion.createStatement();

			String sql = "SELECT * from depart join emple using(dept_no) where depart.loc = '"+localidad+"';";
			ResultSet result = sentencia.executeQuery(sql);

			while (result.next()) {
				System.out.printf("%s, %s, %d %n",
						result.getString("apellido"), result.getString("oficio"), result.getInt("salario"));
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
