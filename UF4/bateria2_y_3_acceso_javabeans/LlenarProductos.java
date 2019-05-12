package bateria2_y_3_acceso_javabeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LlenarProductos {

	public static void main(String[] args) {
		
		BaseDatos db = new BaseDatos();
		
		Producto p1 = new Producto(1, "Duruss Cobalt", 10, 3, 220);
		db.insertarProducto(p1);
		
		Producto p2 = new Producto(2, "Varlion Avant Carbon", 5, 2, 176);
		db.insertarProducto(p2);
		
		Producto p3 = new Producto(3, "Star Vie Pyramid R50", 20, 5, 193);
		db.insertarProducto(p3);
		
		Producto p4 = new Producto(4, "Dunlop Titan", 8, 3, 85);
		db.insertarProducto(p4);
		
		Producto p5 = new Producto(5, "Vision King", 7, 1, 159);
		db.insertarProducto(p5);
		
		Producto p6 = new Producto(6, "Slazenger Reflex Pro", 5, 2, 80);
		db.insertarProducto(p6);

		db.cerrarBD();
	}
}
