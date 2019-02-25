package proyecto_NeoDartis;

public class Emple {
	private int empNo;
	private String apellido;
	private String oficio;
	private Emple dir;
	private  java.sql.Date fechaAlt;
	private float comision;
	private Depart dept;

	public Emple() {
		super();
	}

	public Emple(int empNo, String apellido, String oficio, Emple dir, java.sql.Date fechaAlt, float comision, Depart dept) {
		super();
		this.empNo = empNo;
		this.apellido = apellido;
		this.oficio = oficio;
		this.dir = dir;
		this.fechaAlt = fechaAlt;
		this.comision = comision;
		this.dept = dept;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public Emple getDir() {
		return dir;
	}

	public void setDir(Emple dir) {
		this.dir = dir;
	}

	public java.sql.Date getFechaAlt() {
		return fechaAlt;
	}

	public void setFechaAlt(java.sql.Date fechaAlt) {
		this.fechaAlt = fechaAlt;
	}

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}

	public Depart getDept() {
		return dept;
	}

	public void setDept(Depart dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return apellido;
	}
}
