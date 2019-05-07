package Bateria1_JavaBean;
import java.beans.*;
import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
public class Pedido implements Serializable, PropertyChangeListener {     
    private int numeroPedido;
    private Producto producto;
    private Date fecha;
    private int cantidad;   
    public Pedido() { }
    public Pedido (int numeroPedido, Producto producto, Date fecha, int cantitad) {
            this.numeroPedido = numeroPedido;
            this.producto = producto;
            this.fecha = fecha;
            this.cantidad = cantitad;
    }
   
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    	System.out.printf("stock anterior: %d%n",evt.getOldValue());
    	System.out.printf("stock actual: %d%n",evt.getNewValue());
    	System.out.printf("REALIZAR PEDIDO EN PRODUCTO: %s%n",producto.getDescripcion());
     }
	public int getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
