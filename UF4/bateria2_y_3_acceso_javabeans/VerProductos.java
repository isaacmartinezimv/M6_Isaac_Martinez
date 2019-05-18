package bateria2_y_3_acceso_javabeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.neodatis.odb.Objects;

public class VerProductos implements Serializable {

	public VerProductos() {}

	public void printDatosVentas() {
		BaseDatos db = new BaseDatos();		
		List<List<String>> rows = new ArrayList<>();
		List<String> headers = Arrays.asList("NUM. VENTA", "FECHA", "CANTIDAD", "PRODUCTO", "OBSERVACIONES");
		rows.add(headers);
		
		for(Venta venta : db.getVentas()) {
			rows.add(Arrays.asList(String.valueOf(venta.getNumeroVenta()),
					String.valueOf(venta.getFechaVenta()), 
					String.valueOf(venta.getCantidad()), 
					db.getProducto(venta.getIdProducto()).getDescripcion(), 
					venta.getObservaciones()));
		}
		
		System.out.println(formatAsTable(rows));
		db.cerrarBD();
	}


	public void printDatosPedidos() {
		BaseDatos db = new BaseDatos();		
		List<List<String>> rows = new ArrayList<>();
		List<String> headers = Arrays.asList("NUM. PEDIDO", "PRODUCTO", "FECHA", "CANTIDAD");
		rows.add(headers);
		
		for(Pedido pedido : db.getPedidos()) {
			rows.add(Arrays.asList(String.valueOf(pedido.getNumeroPedido()), 
					String.valueOf(pedido.getProducto()), 
					String.valueOf(pedido.getFecha()), 
					String.valueOf(pedido.getCantidad())));
		}
		
		System.out.println(formatAsTable(rows));
		db.cerrarBD();
	}

	public void printDatosProductos() {
		BaseDatos db = new BaseDatos();		
		List<List<String>> rows = new ArrayList<>();
		List<String> headers = Arrays.asList("ID", "DESCRIPCIÓN", "STOCK ACTUAL", "STOCK MÍNIMO", "PVP");
		rows.add(headers);
		
		for (Producto producto : db.getProductos()) {
			rows.add(Arrays.asList(String.valueOf(producto.getIdproducto()),
					producto.getDescripcion(),
					String.valueOf(producto.getStockactual()),
					String.valueOf(producto.getStockminimo()),
					String.valueOf(producto.getPvp()+"€")));
		}
		
		System.out.println(formatAsTable(rows));
		db.cerrarBD();

	}
	
	public void mostrarTodo() {
		printDatosProductos();
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------\n");
		printDatosPedidos();
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------\n");
		printDatosVentas();
	}

	
	// FUNCIÓN EXTRAIDA DE LIBRERIA
	public static String formatAsTable(List<List<String>> rows) {
	    int[] maxLengths = new int[rows.get(0).size()];
	    
	    for (List<String> row : rows) {
	        for (int i = 0; i < row.size(); i++) {
	            maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
	        }
	    }

	    StringBuilder formatBuilder = new StringBuilder();
	    for (int maxLength : maxLengths) {
	        formatBuilder.append("%-").append(maxLength + 12).append("s");
	    }
	    
	    String format = formatBuilder.toString();
	    StringBuilder result = new StringBuilder();
	    
	    for (List<String> row : rows) {
	        result.append(String.format(format, row.toArray(new String[0]))).append("\n");
	    }
	    
	    return result.toString();
	}
}
