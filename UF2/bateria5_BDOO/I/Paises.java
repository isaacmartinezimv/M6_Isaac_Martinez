package bateria5_BDOO.I;

public class Paises {
	private int id;
	private String nombrePais;
	
	public Paises() {
		super();
	}

	public Paises(int id, String nombrepais) {
		super();
		this.id = id;
		this.nombrePais = nombrepais;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombrepais() {
		return nombrePais;
	}

	public void setNombrepais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	@Override
	public String toString() {
		return nombrePais;
	}
}