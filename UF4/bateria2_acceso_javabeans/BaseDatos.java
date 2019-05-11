package bateria2_acceso_javabeans;

import java.math.BigDecimal;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class BaseDatos {
	ODB db;

	public BaseDatos() {
		this.db = ODBFactory.open("Producto_Ped.DB");
	}

	public void insertarProducto(Producto producto) {
		db.store(producto);
		db.commit();
	}

	public void insertarPedido(Pedido pedido) {
		db.store(pedido);
		db.commit();
	}
	
	// PENDIENTE
	public void insertarVenta(Venta venta) {
		db.store(venta);
		db.commit();
	}

	public void cerrarBD() {
		db.close();
	}
	
		
	public Objects<Producto> getProductos() {
		return db.getObjects(Producto.class);
	}

	public Objects<Pedido> getPedidos() {
		return db.getObjects(Pedido.class);
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

	public int getAutoIdPedido() {
		Values val = db.getValues(new ValuesCriteriaQuery(Pedido.class).max("numeroPedido"));
		int autoId = ((BigDecimal) val.next().getByAlias("numeroPedido")).intValue(); 
		return autoId;
	}

}
