package bateria2_y_3_acceso_javabeans;

public class Prueba {

	public static void main(String[] args) {
		VerProductos ver = new VerProductos();

		// Imprimimos los datos iniciales de la Base de datos
		System.out.println("PRODUCTOS\n--------------");
		ver.printDatosProductos();

		System.out.println("VENTAS\n--------------");
		ver.printDatosVentas();

		System.out.println("PEDIDOS\n--------------");
		ver.printDatosPedidos();

		venderPrimerProducto();
		venderSegundoProducto();


		//Mostramos los datos despues de los cambios
		System.out.println("POST - PRODUCTOS\n--------------");
		ver.printDatosProductos();

		System.out.println("POST - PEDIDOS\n--------------");
		ver.printDatosPedidos();

		System.out.println(" POST - VENTAS\n--------------");
		ver.printDatosVentas();
	}


	public static void venderPrimerProducto() {
		// Escogemos un producto que vamos a vender para comprobar como se repone el stock
		BaseDatos db = new BaseDatos();
		Producto product = db.getProducto(6);
		db.cerrarBD();

		if (product != null) {
			// Creamos un pedido y una venta
			Pedido pedido = new Pedido();
			Venta venta = new Venta();

			//Añadimos el producto escogido al pedido
			pedido.setProducto(product);

			//Añadimos el listener al producto
			product.addPropertyChangeListener(pedido);
			product.addPropertyChangeListener(venta);
			product.setVenta(venta);

			//Restamos la cantidad de producto para que quede menos del minimo
			product.realizarVenta(4);

			db = new BaseDatos();
			db.updateProducto(product);
			db.cerrarBD();
		} 
	}


	public static void venderSegundoProducto() {
		// Escogemos un producto que vamos a vender para comprobar como se repone el stock
		BaseDatos db = new BaseDatos();
		Producto product = db.getProducto(1);
		db.cerrarBD();

		if (product != null) {
			// Creamos un pedido y una venta
			Pedido pedido = new Pedido();
			Venta venta = new Venta();

			//Añadimos el producto escogido al pedido
			pedido.setProducto(product);

			//Añadimos el listener al producto
			product.addPropertyChangeListener(pedido);
			product.addPropertyChangeListener(venta);
			product.setVenta(venta);

			//Restamos la cantidad de producto para que quede menos del minimo
			product.realizarVenta(8);

			db = new BaseDatos();
			db.updateProducto(product);
			db.cerrarBD();

		} 
	}
}


