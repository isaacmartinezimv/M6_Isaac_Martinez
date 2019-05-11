package bateria1_JavaBean;

public class PruebasFinales {
	public static void main(String[] args) {
		Empleado emple = new Empleado("62473411P", "Popolopoulos");
		PanelEmpleado panelEmpleado = new PanelEmpleado();
		
		emple.addPropertyChangeListener(panelEmpleado);
		
		// TRUE
		System.out.println("Testing cargo: Analista");
		emple.setCargo("Analista");
		
		// TRUE
		System.out.println("Testing cargo: SemiSenior");
		emple.setCargo("SemiSenior");
		
		// FALSE
		System.out.println("Testing cargo: Cientifico");
		emple.setCargo("Cientifico");
		
		// FASLE
		System.out.println("Testing sueldo: 4561");
		emple.setSueldo(4561);
		
		// TRUE
		System.out.println("Testing sueldo: 1100");
		emple.setSueldo(1100);
		
		// FALSE
		System.out.println("Testing sueldo: 456");
		emple.setSueldo(456);

	}
}
