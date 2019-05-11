package bateria1_JavaBean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Empleado implements Serializable {
	
	private String NIF, nombre, cargo;
	private int sueldo;
		
	private PropertyChangeSupport changeSupport;

	public Empleado() {
		changeSupport = new PropertyChangeSupport (this);
		this.cargo = "Junior";
		this.sueldo = 1000;
	}

	public Empleado(String NIF, String nombre) {
		this();
		this.NIF = NIF;
		this.nombre = nombre;
	}

	public String getNif() {
		return NIF;
	}

	public void setNif(String nif) {
		this.NIF = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		
		if (cargo != null && !cargo.isEmpty()) {
			
			try {
				changeSupport.firePropertyChange("cargo", this.cargo, cargo);
				this.cargo = cargo;
				
			} catch (Exception e) {
				System.out.println("El puesto no esta en a lista");
				e.printStackTrace();
			}
		}
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		
		if (sueldo > 0) {
			
			try {
				changeSupport.firePropertyChange("sueldo", this.sueldo, sueldo);
				this.sueldo = sueldo;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}
}
