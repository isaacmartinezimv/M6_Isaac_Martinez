package bateria2_y_3_acceso_javabeans;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Date;

public class Venta implements Serializable, PropertyChangeListener {
	int idVenta;
	Date fechaVenta;
	int cantidad;
	int idProducto;
	String observaciones;

	public Venta() {}

	public Venta(int numeroVenta, int cantidad, int idProducto, String observaciones) {
		super();
		this.idVenta = numeroVenta;
		this.fechaVenta = new Date(System.currentTimeMillis());
		this.cantidad = cantidad;
		this.idProducto = idProducto;
		this.observaciones = observaciones;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("stockactual")) {
			BaseDatos db = new BaseDatos();

			Venta venta = ((Producto)evt.getSource()).getVenta();
			venta.setObservaciones("Pendiente de pedido por falta de stock");
			db.updateVenta(venta);

			db.cerrarBD();
		}
	}

	public int getNumeroVenta() {
		return idVenta;
	}

	public void setNumeroVenta(int numeroVenta) {
		this.idVenta = numeroVenta;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
