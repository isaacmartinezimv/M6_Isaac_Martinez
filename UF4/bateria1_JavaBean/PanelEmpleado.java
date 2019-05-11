package bateria1_JavaBean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Arrays;

public class PanelEmpleado implements Serializable, PropertyChangeListener {

	int limiteVariacionSueldo;
	private String[] listaDeCargos = {"Junior", "SemiSenior", "Analista", "CEO"};

	public PanelEmpleado() {
		this.limiteVariacionSueldo = 10;
	}

	public PanelEmpleado(int limiteVariacionSueldo) {
		if (this.limiteVariacionSueldo >= 10 && this.limiteVariacionSueldo <= 50) {
			this.limiteVariacionSueldo = limiteVariacionSueldo;
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {

		if (event.getPropertyName().equals("sueldo")) {
			float variacion = Math.abs((int)event.getNewValue()-(int)event.getOldValue());
			float porcentajeVariacion = ((variacion/(float)(int)event.getOldValue()) * 100);

			if (porcentajeVariacion > this.limiteVariacionSueldo) {
				throw new IllegalArgumentException("Se ha sobrepasado la variación de sueldo: " + this.limiteVariacionSueldo);
			}

		} else if (event.getPropertyName().equals("cargo")) {
			if (!Arrays.stream(this.listaDeCargos).anyMatch(((String)event.getNewValue())::equals)) {
				throw new IllegalArgumentException("El puesto no esta en a lista");
			}
		}
	}	
}


