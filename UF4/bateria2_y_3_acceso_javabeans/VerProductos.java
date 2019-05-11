package bateria2_y_3_acceso_javabeans;

import org.neodatis.odb.Objects;

public class VerProductos {
	public static void main(String[] args) {
		
		BaseDatos db = new BaseDatos();

		Objects<Producto> productos = db.getProductos();
		Objects<Pedido> pedidos = db.getPedidos();
		
		// Productos
		System.out.printf("%d Productos %n", productos.size());
		while (productos.hasNext()) {
			Producto producto = productos.next();
			System.out.printf("\t %d: %s, %d, %d %n", producto.getIdproducto(), producto.getDescripcion(),
					producto.getStockminimo(), producto.getStockactual());
		}
		
		// Pedidos
		System.out.printf("%d Pedidos %n", pedidos.size());
		while (pedidos.hasNext()) {
			Pedido pedido = pedidos.next();
			System.out.printf("\t %d: %s, %s, %d %n", pedido.getNumeroPedido(), pedido.getProducto().getDescripcion(),
					pedido.getFecha(), pedido.getCantidad());
		}
	}
}
