package bateria2_y_3_acceso_javabeans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IValuesQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class BaseDatos {
	private ODB db;

	public BaseDatos() {
		db = ODBFactory.open("Producto_Ped.DB");
	}

	public void insertarProducto(Producto producto) {
		db.store(producto);
		db.commit();
	}

	public Producto getProducto(int idproducto) {
		ICriterion criterio = Where.equal("idproducto", idproducto);
		CriteriaQuery query = new CriteriaQuery(Producto.class, criterio);
		Objects<Producto> producto = db.getObjects(query);

		if (producto.hasNext()) {
			return producto.getFirst();
		} 
		
		return null;
	}

	public void updateProducto(Producto productoParaActualizar) {
		Producto productoActualizado = getProducto(productoParaActualizar.getIdproducto());

		if (productoActualizado != null) {
			productoActualizado.setDescripcion(productoParaActualizar.getDescripcion());
			productoActualizado.setStockactual(productoParaActualizar.getStockactual());
			productoActualizado.setStockminimo(productoParaActualizar.getStockminimo());

			db.store(productoActualizado);
			db.commit();
		}
	}
	
	public int getNuevoIdProducto() {
		IValuesQuery query = new ValuesCriteriaQuery(Producto.class).max("idproducto");
		Values values = db.getValues(query);

		return ((BigDecimal)values.next().getByAlias("idproducto")).intValue() + 1;
	}

	public Objects<Producto> getProductos() {
		return db.getObjects(Producto.class);
	}
	
	public void insertarPedido(Pedido pedido) {
		Producto productoComprado = getProducto(pedido.getProducto().getIdproducto());
		pedido.setProducto(productoComprado);

		db.store(pedido);
		db.commit();
	}

	public Producto getPedido(int idpedido) {
		ICriterion criterio = Where.equal("numeroPedido", idpedido);
		CriteriaQuery query = new CriteriaQuery(Pedido.class, criterio);
		Objects<Producto> producto = db.getObjects(query);

		if (producto.hasNext()) {
			return producto.getFirst();
		}
		
		return null;
	}

	public int getNuevoIdPedido() {
		IValuesQuery query = new ValuesCriteriaQuery(Pedido.class).max("numeroPedido");
		Values values = db.getValues(query);

		return ((BigDecimal)values.next().getByAlias("numeroPedido")).intValue() + 1;
	}

	public Objects<Pedido> getPedidos() {
		return db.getObjects(Pedido.class);
		
	}

	public Venta crearVenta(int idProducto, int cantidad) {
		Venta nuevaVenta = new Venta(getNuevoIdVenta(), cantidad, idProducto, "");
		db.store(nuevaVenta);
		db.commit();
		return nuevaVenta;
	}
		
	public Venta getVenta(int idventa) {
		ICriterion criterio = Where.equal("numeroVenta", idventa);
		CriteriaQuery query = new CriteriaQuery(Venta.class, criterio);
		Objects<Venta> ventas = db.getObjects(query);

		if (ventas.hasNext()) {
			return ventas.getFirst();
		} 
		
		return null;
	}

	public void updateVenta(Venta venta) {
		Venta ventaActualizada = getVenta(venta.getNumeroVenta());

		if (ventaActualizada != null) {
			ventaActualizada.setCantidad(venta.getCantidad());
			ventaActualizada.setFechaVenta(venta.getFechaVenta());
			ventaActualizada.setIdProducto(venta.getIdProducto());
			ventaActualizada.setObservaciones(venta.getObservaciones());

			db.store(ventaActualizada);
			db.commit();
		}
	}

	public int getNuevoIdVenta() {
		IValuesQuery query = new ValuesCriteriaQuery(Venta.class).max("numeroVenta");
		Values values = db.getValues(query);

		return ((BigDecimal)values.next().getByAlias("numeroVenta")).intValue() + 1;
	}

	public Objects<Venta> getVentas() {
		return db.getObjects(Venta.class);
	}

	public void cerrarBD() {
		db.close();
	}
}
